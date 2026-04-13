package shopping_cart.backend.service;

import shopping_cart.backend.dto.BasketLineResponse;
import shopping_cart.backend.dto.RegisterBasketLineRequest;

public interface IBasketService {

    BasketOpeningResult openBasket(Long shopperId);

    BasketLineResponse registerLine(Long basketId, RegisterBasketLineRequest request);
}
