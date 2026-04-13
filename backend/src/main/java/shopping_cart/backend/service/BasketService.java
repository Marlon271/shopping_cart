package shopping_cart.backend.service;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shopping_cart.backend.dto.BasketLineResponse;
import shopping_cart.backend.dto.BasketSnapshotResponse;
import shopping_cart.backend.dto.RegisterBasketLineRequest;
import shopping_cart.backend.entity.Basket;
import shopping_cart.backend.entity.BasketLine;
import shopping_cart.backend.entity.BasketState;
import shopping_cart.backend.exception.BusinessRuleException;
import shopping_cart.backend.exception.ResourceMissingException;
import shopping_cart.backend.repository.BasketLineRepository;
import shopping_cart.backend.repository.BasketRepository;

@Service
@RequiredArgsConstructor
public class BasketService implements IBasketService {

    private final BasketRepository basketRepository;
    private final BasketLineRepository basketLineRepository;

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

    @Override
    @Transactional
    public BasketLineResponse registerLine(Long basketId, RegisterBasketLineRequest request) {
        Basket basket = basketRepository.findById(basketId)
            .orElseThrow(() -> new ResourceMissingException("No existe una canasta con id " + basketId));

        if (basket.getState() != BasketState.OPEN) {
            throw new BusinessRuleException("Solo se pueden registrar lineas en canastas abiertas");
        }

        BigDecimal normalizedUnitAmount = request.unitAmount().setScale(2, java.math.RoundingMode.HALF_UP);

        BasketLine line = basketLineRepository.findFirstByBasketIdAndSkuId(basketId, request.skuId())
            .map(existingLine -> {
                existingLine.appendUnits(request.units());
                existingLine.refreshSnapshot(request.productLabel(), normalizedUnitAmount);
                return existingLine;
            })
            .orElseGet(() -> BasketLine.builder()
                .basket(basket)
                .skuId(request.skuId())
                .productLabel(request.productLabel().trim())
                .units(request.units())
                .unitAmount(normalizedUnitAmount)
                .build());

        BasketLine storedLine = basketLineRepository.save(line);
        basket.touch();
        basketRepository.save(basket);

        return toLineResponse(storedLine);
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

    private BasketLineResponse toLineResponse(BasketLine line) {
        return new BasketLineResponse(
            line.getId(),
            line.getBasket().getId(),
            line.getSkuId(),
            line.getProductLabel(),
            line.getUnits(),
            line.getUnitAmount(),
            line.getLineAmount()
        );
    }
}
