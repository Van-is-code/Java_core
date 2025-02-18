package controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DatabaseConnection;
import model.User;
import util.EncryptionUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WebAppServlet extends HttpServlet {
    public String getUsernameFromSessionOrCookie(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        String username = (session != null) ? (String) session.getAttribute("username") : null;

        if (username == null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("username".equals(cookie.getName())) {
                        username = cookie.getValue();
                        if (session == null) {
                            session = request.getSession(true);
                        }
                        session.setAttribute("username", username);
                        break;
                    }
                }
            }
        }

        if (username == null) {
            response.sendRedirect("login.jsp");
        }

        return username;
    }

    public User getUserData(String username) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) {
            return null;
        }

        String sql = "SELECT * FROM user WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setUsername(rs.getString("username"));
                    try {
                        user.setPassword(EncryptionUtil.decrypt(rs.getString("password")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // Add other user fields as needed
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}