package est.usedGoodsTradingSystem.domain;

import est.usedGoodsTradingSystem.domain.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product")
    private Product product;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    private LocalDateTime changedAt;

//    public void setProduct(Product product) {
//        this.product = product;
//    }

}
