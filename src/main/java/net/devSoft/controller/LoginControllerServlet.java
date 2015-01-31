package net.devSoft.controller;

import net.devSoft.domain.User;
import net.devSoft.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Sujoy on 1/31/2015.
 */
@WebServlet(name = "LoginControllerServlet")
public class LoginControllerServlet extends HttpServlet {


    private static final String LOGIN_PAGE = "/WEB-INF/jsp/login.jsp";
    private static final String HOME_REQUEST = "/home";
    private static final String UPDATE_MEAL_PLAN_REQUEST = "/update-meal-plan";

    private Logger logger = LoggerFactory.getLogger(LoginControllerServlet.class);
    private LoginService loginService;
    private static String contextPath = "";

    public LoginControllerServlet() {
        loginService = new LoginService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(LOGIN_PAGE);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        contextPath = req.getContextPath();

        String admin = req.getParameter("admin");

        User user = new User();
        user.setId(req.getParameter("userId"));
        user.setPassword(req.getParameter("password"));
        user.setAdmin(isAdmin(admin));

        boolean isValidUser = loginService.isValidUser(user);
        if (isValidUser) {
            logger.info("sending response to " + user.getId());
            startUserSession(user, req);
            sendResponseToValidUser(resp, user);
        } else {
            logger.info("wrong userid or password from " + user.getId());
            sendResponseToInvalidUser(req, resp);
        }

    }

    private boolean isAdmin(String admin) {
        if (admin != null) {
            return true;
        } else {
            return false;
        }
    }

    private void startUserSession(User user, HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.setAttribute("authenticatedUser", user);

    }

    private void sendResponseToValidUser(HttpServletResponse resp, User user) throws IOException {
        if (user.isAdmin()) {
            resp.sendRedirect(contextPath + UPDATE_MEAL_PLAN_REQUEST);
        } else {
            resp.sendRedirect(contextPath + HOME_REQUEST);
        }
    }

    private void sendResponseToInvalidUser(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(LOGIN_PAGE);
        req.setAttribute("message", "wrong userid or password");
        requestDispatcher.forward(req, resp);
    }
}