package am.smartCode.shop.controller;

import am.smartCode.shop.util.CookieUtil;
import am.smartCode.shop.util.constants.Keyword;
import am.smartCode.shop.util.constants.Path;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie remember = CookieUtil.getCookieByName(req.getCookies(), Keyword.REMEMBER);
        remember.setMaxAge(0);
        resp.addCookie(remember);

        req.getSession().invalidate();
        req.getRequestDispatcher(Path.LOGIN).forward(req, resp);
    }
}
