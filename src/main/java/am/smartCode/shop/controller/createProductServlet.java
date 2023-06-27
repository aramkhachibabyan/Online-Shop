package am.smartCode.shop.controller;

import am.smartCode.shop.repository.product.ProductRepository;
import am.smartCode.shop.repository.product.impl.ProductRepositoryJpaImpl;
import am.smartCode.shop.repository.product.impl.ProductRepositoryimpl;
import am.smartCode.shop.service.product.ProductService;
import am.smartCode.shop.service.product.impl.ProductServiceImpl;
import am.smartCode.shop.service.product.impl.ProductServiceJpaImpl;
import am.smartCode.shop.util.DatabaseConnection;
import am.smartCode.shop.util.constants.Keyword;
import am.smartCode.shop.util.constants.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class createProductServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String category = req.getParameter(Keyword.CATEGORY);
        String name = req.getParameter(Keyword.NAME);
        String publishedYear = req.getParameter(Keyword.PUBLISHED_YEAR);
        String pricestr = req.getParameter(Keyword.PRICE);
        long price = 0;
        try {
            price = Long.parseLong(pricestr);
        } catch (Exception ignored) {
        }
        ProductService productService = new ProductServiceJpaImpl(new ProductRepositoryJpaImpl());
        try {
            productService.createProduct(category, name, publishedYear, price);
            req.setAttribute("name", name);
            req.getRequestDispatcher(Path.CREATE_PRODUCT).forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher(Path.CREATE_PRODUCT).forward(req, resp);

        }
    }
}
