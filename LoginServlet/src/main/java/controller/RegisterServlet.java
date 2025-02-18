package controller;

import model.DatabaseConnection;
import util.EncryptionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
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
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Clear old data
        req.getSession().removeAttribute("usernameValue");
        req.getSession().removeAttribute("passwordValue");
        req.getSession().removeAttribute("usernameError");
        req.getSession().removeAttribute("passwordError");
        req.getSession().removeAttribute("error");

        req.getSession().setAttribute("usernameValue", username);
        req.getSession().setAttribute("passwordValue", password);

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
            resp.sendRedirect("register");
            return;
        }

        try {
            String encryptedPassword = EncryptionUtil.encrypt(password);
            String sql = "INSERT INTO user (username, password) VALUES (?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, encryptedPassword);
                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    req.getSession().removeAttribute("error");
                    req.getRequestDispatcher("login.jsp").forward(req, resp);
                }
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) { // Duplicate entry error code for MySQL
                req.getSession().setAttribute("usernameError", "❌ Username already exists.");
            } else {
                req.getSession().setAttribute("error", "❌ SQL Error: " + e.getMessage());
            }
            resp.sendRedirect("register");
        } catch (Exception e) {
            e.printStackTrace();
            req.getSession().setAttribute("error", "❌ Registration failed. Please try again.");
            resp.sendRedirect("register");
        }
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