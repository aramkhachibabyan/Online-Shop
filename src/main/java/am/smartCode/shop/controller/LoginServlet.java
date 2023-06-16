package am.smartCode.shop.controller;

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
import java.io.PrintWriter;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        UserRepository userRepository = new UserRepositoryImpl(databaseConnection);
        UserService userService = new UserServiceImpl(userRepository);
        try {
            userService.login(email, password);
            resp.sendRedirect("index.html");
        } catch (Exception e) {
            resp.getWriter().write("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Login Page</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "\n" +
                    "    <h2>Wrong email or password</h2>\n" +
                    "    <form method=\"post\" action=\"/login\">        \n" +
                    "            email: <input type=\"text\" name=\"email\"/><br><br>\n" +
                    "            password: <input type=\"password\" name=\"password\"/><br><br>\n" +
                    "            <input type=\"submit\"/>\n" +
                    "    </form>\n" +
                    "</body>\n" +
                    "</html>");
        }


    }
}
