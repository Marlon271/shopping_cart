package shopping_cart.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shopping_cart.backend.entity.Basket;
import shopping_cart.backend.entity.BasketState;
import shopping_cart.backend.repository.BasketRepository;

@ExtendWith(MockitoExtension.class)
class BasketServiceTest {

    @Mock
    private BasketRepository basketRepository;

    private BasketService basketService;

    @BeforeEach
    void setUp() {
        basketService = new BasketService(basketRepository);
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
}
