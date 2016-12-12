/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonRestful;

import controller.SessionBean;
import static java.lang.System.console;
import java.net.URISyntaxException;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
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
@Path("generic")
public class GenericResource {

    @EJB
    private SessionBean sessionBean;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of JsonRestful.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("imageData")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Img> getImages() {
        //here call the find by id from the Stateless bea  
        //generates a json object
        return sessionBean.getAllImages();

    }

    @GET
    @Path("commentData")
    @Produces(MediaType.APPLICATION_JSON)
    //makes a list of all the comments
    public List<Feedback> getComments() {
        //here call the find by id from the Stateless bea
        //generates a json object
        return sessionBean.getAllComments();

    }

    @GET
    @Path("userData")
    @Produces(MediaType.APPLICATION_JSON)
    //makes a list of all the users
    public List<Usr> getUsers() {
        //here call the find by id from the Stateless bea
        //generates a json object
        return sessionBean.getAllUser();

    }

    @GET
    @Path("topComments")
    @Produces(MediaType.APPLICATION_JSON)
    
    public List<Feedback> hello() {
        //generates a json object
        return sessionBean.getImgMostFeedback();
    }
    
    @GET
    @Path("topUser")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Feedback> hello2() {
        //generates a json object
        return sessionBean.getUserMostFeedback();
    }

    @POST()
    @Path("deleteImage")
    //makes it possible to delete images
    public Response deleteImage(@FormParam("id") int id) throws URISyntaxException {
        
        //generates a json object
        List<Feedback> feedbackList = sessionBean.getAllComments();
        //for the image to be deleted the comments need to go as well
        for(Feedback feedback : feedbackList) {
            if (feedback.getOnContent().equals(id)) {
                sessionBean.deleteFeedback(feedback);
            }
        }
        //removes the image
        sessionBean.deleteImage(sessionBean.getImgByID(id));
        //redirects back to the admin page
        java.net.URI location = new java.net.URI("../admin/admin.html");
        return Response.temporaryRedirect(location).build();
    }

    @POST()
    @Path("deleteUser")
    //makes it possible to delete users
    public Response deleteUser(@FormParam("id") int id) throws URISyntaxException {
        ////generates a json object
        sessionBean.deleteUser(sessionBean.getUserById(id));
        //redirects back to the admin page
        java.net.URI location = new java.net.URI("../admin/admin.html");
        return Response.temporaryRedirect(location).build();
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     *
     * @param content representation for the resource
     */
}
