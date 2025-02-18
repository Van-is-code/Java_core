<%
    String error = (String) session.getAttribute("errorRegister");
    String usernameError = (String) session.getAttribute("usernameError");
    String passwordError = (String) session.getAttribute("passwordError");
    String usernameValue = (String) session.getAttribute("usernameValue");
    String passwordValue = (String) session.getAttribute("passwordValue");
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h1>Registration Form</h1>
<form action="register" method="post">
    <label for="username">Username:</label><br>
    <% if (usernameError != null) { %>
    <div class="error"><%= usernameError %></div>
    <% } %>
    <input type="text" id="username" name="username" value="<%= usernameValue != null ? usernameValue : "" %>"><br>
    <label for="password">Password:</label><br>
    <% if (passwordError != null) { %>
    <div class="error"><%= passwordError %></div>
    <% } %>
    <input type="password" id="password" name="password" value="<%= passwordValue != null ? passwordValue : "" %>"><br>
    <% if (error != null) { %>
    <div class="error"><%= error %></div>
    <% } %>
    <br>
    <button type="submit">Register</button>
    <a href="login.jsp">Login</a>
</form>
</body>
</html>
<%
    session.removeAttribute("usernameError");
    session.removeAttribute("passwordError");
    session.removeAttribute("usernameValue");
    session.removeAttribute("passwordValue");
%>