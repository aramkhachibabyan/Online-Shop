package am.smartCode.shop.controller;

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
import java.io.IOException;
import java.sql.SQLException;

public class ChangePasswordServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = (String)req.getSession().getAttribute(Keyword.EMAIL);
        String newPassword = req.getParameter(Keyword.NEW_PASSWORD);
        String repeatPassword = req.getParameter(Keyword.REPEAT_PASSWORD);
        UserService userService = new UserServiceJpaImpl(new UserRepositoryJpaImpl());
        try {
            userService.updateUser(email,newPassword,repeatPassword);
            resp.sendRedirect(Path.HOME_PAGE);
        } catch (Exception e) {
            req.setAttribute(Keyword.MESSAGE,e.getMessage());
            req.getRequestDispatcher(Path.CHANGE_PASSWORD).forward(req,resp);
        }
    }
}
