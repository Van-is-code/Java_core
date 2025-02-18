<%
    String error = (String) session.getAttribute("error");
    String usernameError = (String) session.getAttribute("usernameError");
    String passwordError = (String) session.getAttribute("passwordError");
    String usernameValue = (String) session.getAttribute("usernameValue");
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login</h1>
<form action="login" method="post">
    <label for="username">Username:</label><br>
    <% if (usernameError != null) { %>
    <div class="error"><%= usernameError %></div>
    <% } %>
    <input type="text" id="username" name="username" value="<%= usernameValue != null ? usernameValue : "" %>"><br>
    <label for="password">Password:</label><br>
    <% if (passwordError != null) { %>
    <div class="error"><%= passwordError %></div>
    <% } %>
    <input type="password" id="password" name="password"><br>
    <% if (error != null) { %>
    <div class="error"><%= error %></div>
    <% } %>
    <br>
    <input type="checkbox" id="rememberMe" name="rememberMe">
    <label for="rememberMe">Remember Me</label><br><br>
    <button type="submit">Login</button>
    <a href="register.jsp">Register</a>
</form>
</body>
</html>
<%
    session.removeAttribute("usernameError");
    session.removeAttribute("passwordError");
    session.removeAttribute("usernameValue");
%>