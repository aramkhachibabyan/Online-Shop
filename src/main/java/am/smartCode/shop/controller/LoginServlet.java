package am.smartCode.shop.controller;

import am.smartCode.shop.repository.user.UserRepository;
import am.smartCode.shop.repository.user.impl.UserRepositoryImpl;
import am.smartCode.shop.repository.user.impl.UserRepositoryJpaImpl;
import am.smartCode.shop.service.user.UserService;
import am.smartCode.shop.service.user.impl.UserServiceImpl;
import am.smartCode.shop.service.user.impl.UserServiceJpaImpl;
import am.smartCode.shop.util.DatabaseConnection;
import am.smartCode.shop.util.constants.Keyword;
import am.smartCode.shop.util.constants.Path;
import am.smartCode.shop.util.encoder.AESManager;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String email = req.getParameter(Keyword.EMAIL);
        String password = req.getParameter(Keyword.PASSWORD);
        String rememberMe = req.getParameter(Keyword.REMEMBER_ME);
        UserService userService = new UserServiceJpaImpl(new UserRepositoryJpaImpl());
        try {
            userService.login(email, password);
            if (rememberMe != null) {
                Cookie cookie = new Cookie(Keyword.REMEMBER, AESManager.encrypt(email + ":" + password));
                cookie.setMaxAge(360000);
                resp.addCookie(cookie);
            }
            HttpSession session = req.getSession();
            session.setAttribute(Keyword.EMAIL, email);
            req.getRequestDispatcher(Path.HOME_PAGE).forward(req, resp);
        } catch (Exception e) {
            req.setAttribute(Keyword.MESSAGE, e.getMessage());
            req.getRequestDispatcher(Path.LOGIN).forward(req, resp);
        }


    }
}
