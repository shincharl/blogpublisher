package blogpublisher.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class BlogPostDto {

    private String pageId;
    private String title;
    private String status;
    private String category;
    private String createdDate;
}
