<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="controller.WebAppServlet" %>
<%@ page import="model.User" %>

<%
    WebAppServlet webAppServlet = new WebAppServlet();
    String username = webAppServlet.getUsernameFromSessionOrCookie(request, response);
    if (username == null) {
        return;
    }

    User user = webAppServlet.getUserData(username);
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Index Page</title>
</head>
<body>
<h1>Welcome, <%= user.getUsername() %>!</h1>
<p>Your password: <%= user.getPassword() %></p>
<!-- Add other user fields as needed -->
<a href="logout">Logout</a>
</body>
</html>