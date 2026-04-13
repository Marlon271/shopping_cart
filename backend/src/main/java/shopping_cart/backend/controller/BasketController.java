package shopping_cart.backend.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shopping_cart.backend.dto.BasketLineResponse;
import shopping_cart.backend.dto.BasketSnapshotResponse;
import shopping_cart.backend.dto.OpenBasketRequest;
import shopping_cart.backend.dto.RegisterBasketLineRequest;
import shopping_cart.backend.service.BasketOpeningResult;
import shopping_cart.backend.service.IBasketService;

@RestController
@RequestMapping("/api/v1/baskets")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Validated
public class BasketController {

    private final IBasketService basketService;

    @PostMapping
    public ResponseEntity<BasketSnapshotResponse> openBasket(@Valid @RequestBody OpenBasketRequest request) {
        BasketOpeningResult result = basketService.openBasket(request.shopperId());
        HttpStatus status = result.created() ? HttpStatus.CREATED : HttpStatus.OK;
        return ResponseEntity.status(status).body(result.basket());
    }

    @PostMapping("/{basketId}/lines")
    public ResponseEntity<BasketLineResponse> registerLine(
        @PathVariable @Positive(message = "basketId debe ser un valor positivo") Long basketId,
        @Valid @RequestBody RegisterBasketLineRequest request
    ) {
        BasketLineResponse response = basketService.registerLine(basketId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
