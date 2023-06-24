package am.smartCode.shop.controller;

import am.smartCode.shop.repository.user.impl.UserRepositoryImpl;
import am.smartCode.shop.service.user.UserService;
import am.smartCode.shop.service.user.impl.UserServiceImpl;
import am.smartCode.shop.util.CookieUtil;
import am.smartCode.shop.util.DatabaseConnection;
import am.smartCode.shop.util.constants.Keyword;
import am.smartCode.shop.util.constants.Path;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteAccauntServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = (String) req.getSession().getAttribute(Keyword.EMAIL);
        String password = req.getParameter(Keyword.PASSWORD);
        UserService userService = new UserServiceImpl(new UserRepositoryImpl(DatabaseConnection.getInstance()));
        try {
            userService.deleteUser(email, password);
            Cookie remember = CookieUtil.getCookieByName(req.getCookies(), Keyword.REMEMBER);
            if (remember != null) {
                remember.setMaxAge(0);
                resp.addCookie(remember);
            }
            req.getSession().invalidate();
            resp.sendRedirect(Path.LOGIN);
        } catch (Exception e) {
            req.setAttribute(Keyword.MESSAGE, e.getMessage());
            req.getRequestDispatcher(Path.DELETE_ACCOUNT).forward(req, resp);
        }
    }
}
