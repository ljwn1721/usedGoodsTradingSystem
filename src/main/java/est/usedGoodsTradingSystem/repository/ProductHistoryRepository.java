package est.usedGoodsTradingSystem.repository;

import est.usedGoodsTradingSystem.domain.ProductHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductHistoryRepository extends JpaRepository<ProductHistory,Long> {
}
