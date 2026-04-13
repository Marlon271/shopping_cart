package shopping_cart.gateway.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record RegisterBasketLineRequest(
    @NotNull(message = "skuId es obligatorio")
    @Positive(message = "skuId debe ser un valor positivo")
    Long skuId,

    @NotBlank(message = "productLabel es obligatorio")
    String productLabel,

    @NotNull(message = "units es obligatorio")
    @Positive(message = "units debe ser mayor a cero")
    Integer units,

    @NotNull(message = "unitAmount es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "unitAmount debe ser mayor a cero")
    BigDecimal unitAmount
) {
}
