package org.bondar.rabbitejbexample.controller;

import org.bondar.rabbitejbexample.producer.RabbitProducer;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Stateless
@LocalBean
@Path("/messages")
public class MessageController {

    @EJB
    private RabbitProducer rabbitProducer;

    @GET
    @Path("/sendMessages")
    public Response sendMessages() {
        rabbitProducer.sendMessagesInLoop("Test Message");
        return Response.ok("Messages sent successfully!").build();
    }
}
