package shopping_cart.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shopping_cart.backend.dto.BasketLineResponse;
import shopping_cart.backend.dto.RegisterBasketLineRequest;
import shopping_cart.backend.entity.Basket;
import shopping_cart.backend.entity.BasketLine;
import shopping_cart.backend.entity.BasketState;
import shopping_cart.backend.repository.BasketLineRepository;
import shopping_cart.backend.repository.BasketRepository;

@ExtendWith(MockitoExtension.class)
class BasketServiceTest {

    @Mock
    private BasketRepository basketRepository;

    @Mock
    private BasketLineRepository basketLineRepository;

    private BasketService basketService;

    @BeforeEach
    void setUp() {
        basketService = new BasketService(basketRepository, basketLineRepository);
    }

    @Test
    void shouldCreateBasketWhenShopperHasNoOpenBasket() {
        Basket storedBasket = Basket.builder()
            .id(1L)
            .shopperId(240L)
            .state(BasketState.OPEN)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();

        when(basketRepository.findFirstByShopperIdAndState(240L, BasketState.OPEN)).thenReturn(Optional.empty());
        when(basketRepository.save(any(Basket.class))).thenReturn(storedBasket);

        BasketOpeningResult result = basketService.openBasket(240L);

        assertTrue(result.created());
        assertEquals(1L, result.basket().basketId());
        assertEquals(240L, result.basket().shopperId());
        assertEquals("OPEN", result.basket().state());
        verify(basketRepository).save(any(Basket.class));
    }

    @Test
    void shouldReuseBasketWhenShopperAlreadyHasOneOpen() {
        Basket existingBasket = Basket.builder()
            .id(8L)
            .shopperId(999L)
            .state(BasketState.OPEN)
            .createdAt(LocalDateTime.now().minusMinutes(12))
            .updatedAt(LocalDateTime.now().minusMinutes(2))
            .build();

        when(basketRepository.findFirstByShopperIdAndState(999L, BasketState.OPEN))
            .thenReturn(Optional.of(existingBasket));

        BasketOpeningResult result = basketService.openBasket(999L);

        assertFalse(result.created());
        assertEquals(8L, result.basket().basketId());
        assertEquals(999L, result.basket().shopperId());
    }

    @Test
    void shouldCreateNewLineWhenSkuDoesNotExistInBasket() {
        Basket basket = Basket.builder()
            .id(50L)
            .shopperId(321L)
            .state(BasketState.OPEN)
            .createdAt(LocalDateTime.now().minusMinutes(10))
            .updatedAt(LocalDateTime.now().minusMinutes(1))
            .build();

        RegisterBasketLineRequest request = new RegisterBasketLineRequest(
            9001L,
            "Monitor 27 pulgadas",
            2,
            new BigDecimal("850000")
        );

        BasketLine storedLine = BasketLine.builder()
            .id(70L)
            .basket(basket)
            .skuId(9001L)
            .productLabel("Monitor 27 pulgadas")
            .units(2)
            .unitAmount(new BigDecimal("850000.00"))
            .lineAmount(new BigDecimal("1700000.00"))
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();

        when(basketRepository.findById(50L)).thenReturn(Optional.of(basket));
        when(basketLineRepository.findFirstByBasketIdAndSkuId(50L, 9001L)).thenReturn(Optional.empty());
        when(basketLineRepository.save(any(BasketLine.class))).thenReturn(storedLine);
        when(basketRepository.save(any(Basket.class))).thenReturn(basket);

        BasketLineResponse response = basketService.registerLine(50L, request);

        assertEquals(70L, response.lineId());
        assertEquals(50L, response.basketId());
        assertEquals(2, response.units());
        assertEquals(new BigDecimal("1700000.00"), response.lineAmount());
    }

    @Test
    void shouldAppendUnitsWhenSkuAlreadyExistsInBasket() {
        Basket basket = Basket.builder()
            .id(51L)
            .shopperId(654L)
            .state(BasketState.OPEN)
            .createdAt(LocalDateTime.now().minusMinutes(15))
            .updatedAt(LocalDateTime.now().minusMinutes(5))
            .build();

        BasketLine existingLine = BasketLine.builder()
            .id(71L)
            .basket(basket)
            .skuId(44L)
            .productLabel("Mouse ergonomico")
            .units(1)
            .unitAmount(new BigDecimal("120000.00"))
            .lineAmount(new BigDecimal("120000.00"))
            .createdAt(LocalDateTime.now().minusMinutes(10))
            .updatedAt(LocalDateTime.now().minusMinutes(2))
            .build();

        RegisterBasketLineRequest request = new RegisterBasketLineRequest(
            44L,
            "Mouse ergonomico",
            3,
            new BigDecimal("120000.00")
        );

        when(basketRepository.findById(51L)).thenReturn(Optional.of(basket));
        when(basketLineRepository.findFirstByBasketIdAndSkuId(51L, 44L)).thenReturn(Optional.of(existingLine));
        when(basketLineRepository.save(existingLine)).thenReturn(existingLine);
        when(basketRepository.save(any(Basket.class))).thenReturn(basket);

        BasketLineResponse response = basketService.registerLine(51L, request);

        assertEquals(4, response.units());
        assertEquals(new BigDecimal("480000.00"), response.lineAmount());
        verify(basketLineRepository).save(existingLine);
    }
}
