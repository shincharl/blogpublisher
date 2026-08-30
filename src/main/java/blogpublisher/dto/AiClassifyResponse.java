package blogpublisher.dto;

public record AiClassifyResponse (
        String category,
        double confidence
) {

}
