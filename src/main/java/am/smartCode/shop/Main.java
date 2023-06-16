package am.smartCode.shop;

import am.smartCode.shop.*;
import am.smartCode.shop.repository.order.OrderRepository;
import am.smartCode.shop.repository.order.impl.OrderRepositoryImpl;
import am.smartCode.shop.repository.product.ProductRepository;
import am.smartCode.shop.repository.product.impl.ProductRepositoryimpl;
import am.smartCode.shop.repository.user.UserRepository;
import am.smartCode.shop.repository.user.impl.UserRepositoryImpl;
import am.smartCode.shop.service.order.OrderService;
import am.smartCode.shop.service.order.impl.OrderServiceImpl;
import am.smartCode.shop.service.product.ProductService;
import am.smartCode.shop.service.product.impl.ProductServiceImpl;
import am.smartCode.shop.service.user.UserService;
import am.smartCode.shop.service.user.impl.UserServiceImpl;
import am.smartCode.shop.util.DatabaseConnection;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {
//        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
//
//        UserRepository userRepository = new UserRepositoryImpl(databaseConnection);
//        UserService userService = new UserServiceImpl(userRepository);
////        userService.register("Aghasi", "Khachatryan", "qweqweqw1@mail.ru", "pass1", 27, 3000000L);
////        userService.register("Arayik", "Harutyunyan", "uefewf2@mail.ru", "pass2", 29, 1000000L);
////        userService.register("Eduard", "Parunakyan", "ussssssde3@mail.ru", "pass3", 23, 1500000L);
////        userService.register("Vazgen", "Sahakyan", "uasfage4@mail.ru", "pass4", 30, 800000L);
////        userService.register("Abgar", "Hakobyan", "u54hgth5@mail.ru", "pass5", 18, 1200000L);
////        userService.register("Artak", "Shahinyan", "artshah5@mail.ru", "pass6", 55, 900000L);
//
//        userService.login("qweqweqw1@mail.ru", "pass1");
//        userService.login("uefewf2@mail.ru", "pass2");
//
//        ProductRepository productRepository = new ProductRepositoryimpl(databaseConnection);
//        ProductService productService = new ProductServiceImpl(productRepository);
///*        productService.createProduct("Phone", "Iphone 14", "12/09/2022", 400000);
//        productService.createProduct("Phone", "Samsung Galaxy S22", "19/03/2022", 396000);
//        productService.createProduct("Tv", "LG OLED65C2", "11/02/2022", 780000);
//        productService.createProduct("Tv", "Samsung UE43AU7100", "30/12/2022", 720000);
//        productService.createProduct("Laptop", "Apple MacBook Air", "24/03/2022", 1240000);
//        productService.createProduct("Laptop", "HP Spectre x360 14", "27/07/2022", 790000);*/
//
//        OrderRepository orderRepository = new OrderRepositoryImpl(databaseConnection);
//        OrderService orderService = new OrderServiceImpl(orderRepository, productRepository, userRepository);
//        /*orderService.createOrder(1L,2L,1);
//        orderService.createOrder(2L,3L,1);
//        orderService.createOrder(3L,5L,1);
//        orderService.createOrder(1L,1L,1);*/

    }
}