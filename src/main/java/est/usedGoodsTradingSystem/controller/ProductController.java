package est.usedGoodsTradingSystem.controller;

import est.usedGoodsTradingSystem.dto.ProductBuyRequest;
import est.usedGoodsTradingSystem.dto.ProductHistoryResponse;
import est.usedGoodsTradingSystem.dto.ProductResponse;
import est.usedGoodsTradingSystem.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/{productId}/buy")
    public ResponseEntity<ProductResponse> buyProduct(
            @PathVariable Long productId,
            @RequestBody ProductBuyRequest request
    ){
        ProductResponse response = productService.buyProduct(productId, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{productId}/history")
    public ResponseEntity<List<ProductHistoryResponse>> getProductHistory(
            @PathVariable Long productId) {
        List<ProductHistoryResponse> historyList = productService.getProductHistory(productId);
        return ResponseEntity.ok(historyList);
    }
}
