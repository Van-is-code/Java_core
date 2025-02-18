package controller;

import model.DatabaseConnection;
import util.EncryptionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private Connection connection;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            connection = DatabaseConnection.getConnection();
            if (connection == null) {
                throw new ServletException("Database connection is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Database connection error: " + e.getMessage(), e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String rememberMe = req.getParameter("rememberMe");

        // Clear old data
        req.getSession().removeAttribute("usernameValue");
        req.getSession().removeAttribute("usernameError");
        req.getSession().removeAttribute("passwordError");
        req.getSession().removeAttribute("error");

        req.getSession().setAttribute("usernameValue", username);

        boolean hasError = false;

        if (!isValidUsername(username)) {
            req.getSession().setAttribute("usernameError", "❌ Username must be at least 5 characters long");
            hasError = true;
        }

        if (!isValidPassword(password)) {
            req.getSession().setAttribute("passwordError", "❌ Password must be at least 8 characters long");
            hasError = true;
        }

        if (hasError) {
            resp.sendRedirect("login");
            return;
        }

        if (validateUser(username, password)) {
            req.getSession().removeAttribute("error");
            req.getSession().setAttribute("username", username);

            if ("on".equals(rememberMe)) {
                Cookie cookie = new Cookie("username", username);
                cookie.setMaxAge(60 * 60 * 24 * 30);
                resp.addCookie(cookie);
            }

            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } else {
            req.getSession().setAttribute("error", "❌ Invalid username or password");
            resp.sendRedirect("login");
        }
    }

    private boolean validateUser(String username, String password) {
        String query = "SELECT password FROM user WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String encryptedPassword = rs.getString("password");
                    String decryptedPassword = EncryptionUtil.decrypt(encryptedPassword);
                    return password.equals(decryptedPassword);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isValidUsername(String username) {
        return username != null && username.length() >= 5;
    }

    private boolean isValidPassword(String password) {
        return password != null && password.length() >= 8;
    }

    @Override
    public void destroy() {
        super.destroy();
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}