package shopping_cart.backend.dto;

import java.math.BigDecimal;

public record BasketDetailLineResponse(
    Long lineId,
    Long skuId,
    String productLabel,
    Integer units,
    BigDecimal unitAmount,
    BigDecimal lineAmount
) {
}
