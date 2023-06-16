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

public class getProductServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idstr = req.getParameter("id");
        long id = Long.parseLong(idstr);
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        ProductRepository productRepository = new ProductRepositoryimpl(databaseConnection);
        ProductService productService = new ProductServiceImpl(productRepository);
        try {
            String product = productService.getProduct(id).toString();
            resp.getWriter().write("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Product you are searching...</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    product +
                    "\n" +
                    "<br><a href=\"http://localhost:8080/product.html\">Product Page</a><br><br>" +
                    "\n" +
                    "</body>\n" +
                    "</html>");
        } catch (Exception e) {
            resp.sendRedirect("/getProduct");
        }
    }
}
