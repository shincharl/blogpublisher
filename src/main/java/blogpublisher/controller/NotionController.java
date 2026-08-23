package blogpublisher.controller;

import blogpublisher.dto.BlogPostDto;
import blogpublisher.service.NotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notion")
public class NotionController {

    private final NotionService notionService;

    @GetMapping("/test")
    public String test(){
        return notionService.getMe();
    }

    @GetMapping("/search")
    public String search(){
        return notionService.search();
    }

    @GetMapping("/search-all")
    public String searchAll() {
        return notionService.searchAll();
    }

    @GetMapping("/page/{pageId}/children")
    public String getPageChildren(@PathVariable String pageId){
        return notionService.getPageChildren(pageId);
    }

    @GetMapping("/database/{databaseId}")
    public String getDatabase(@PathVariable String databaseId){
        return notionService.getDatabase(databaseId);
    }

    @GetMapping("/datasource/{dataSourceId}")
    public String queryDataSource(@PathVariable String dataSourceId){
        return notionService.queryDataSource(dataSourceId);
    }

    @GetMapping("/posts")
    public List<BlogPostDto> getPosts() throws Exception {

        return notionService.getPosts(
                "3c43ae92-25c6-8005-a0ad-000b71f47d97"
        );
    }
}
