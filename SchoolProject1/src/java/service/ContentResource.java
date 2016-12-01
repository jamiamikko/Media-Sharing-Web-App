/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import controller.Codebox;
import java.net.URISyntaxException;
import javax.ejb.EJB;
import javax.ws.rs.FormParam;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import model.Usr;

/**
 *
 * @author Mokhtar
 */
@Path("Content")
public class ContentResource {
    
    @Context
    private UriInfo context;
    @Context
    private HttpHeaders headers;
    @EJB
    private Codebox resource;
    
    public ContentResource() {
    }
    
    @Path("login")
    @POST
    //@Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormParam("Username")String Username, @FormParam("Password")String Password) throws URISyntaxException{
        Usr newUser = resource.getUserByName(Username);
        if(newUser == null){
            return null;
        }
        else{
            if(newUser.getPassword().equals(Password)){
//                return newUser.getImgCollection();
                java.net.URI location = new java.net.URI("../index.html?id=" + newUser.getId().toString());
                return Response.temporaryRedirect(location).build();
            }
            else{
                return null;
            }
        }
    }
    
    @Path("signup")
    @POST
    //@Produces(MediaType.APPLICATION_JSON)
    public /*Collection<Img>*/ Response signup(@FormParam("Username")String Username, @FormParam("Password")String Password) throws URISyntaxException{
        Usr newUser = resource.getUserByName(Username);
        if(newUser == null){
            newUser = new Usr(Username, Password);
            resource.insertUser(newUser);
//            return newUser.getImgCollection();
            java.net.URI location = new java.net.URI("../index.html?id=" + newUser.getId().toString());
            return Response.temporaryRedirect(location).build();
        }
        else{
            return null;
        }
    }
   
}
