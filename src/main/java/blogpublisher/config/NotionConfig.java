package blogpublisher.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class NotionConfig {

    @Bean
    public RestClient notionRestClient(
            @Value("${notion.token}") String token
    ){
        return RestClient.builder()
                .baseUrl("https://api.notion.com/v1")
                .defaultHeader("Authorization", "Bearer " + token)
                .defaultHeader("Notion-Version", "2025-09-03")
                .build();
    }
}
