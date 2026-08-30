package blogpublisher.service;

import blogpublisher.dto.AiClassifyResponse;
import blogpublisher.dto.BlogPostDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
public class OpenAiService {

    private final RestClient openAiRestClient;

    public OpenAiService(
            @Qualifier("openAiRestClient") RestClient openAiRestClient
    ){
      this.openAiRestClient = openAiRestClient;
    }

    public AiClassifyResponse classify(BlogPostDto post) {

        Map<String, String> request = Map.of(
            "title", post.getTitle(),
                "content", post.getContent()
        );

        return openAiRestClient.post()
                .uri("/ai/classify")
                .body(request)
                .retrieve()
                .body(AiClassifyResponse.class);
    }
}
