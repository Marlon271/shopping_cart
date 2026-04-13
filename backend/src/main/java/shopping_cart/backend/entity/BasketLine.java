package shopping_cart.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "basket_lines")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasketLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "basket_id", nullable = false)
    private Basket basket;

    @Column(name = "sku_id", nullable = false)
    private Long skuId;

    @Column(name = "product_label", nullable = false, length = 255)
    private String productLabel;

    @Column(nullable = false)
    private Integer units;

    @Column(name = "unit_amount", nullable = false, precision = 12, scale = 2)
    private BigDecimal unitAmount;

    @Column(name = "line_amount", nullable = false, precision = 12, scale = 2)
    private BigDecimal lineAmount;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
        recalculateLineAmount();
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
        recalculateLineAmount();
    }

    public void appendUnits(Integer extraUnits) {
        units += extraUnits;
        recalculateLineAmount();
    }

    public void refreshSnapshot(String nextLabel, BigDecimal nextUnitAmount) {
        productLabel = nextLabel.trim();
        unitAmount = normalize(nextUnitAmount);
        recalculateLineAmount();
    }

    public void recalculateLineAmount() {
        if (unitAmount != null && units != null) {
            lineAmount = normalize(unitAmount.multiply(BigDecimal.valueOf(units.longValue())));
        }
    }

    private BigDecimal normalize(BigDecimal amount) {
        return amount.setScale(2, RoundingMode.HALF_UP);
    }
}
