package shopping_cart.backend.service;

import shopping_cart.backend.dto.AdjustBasketLineUnitsRequest;
import shopping_cart.backend.dto.BasketAmountsResponse;
import shopping_cart.backend.dto.BasketDetailResponse;
import shopping_cart.backend.dto.BasketLineResponse;
import shopping_cart.backend.dto.RemoveBasketLineResponse;
import shopping_cart.backend.dto.RegisterBasketLineRequest;

public interface IBasketService {

    BasketOpeningResult openBasket(Long shopperId);

    BasketLineResponse registerLine(Long basketId, RegisterBasketLineRequest request);

    BasketDetailResponse getBasketDetail(Long basketId);

    BasketLineResponse adjustLineUnits(Long basketId, Long lineId, AdjustBasketLineUnitsRequest request);

    RemoveBasketLineResponse removeLine(Long basketId, Long lineId);

    BasketAmountsResponse getBasketAmounts(Long basketId);
}
