package shopping_cart.backend.dto;

import java.math.BigDecimal;

public record BasketAmountsResponse(
    Long basketId,
    Integer totalLines,
    Integer totalUnits,
    BigDecimal grossAmount
) {
}
