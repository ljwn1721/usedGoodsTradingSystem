package est.usedGoodsTradingSystem.controller;

import est.usedGoodsTradingSystem.dto.ProductRegisterRequest;
import est.usedGoodsTradingSystem.dto.ProductResponse;
import est.usedGoodsTradingSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/{userId}/products")
    public ResponseEntity<ProductResponse> registerProduct(
            @PathVariable Long userId, @RequestBody ProductRegisterRequest request
    ){
        ProductResponse response = userService.registerProduct(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{userId}/products")
    public ResponseEntity<List<ProductResponse>> getUserProducts(@PathVariable Long userId) {
        List<ProductResponse> products = userService.getUserProducts(userId);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

}
