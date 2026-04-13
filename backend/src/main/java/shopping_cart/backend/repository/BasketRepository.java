package shopping_cart.backend.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopping_cart.backend.entity.Basket;
import shopping_cart.backend.entity.BasketState;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {

    Optional<Basket> findFirstByShopperIdAndState(Long shopperId, BasketState state);
}
