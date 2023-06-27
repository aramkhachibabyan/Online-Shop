package am.smartCode.shop.controller;

import am.smartCode.shop.model.User;
import am.smartCode.shop.repository.user.UserRepository;
import am.smartCode.shop.repository.user.impl.UserRepositoryImpl;
import am.smartCode.shop.repository.user.impl.UserRepositoryJpaImpl;
import am.smartCode.shop.service.user.UserService;
import am.smartCode.shop.service.user.impl.UserServiceImpl;
import am.smartCode.shop.service.user.impl.UserServiceJpaImpl;
import am.smartCode.shop.util.DatabaseConnection;
import am.smartCode.shop.util.constants.Keyword;
import am.smartCode.shop.util.constants.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter(Keyword.NAME);
        String lastname = req.getParameter(Keyword.LASTNAME);
        String email = req.getParameter(Keyword.EMAIL);
        String password = req.getParameter(Keyword.PASSWORD);
        String agestr = req.getParameter(Keyword.AGE);
        int age = 0;
        try {
            age = Integer.parseInt(agestr);
        } catch (Exception ignored) {
        }
        String balancestr = req.getParameter(Keyword.BALANCE);
        long balance = 0;
        try {
            balance = Long.parseLong(balancestr);
        } catch (Exception ignored) {
        }
        UserService userService = new UserServiceJpaImpl(new UserRepositoryJpaImpl());
        try {
            userService.register(name, lastname, email, password, age, balance);
            HttpSession session = req.getSession();
            session.setAttribute(Keyword.EMAIL, email);
            req.getRequestDispatcher(Path.HOME_PAGE).forward(req,resp);
        } catch (Exception e) {
            req.setAttribute(Keyword.MESSAGE, e.getMessage());
            req.getRequestDispatcher(Path.REGISTER).forward(req, resp);
        }


    }
}
