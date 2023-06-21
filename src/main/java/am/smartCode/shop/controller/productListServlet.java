package am.smartCode.shop.controller;

import am.smartCode.shop.model.Product;
import am.smartCode.shop.repository.product.impl.ProductRepositoryimpl;
import am.smartCode.shop.service.product.ProductService;
import am.smartCode.shop.service.product.impl.ProductServiceImpl;
import am.smartCode.shop.util.DatabaseConnection;
import org.w3c.dom.html.HTMLTableCaptionElement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class productListServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService productService = new ProductServiceImpl(new ProductRepositoryimpl(DatabaseConnection.getInstance()));
        try {
            List<Product> allProducts = productService.getAllProducts();
            req.setAttribute("list",allProducts);
            req.getRequestDispatcher("/product.jsp").forward(req,resp);
        } catch (Exception e) {
            req.setAttribute("message",e.getMessage());
            req.getRequestDispatcher("/product.jsp").forward(req,resp);
        }
    }
}
