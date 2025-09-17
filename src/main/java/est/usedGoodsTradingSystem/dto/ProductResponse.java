package est.usedGoodsTradingSystem.dto;

import est.usedGoodsTradingSystem.domain.Product;
import est.usedGoodsTradingSystem.domain.User;
import est.usedGoodsTradingSystem.domain.enums.ProductStatus;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private Long productId;
    private String title;
    private Long price;
    private ProductStatus status;

    public ProductResponse(Product product) {
        this.productId = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.status = product.getStatus();
    }
}
