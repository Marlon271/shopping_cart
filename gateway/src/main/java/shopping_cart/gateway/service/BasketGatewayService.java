package shopping_cart.gateway.service;

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

    public ResponseEntity<Object> registerLine(Long basketId, RegisterBasketLineRequest request) {
        return backendRestClient.post()
            .uri("/api/v1/baskets/{basketId}/lines", basketId)
            .body(request)
            .retrieve()
            .toEntity(Object.class);
    }
}
