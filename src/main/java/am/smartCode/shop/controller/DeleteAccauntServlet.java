package am.smartCode.shop.controller;

import am.smartCode.shop.repository.user.impl.UserRepositoryImpl;
import am.smartCode.shop.service.user.UserService;
import am.smartCode.shop.service.user.impl.UserServiceImpl;
import am.smartCode.shop.util.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteAccauntServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email =req.getParameter("email");
        String password = req.getParameter("password");
        UserService userService = new UserServiceImpl(new UserRepositoryImpl(DatabaseConnection.getInstance()));
        try {
            userService.deleteUser(email,password);
            resp.sendRedirect("/index.jsp");
        } catch (Exception e) {
            req.setAttribute("email",email);
            req.setAttribute("message",e.getMessage());
            req.getRequestDispatcher("/deleteAccaunt.jsp").forward(req,resp);
        }
    }
}
