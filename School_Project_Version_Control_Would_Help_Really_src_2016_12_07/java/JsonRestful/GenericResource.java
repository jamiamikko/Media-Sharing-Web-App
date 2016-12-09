/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JsonRestful;

import controller.SessionBean;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
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

    @Context
    private UriInfo context;

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
        return sessionBean.selectAllImages();

    }

    @GET
    @Path("commentData")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Feedback> getComments() {
        //here call the find by id from the Stateless bea        
        return sessionBean.selectAllComments();

    }

    @GET
    @Path("userData")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usr> getUsers() {
        //here call the find by id from the Stateless bea        
        return sessionBean.selectAllUsers();

    }

    @GET
    @Path("topComments")
    @Produces(MediaType.APPLICATION_JSON)
    public Img hello() {
        return sessionBean.getOneOfImgWithMostFeedback();
    }

    @POST()
    @Path("deleteImage")
    public void deleteImage(@FormParam("id") int id, @FormParam("url") String url, @FormParam("name") String name, @FormParam("description") String description) {
        List<Img> allImages = sessionBean.selectAllImages();

        for (Img image : allImages) {
            if (image.getId().equals(id) && image.getName().equals(name) && image.getUrl().equals(url) && image.getDescription().equals(description)) {
                sessionBean.deleteImage(image);
            }
        }
    }
    
    @POST()
    @Path("deleteUser")
    public void deleteUser(@FormParam("id") int id, @FormParam("userName") String name) {
        List<Usr> allUsers = sessionBean.selectAllUsers();

        for (Usr user : allUsers) {
            if(user.getId().equals(id) && user.getUserName().equals(name)) {
                sessionBean.deleteUser(user);
            }
        }
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     *
     * @param content representation for the resource
     */
}
