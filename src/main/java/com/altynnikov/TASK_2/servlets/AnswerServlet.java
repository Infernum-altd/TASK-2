package com.altynnikov.TASK_2.servlets;

import com.altynnikov.TASK_2.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AnswerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String answer = req.getParameter("answer");

        UserService.getUserSession().interact(answer);

        String[] navMessages = UserService.getUserSession().getCurrentView().getNavigationMessage().split("\n");

        req.setAttribute("navMessages", navMessages);

        getServletContext().getRequestDispatcher("/interaction.jsp").forward(req, resp);
    }
}
