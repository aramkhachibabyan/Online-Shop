package am.smartCode.shop.filter;

import am.smartCode.shop.util.constants.Keyword;
import am.smartCode.shop.util.constants.Path;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AuthorizationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String email =(String) httpRequest.getSession().getAttribute(Keyword.EMAIL);
        if (email == null){
            httpRequest.setAttribute(Keyword.MESSAGE,"Please login");
            httpResponse.setStatus(401);
            httpRequest.getRequestDispatcher(Path.LOGIN).forward(httpRequest,httpResponse);
        }else {
            filterChain.doFilter(httpRequest,httpResponse);
        }

    }

    @Override
    public void destroy() {
        System.out.println("Filter destroyed");
    }
}
