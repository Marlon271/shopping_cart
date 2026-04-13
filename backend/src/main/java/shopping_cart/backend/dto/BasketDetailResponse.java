package shopping_cart.backend.dto;

import java.time.LocalDateTime;
import java.util.List;

public record BasketDetailResponse(
    Long basketId,
    Long shopperId,
    String state,
    Integer lineCount,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    List<BasketDetailLineResponse> lines
) {
}
