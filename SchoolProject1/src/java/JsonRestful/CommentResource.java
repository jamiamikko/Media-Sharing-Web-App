/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonRestful;

import controller.SessionBean;
import java.net.URISyntaxException;
import java.util.Date;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.Feedback;
import model.Img;
import model.Usr;

/**
 * REST Web Service
 *
 * @author Mokhtar
 */
@Path("Commenting")
public class CommentResource {

    @Context
    private UriInfo context;

    @EJB
    private SessionBean sessionBean;
    /**
     * Creates a new instance of CommentResource
     */
    public CommentResource() {
    }

    /**
     * Retrieves representation of an instance of JsonRestful.CommentResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of CommentResource
     * @param content representation for the resource
     */
    @POST
    @Path("insert")
    public Response insert(@FormParam("content")String content, @FormParam("userId")int user, @FormParam("postId")int post) throws URISyntaxException {
        Usr usar = new Usr(user);
        Img image = new Img(post);
        Feedback feedback = new Feedback(content, new Date(), usar, image);
        sessionBean.insertFeedback(feedback);
        
        java.net.URI location = new java.net.URI("../index.html");
        return Response.temporaryRedirect(location).build();
        
        
    }
}
