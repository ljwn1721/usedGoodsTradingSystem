package est.usedGoodsTradingSystem.service;

import est.usedGoodsTradingSystem.domain.Product;
import est.usedGoodsTradingSystem.domain.ProductHistory;
import est.usedGoodsTradingSystem.domain.User;
import est.usedGoodsTradingSystem.domain.enums.ProductStatus;
import est.usedGoodsTradingSystem.dto.ProductRegisterRequest;
import est.usedGoodsTradingSystem.dto.ProductResponse;
import est.usedGoodsTradingSystem.repository.ProductHistoryRepository;
import est.usedGoodsTradingSystem.repository.ProductRepository;
import est.usedGoodsTradingSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ProductResponse registerProduct(Long userId, ProductRegisterRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Product product = Product.builder()
                .title(request.getTitle())
                .price(request.getPrice())
                .status(ProductStatus.SALE)
                .build();
        user.addProduct(product);

        ProductHistory history = ProductHistory.builder()
                .status(ProductStatus.SALE)
                .changedAt(LocalDateTime.now())
                .build();
        product.addHistory(history);

        return new ProductResponse(productRepository.save(product));
    }

    public List<ProductResponse> getUserProducts(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // User.products → List<Product>
        return user.getProducts().stream()
                .map(ProductResponse::new)
                .toList();
    }

}
