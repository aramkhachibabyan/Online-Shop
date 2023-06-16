package am.smartCode.shop.controller;

import am.smartCode.shop.model.User;
import am.smartCode.shop.repository.user.UserRepository;
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

public class RegisterServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String agestr = req.getParameter("age");
        int age = Integer.parseInt(agestr);
        String balancestr = req.getParameter("balance");
        long balance = Long.parseLong(balancestr);
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        UserRepository userRepository = new UserRepositoryImpl(databaseConnection);
        UserService userService = new UserServiceImpl(userRepository);
        try {
            userService.register(name,lastname,email,password,age,balance);
        } catch (SQLException e) {
            resp.sendRedirect("register.html");
        }
        resp.sendRedirect("/login");

    }
}
