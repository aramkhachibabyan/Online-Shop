package am.smartCode.shop.controller;

import am.smartCode.shop.repository.user.UserRepository;
import am.smartCode.shop.repository.user.impl.UserRepositoryImpl;
import am.smartCode.shop.service.user.UserService;
import am.smartCode.shop.service.user.impl.UserServiceImpl;
import am.smartCode.shop.util.DatabaseConnection;
import am.smartCode.shop.util.constants.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UserService userService = new UserServiceImpl(new UserRepositoryImpl(DatabaseConnection.getInstance()));
        try {
            userService.login(email, password);
            HttpSession session = req.getSession();
            session.setAttribute("email", email);
            req.getRequestDispatcher(Path.HOME_PAGE).forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher(Path.LOGIN).forward(req, resp);
        }


    }
}
