package shopping_cart.backend.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopping_cart.backend.entity.BasketLine;

@Repository
public interface BasketLineRepository extends JpaRepository<BasketLine, Long> {

    Optional<BasketLine> findFirstByBasketIdAndSkuId(Long basketId, Long skuId);

    List<BasketLine> findAllByBasketIdOrderByIdAsc(Long basketId);
}
