package shopping_cart.backend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AdjustBasketLineUnitsRequest(
    @NotNull(message = "units es obligatorio")
    @Positive(message = "units debe ser mayor a cero")
    Integer units
) {
}
