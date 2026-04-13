package shopping_cart.backend.dto;

import java.time.LocalDateTime;

public record BasketSnapshotResponse(
    Long basketId,
    Long shopperId,
    String state,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
}
