package shopping_cart.gateway.service;

import shopping_cart.gateway.dto.AdjustBasketLineUnitsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import shopping_cart.gateway.dto.OpenBasketRequest;
import shopping_cart.gateway.dto.RegisterBasketLineRequest;

@Service
@RequiredArgsConstructor
public class BasketGatewayService {

    private final RestClient backendRestClient;

    public ResponseEntity<Object> openBasket(OpenBasketRequest request) {
        return backendRestClient.post()
            .uri("/api/v1/baskets")
            .body(request)
            .retrieve()
            .toEntity(Object.class);
    }

    public ResponseEntity<Object> getBasketDetail(Long basketId) {
        return backendRestClient.get()
            .uri("/api/v1/baskets/{basketId}", basketId)
            .retrieve()
            .toEntity(Object.class);
    }

    public ResponseEntity<Object> getBasketAmounts(Long basketId) {
        return backendRestClient.get()
            .uri("/api/v1/baskets/{basketId}/amounts", basketId)
            .retrieve()
            .toEntity(Object.class);
    }

    public ResponseEntity<Object> adjustLineUnits(Long basketId, Long lineId, AdjustBasketLineUnitsRequest request) {
        return backendRestClient.patch()
            .uri("/api/v1/baskets/{basketId}/lines/{lineId}", basketId, lineId)
            .body(request)
            .retrieve()
            .toEntity(Object.class);
    }

    public ResponseEntity<Object> removeLine(Long basketId, Long lineId) {
        return backendRestClient.delete()
            .uri("/api/v1/baskets/{basketId}/lines/{lineId}", basketId, lineId)
            .retrieve()
            .toEntity(Object.class);
    }

    public ResponseEntity<Object> registerLine(Long basketId, RegisterBasketLineRequest request) {
        return backendRestClient.post()
            .uri("/api/v1/baskets/{basketId}/lines", basketId)
            .body(request)
            .retrieve()
            .toEntity(Object.class);
    }
}
