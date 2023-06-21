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

public class deleteProductServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idstr = req.getParameter("id");
        long id=0;
        try {
            id = Long.parseLong(idstr);
        } catch (Exception ignored) {
        }
        ProductService productService = new ProductServiceImpl(new ProductRepositoryimpl(DatabaseConnection.getInstance()));
        try {
            productService.deleteProduct(id);
            req.setAttribute("id",id);
            req.getRequestDispatcher("/deleteProduct.jsp").forward(req,resp);
        } catch (Exception e) {
            req.setAttribute("message",e.getMessage());
            req.getRequestDispatcher("/deleteProduct.jsp").forward(req,resp);
        }
    }
}
