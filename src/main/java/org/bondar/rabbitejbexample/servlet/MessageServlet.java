package org.bondar.rabbitejbexample.servlet;

import org.bondar.rabbitejbexample.producer.RabbitProducer;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/send")
public class MessageServlet extends HttpServlet {

    @EJB
    private RabbitProducer rabbitProducer;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter("message");
        if (message != null && !message.isEmpty()) {
            rabbitProducer.sendMessage(message);
            resp.getWriter().write("Message Sent: " + message);
        } else {
            resp.getWriter().write("Please provide a message!");
        }
    }
}
