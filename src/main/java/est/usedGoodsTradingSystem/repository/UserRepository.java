package est.usedGoodsTradingSystem.repository;

import est.usedGoodsTradingSystem.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
