package by.rublevskaya.model.servlet;

import by.rublevskaya.model.log.CustomLogger;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomLogger.info("LoginServlet: handling login request");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        CustomLogger.info("LoginServlet: received username: " + username);

        if ("kate".equals(username) && "kate2511".equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("role", "ADMIN");
            response.getWriter().write("Login successful as ADMIN");
            CustomLogger.info("LoginServlet: user " + username + " authenticated with role ADMIN");

        } else if ("katusha".equals(username) && "2511".equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("role", "USER");
            response.getWriter().write("Login successful as USER");
            CustomLogger.info("LoginServlet: user " + username + " authenticated with role USER");
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            response.getWriter().write("Invalid username or password.");
            CustomLogger.error("LoginServlet: failed login attempt for username: " + username);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().write("""
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Login</title>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        background-color: #ffe4e1;
                        color: #7f6881;
                        text-align: center;
                        margin: 0;
                        padding: 0;
                    }
                    form {
                        margin-top: 50px;
                    }
                    input[type="text"], input[type="password"] {
                        padding: 10px;
                        margin: 10px;
                        border: 1px solid #ccc;
                        border-radius: 5px;
                        font-size: 1em;
                    }
                    input[type="submit"] {
                        background-color: #ff69b4;
                        color: white;
                        padding: 10px 20px;
                        border: none;
                        border-radius: 20px;
                        font-size: 1em;
                        cursor: pointer;
                    }
                    input[type="submit"]:hover {
                        background-color: #ff1493;
                    }
                </style>
            </head>
            <body>
                <h1>Login</h1>
                <p>Please enter your username and password:</p>
                <form method="POST" action="/login">
                    <input type="text" name="username" placeholder="Username" required>
                    <br>
                    <input type="password" name="password" placeholder="Password" required>
                    <br>
                    <input type="submit" value="Login">
                </form>
            </body>
            </html>
            """);
    }
}