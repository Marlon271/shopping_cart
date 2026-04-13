package shopping_cart.gateway.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shopping_cart.gateway.dto.OpenBasketRequest;
import shopping_cart.gateway.dto.RegisterBasketLineRequest;
import shopping_cart.gateway.service.BasketGatewayService;

@RestController
@RequestMapping("/api/v1/baskets")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Validated
public class BasketGatewayController {

    private final BasketGatewayService basketGatewayService;

    @PostMapping
    public ResponseEntity<Object> openBasket(@Valid @RequestBody OpenBasketRequest request) {
        return basketGatewayService.openBasket(request);
    }

    @GetMapping("/{basketId}")
    public ResponseEntity<Object> getBasketDetail(
        @PathVariable @Positive(message = "basketId debe ser un valor positivo") Long basketId
    ) {
        return basketGatewayService.getBasketDetail(basketId);
    }

    @PostMapping("/{basketId}/lines")
    public ResponseEntity<Object> registerLine(
        @PathVariable @Positive(message = "basketId debe ser un valor positivo") Long basketId,
        @Valid @RequestBody RegisterBasketLineRequest request
    ) {
        return basketGatewayService.registerLine(basketId, request);
    }
}
