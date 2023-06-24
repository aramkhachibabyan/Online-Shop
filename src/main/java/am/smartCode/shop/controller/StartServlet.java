package am.smartCode.shop.controller;

import am.smartCode.shop.repository.user.impl.UserRepositoryImpl;
import am.smartCode.shop.service.user.UserService;
import am.smartCode.shop.service.user.impl.UserServiceImpl;
import am.smartCode.shop.util.CookieUtil;
import am.smartCode.shop.util.DatabaseConnection;
import am.smartCode.shop.util.constants.Keyword;
import am.smartCode.shop.util.constants.Path;
import am.smartCode.shop.util.encoder.AESManager;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.security.Key;

public class StartServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String encodedString = CookieUtil.getCookieValueByName(req.getCookies(), Keyword.REMEMBER);
        UserService userService = new UserServiceImpl(new UserRepositoryImpl(DatabaseConnection.getInstance()));
        try {
            if (encodedString != null) {
                String remember = AESManager.decrypt(encodedString);
                String email = remember.split(":")[0];
                String password = remember.split(":")[1];
                userService.login(email, password);
                Cookie cookie = new Cookie(Keyword.REMEMBER, AESManager.encrypt(email + ":" + password));
                cookie.setMaxAge(360000);
                resp.addCookie(cookie);

                HttpSession session = req.getSession();
                session.setAttribute(Keyword.EMAIL, email);
                req.getRequestDispatcher(Path.HOME_PAGE).forward(req, resp);
            }
            else {
                resp.sendRedirect(Path.LOGIN);
            }
        } catch (Exception e) {
            req.setAttribute(Keyword.MESSAGE, e.getMessage());
            resp.setStatus(400);
            req.getRequestDispatcher(Path.LOGIN).forward(req, resp);
        }
    }
}
