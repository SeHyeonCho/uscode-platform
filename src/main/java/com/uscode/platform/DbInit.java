package com.uscode.platform;

import com.uscode.platform.order.Order;
import com.uscode.platform.order.OrderService;
import com.uscode.platform.product.Product;
import com.uscode.platform.product.ProductService;
import com.uscode.platform.user.User;
import com.uscode.platform.user.UserService;
import com.uscode.platform.user.dto.UserCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class DbInit implements ApplicationRunner {

    private final UserService userService;
    private final ProductService productService;
    private final OrderService orderService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String url = "/home/yun777567/images/";

        userService.register(new UserCreateDto("김말자", "rlaakfwk@naver.com", "1234"));

        User user1 = userService.findByName("김말자");
        user1.changeSeller();

        userService.register(new UserCreateDto("강춘삼", "rkdcnstka@naver.com", "1234"));
        User user2 = userService.findByName("강춘삼");
        user2.changeSeller();

        userService.register(new UserCreateDto("윤혜숙", "dbsgPtnr@naver.com", "1234"));
        User user3 = userService.findByName("윤혜숙");
        user3.changeSeller();

        userService.register(new UserCreateDto("조세현", "whtpgus@naver.com", "1234"));
        User user4 = userService.findByName("조세현");
        user4.updateInfo("조세현", "010-1111-1111", "경기도 광주시 오포로 73-21");


        Long productId1 = productService.save(Product.of(user1, "/home/yun777567/images/1", "말자네 자두", 15000L, "제가.직접.키운자두.입니다.\n" +
                "농사만.어언.27년이니.믿고드세오.^^\n"));

        Product product1 = productService.findById(productId1);

        Long productId2 = productService.save(Product.of(user2, "/home/yun777567/images/2", "의성 으뜸 마늘", 9500L, "손수 까서 드립니다.\n" +
                "최상급 마늘만 취급합니다.\n" +
                "믿고 사세요." +
                "농사만.어언.27년이니.믿고드세오.^^\n"));

        Product product2 = productService.findById(productId2);

        Long productId3 = productService.save(Product.of(user2, "/home/yun777567/images/3", "강춘삼 마늘",
                8900L, "평생을 마늘만 했습니다.\n" +
                        "믿고 사가세요."));

        Long productId4 = productService.save(Product.of(user3, "/home/yun777567/images/4", "진짜배기 의성 사과",
                13000L, "저희는 농약 안쓰는 친환경 사과입니다.\n" +
                        "깨끗하니 물로 살짝 행궈 그냥 드세요."));


        orderService.createOrder(Order.of(product1, user4, 2, product1.getPrice() * 2, user4.getAddress()));
        orderService.createOrder(Order.of(product2, user4, 3, product2.getPrice() * 2, user4.getAddress()));
    }
}
