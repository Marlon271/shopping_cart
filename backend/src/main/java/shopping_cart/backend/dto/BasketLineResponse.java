package shopping_cart.backend.dto;

import java.math.BigDecimal;

public record BasketLineResponse(
    Long lineId,
    Long basketId,
    Long skuId,
    String productLabel,
    Integer units,
    BigDecimal unitAmount,
    BigDecimal lineAmount
) {
}
