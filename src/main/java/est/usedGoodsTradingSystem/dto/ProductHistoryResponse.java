package est.usedGoodsTradingSystem.dto;

import est.usedGoodsTradingSystem.domain.ProductHistory;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ProductHistoryResponse {
    private Long id;
    private String status;
    private LocalDateTime changedAt;

    public ProductHistoryResponse(ProductHistory history) {
        this.id = history.getId();
        this.status = history.getStatus().name();  // enum → 문자열
        this.changedAt = history.getChangedAt();
    }
}
