package am.smartCode.shop.controller;

import am.smartCode.shop.repository.product.ProductRepository;
import am.smartCode.shop.repository.product.impl.ProductRepositoryimpl;
import am.smartCode.shop.service.product.ProductService;
import am.smartCode.shop.service.product.impl.ProductServiceImpl;
import am.smartCode.shop.util.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class createProductServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String category = req.getParameter("category");
        String name = req.getParameter("name");
        String publishedYear = req.getParameter("publishedYear");
        String pricestr = req.getParameter("price");
        long price = Long.parseLong(pricestr);
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        ProductRepository productRepository = new ProductRepositoryimpl(databaseConnection);
        ProductService productService = new ProductServiceImpl(productRepository);
        try {
            productService.createProduct(category,name,publishedYear,price);
            resp.sendRedirect("/product.html");
        } catch (Exception e) {
            resp.sendRedirect("/createProduct");
        }
    }
}
