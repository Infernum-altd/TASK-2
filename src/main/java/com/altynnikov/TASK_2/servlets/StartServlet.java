package com.altynnikov.TASK_2.servlets;

import com.altynnikov.TASK_2.services.UserService;
import com.altynnikov.TASK_2.services.WriterReaderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new UserService();
        String[] navMessages = UserService.getUserSession().getCurrentView().getNavigationMessage().split("\n");
        req.setAttribute("navMessages", navMessages);

        getServletContext().getRequestDispatcher("/interaction.jsp").forward(req, resp);
    }
}
