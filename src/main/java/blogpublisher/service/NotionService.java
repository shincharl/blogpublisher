package blogpublisher.service;

import blogpublisher.dto.BlogPostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotionService {
    private final RestClient notionRestClient;
    private final ObjectMapper objectMapper;

    public String getMe(){
        return notionRestClient.get()
                .uri("/users/me")
                .retrieve()
                .body(String.class);
    }

    public String search() {
        return notionRestClient.post()
                .uri("/search")
                .body("""
                        {
                            "query":"My_Blog_Project_DB"
                        }
                        """)
                .retrieve()
                .body(String.class);
    }

    // 조회할수 있는 데이터베이스 노션 api 목록 메서드
    public String searchAll() {
        return notionRestClient.post()
                .uri("/search")
                .body("{}")
                .retrieve()
                .body(String.class);
    }

    // 조회한 노션 자식 조회 메서드
    public String getPageChildren(String pageId) {
        return notionRestClient.get()
                .uri("/blocks/" + pageId + "/children")
                .retrieve()
                .body(String.class);
    }

    // 데이터베이스 조회 메서드
    public String getDatabase(String databaseId){
        return notionRestClient.get()
                .uri("/databases/" + databaseId)
                .retrieve()
                .body(String.class);
    }

    // 글 목록 조회 메서드
    public String queryDataSource(String dataSourceId) {
        return notionRestClient.post()
                .uri("/data_sources/" + dataSourceId + "/query")
                .body("{}")
                .retrieve()
                .body(String.class);
    }

    public List<BlogPostDto> getPosts(String dataSourceId) throws Exception {

        String response =  notionRestClient.post()
                .uri("/data_sources/" + dataSourceId + "/query")
                .body("{}")
                .retrieve()
                .body(String.class);

        JsonNode root = objectMapper.readTree(response);

        List<BlogPostDto> posts = new ArrayList<>();

        for (JsonNode page : root.get("results")) {

            JsonNode properties = page.get("properties");

            String pageId = page
                    .get("id")
                    .asText();

            String title = properties
                    .get("제목")
                    .get("title")
                    .get(0)
                    .get("plain_text")
                    .asText();

            String status = properties
                    .get("상태")
                    .get("status")
                    .get("name")
                    .asText();

            String category = properties
                    .get("분류")
                    .get("rich_text")
                    .get(0)
                    .get("plain_text")
                    .asText()
                    .trim();

            String createdDate = properties
                    .get("작성일")
                    .get("date")
                    .get("start")
                    .asText();

            BlogPostDto post = new BlogPostDto(
                    pageId,
                    title,
                    status,
                    category,
                    createdDate
            );

            posts.add(post);

        }

        return posts;
    }



}
