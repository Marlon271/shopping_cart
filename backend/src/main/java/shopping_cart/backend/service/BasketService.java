package shopping_cart.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shopping_cart.backend.dto.BasketSnapshotResponse;
import shopping_cart.backend.entity.Basket;
import shopping_cart.backend.entity.BasketState;
import shopping_cart.backend.repository.BasketRepository;

@Service
@RequiredArgsConstructor
public class BasketService implements IBasketService {

    private final BasketRepository basketRepository;

    @Override
    public BasketOpeningResult openBasket(Long shopperId) {
        return basketRepository.findFirstByShopperIdAndState(shopperId, BasketState.OPEN)
            .map(existingBasket -> new BasketOpeningResult(toResponse(existingBasket), false))
            .orElseGet(() -> {
                Basket storedBasket = basketRepository.save(Basket.builder()
                    .shopperId(shopperId)
                    .state(BasketState.OPEN)
                    .build());

                return new BasketOpeningResult(toResponse(storedBasket), true);
            });
    }

    private BasketSnapshotResponse toResponse(Basket basket) {
        return new BasketSnapshotResponse(
            basket.getId(),
            basket.getShopperId(),
            basket.getState().name(),
            basket.getCreatedAt(),
            basket.getUpdatedAt()
        );
    }
}
