package est.usedGoodsTradingSystem.service;

import est.usedGoodsTradingSystem.domain.Product;
import est.usedGoodsTradingSystem.domain.User;
import est.usedGoodsTradingSystem.domain.enums.ProductStatus;
import est.usedGoodsTradingSystem.dto.ProductBuyRequest;
import est.usedGoodsTradingSystem.dto.ProductHistoryResponse;
import est.usedGoodsTradingSystem.dto.ProductResponse;
import est.usedGoodsTradingSystem.repository.ProductRepository;
import est.usedGoodsTradingSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Transactional
    public ProductResponse buyProduct(Long productId, ProductBuyRequest request){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        if (product.getStatus() != ProductStatus.SALE) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Product is not available for sale");
        }

        User buyer = userRepository.findById(request.getBuyerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Buyer not found"));

        product.setBuyer(buyer);
        product.changeStatus(ProductStatus.SOLD);

        return new ProductResponse(productRepository.save(product));
    }

    public List<ProductHistoryResponse> getProductHistory(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        return product.getHistories().stream()
                .map(ProductHistoryResponse::new)
                .toList();
    }

}
