package com.uscode.platform.order;

import com.uscode.platform.order.dto.OrderCreateDto;
import com.uscode.platform.order.dto.OrderListDto;
import com.uscode.platform.order.dto.OrderStatusDto;
import com.uscode.platform.product.Product;
import com.uscode.platform.product.ProductService;
import com.uscode.platform.user.User;
import com.uscode.platform.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/orders")
public class OrderController {

    private final OrderService orderService;
    private final ProductService productService;
    private final UserService userService;
    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody @Valid OrderCreateDto dto) {
        Product product = productService.findById(dto.getProductId());
        User user = userService.findByName(dto.getUsername());
        orderService.createOrder(Order.of(product, user, dto.getQuantity(), dto.getTotalPrice(), dto.getAddress()));
        return ResponseEntity.ok("주문이 생성되었습니다.");
    }

    //구매자 기준 조회
    @GetMapping("/buyer/{userId}")
    public OrderListDto getBuyerOrderList(@PathVariable Long userId) {
        List<Order> userOrderList = orderService.getUserOrderList(userId);
        return new OrderListDto(userOrderList);

    }

    @PostMapping("/{orderId}")
    public ResponseEntity<Void> changeOrderStatus(@RequestBody @Valid OrderStatusDto dto) {
        orderService.changeState(dto);
        return ResponseEntity.ok().build();
    }

    //판매자 기준 조회
    @GetMapping("/seller/{sellerId}")
    public OrderListDto getSellerOrderList(@PathVariable Long sellerId) {
        List<Order> userOrderList = orderService.getSellerOrderList(sellerId);
        return new OrderListDto(userOrderList);
    }







}
