package shopping_cart.backend.dto;

public record RemoveBasketLineResponse(
    String message,
    Long basketId,
    Long lineId
) {
}
