package est.usedGoodsTradingSystem.domain;

import est.usedGoodsTradingSystem.domain.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Long price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller")
    private User seller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id")
    private User buyer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ProductStatus status;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ProductHistory> histories = new ArrayList<>();

    public void addHistory(ProductHistory history) {
        histories.add(history);
        history.setProduct(this);
    }

//    public void setSeller(User seller) {
//        this.seller = seller;
//    }

    public void changeStatus(ProductStatus status) {
        this.status = status;
        ProductHistory history = ProductHistory.builder()
                .product(this)
                .status(status)
                .changedAt(LocalDateTime.now())
                .build();
        addHistory(history);
    }

}
