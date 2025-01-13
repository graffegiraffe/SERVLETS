package by.rublevskaya.model.servlet;

import by.rublevskaya.model.log.CustomLogger;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomLogger.info("HomeServlet: processing GET request");
        HttpSession session = request.getSession(false);

        if (session != null) {
            String username = (String) session.getAttribute("username");
            String role = (String) session.getAttribute("role");
            if (username != null && role != null) {
                // Если пользователь найден в сессии
                response.getWriter().write("Welcome, " + username + "! Your role is: " + role);
                CustomLogger.info("HomeServlet: user " + username + " with role " + role + " accessed home page");
            } else {
                // Если в сессии нет пользователя
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Access denied. User is not logged in.");
                CustomLogger.info("HomeServlet: unauthorized access attempt, user not found in session");
            }
        } else {
            // Если сессии вообще нет
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Access denied. User is not logged in.");
            CustomLogger.info("HomeServlet: unauthorized access attempt, session not found");
        }
    }
}