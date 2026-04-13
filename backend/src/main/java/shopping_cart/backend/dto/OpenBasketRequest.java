package shopping_cart.backend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OpenBasketRequest(
    @NotNull(message = "shopperId es obligatorio")
    @Positive(message = "shopperId debe ser un valor positivo")
    Long shopperId
) {
}
