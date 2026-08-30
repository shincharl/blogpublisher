package blogpublisher.controller;

import blogpublisher.dto.AiClassifyResponse;
import blogpublisher.dto.BlogPostDto;
import blogpublisher.service.NotionService;
import blogpublisher.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OpenAiController {

    private final NotionService notionService;
    private final OpenAiService aiService;

    @GetMapping("/api/ai/test")
    public AiClassifyResponse test() throws Exception {

        List<BlogPostDto> posts = notionService.getPosts(
                "3c43ae92-25c6-8005-a0ad-000b71f47d97"
        );

        BlogPostDto post = posts.get(0);

        return aiService.classify(post);
    }
}
