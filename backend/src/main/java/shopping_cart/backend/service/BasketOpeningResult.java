package shopping_cart.backend.service;

import shopping_cart.backend.dto.BasketSnapshotResponse;

public record BasketOpeningResult(BasketSnapshotResponse basket, boolean created) {
}
