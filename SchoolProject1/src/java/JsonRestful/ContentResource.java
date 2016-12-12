/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JsonRestful;

import Security.Encrypter;
import controller.SessionBean;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.ejb.EJB;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import model.Usr;

/**
 *
 * @author Mokhtar
 */
@Path("Content")
public class ContentResource {
    
    @EJB
    private SessionBean resource;
    private Encrypter en;
    
    public ContentResource() {
    }
    
    @Path("login")
    @POST
    //@Produces(MediaType.APPLICATION_JSON)
    //creates a login function that fetches the existing parameters of the username
    //and if the username and password matches the user logs in,
    //if not, then it's and wrong then nothing happens
    public Response login(@FormParam("Username")String Username, @FormParam("Password")String Password) throws URISyntaxException{
        Usr newUser = resource.getUserByName(Username);
        try {
            en = new Encrypter("-CODEBOX");
            Password = en.encrypt(Password);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException ex) {
            Logger.getLogger(ContentResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(newUser == null){
            return null;
        }
        else{
            if(newUser.getPassword().equals(Password)){
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
    //if user signs up for the first time, the system inserts the name and
    //the password to the database
    //passwords are encrypted while inserted to the database
    public /*Collection<Img>*/ Response signup(@FormParam("Username")String Username, @FormParam("Password")String Password) throws URISyntaxException{
        Usr newUser = resource.getUserByName(Username);
        try {
            en = new Encrypter("-CODEBOX");
            Password = en.encrypt(Password);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException ex) {
            Logger.getLogger(ContentResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(newUser == null){
            newUser = new Usr(Username, Password);
            newUser.setPrivilege(false);
            resource.insertUser(newUser);
            //Redirect to index.html
            java.net.URI location = new java.net.URI("../index.html?id=" + newUser.getId().toString());
            return Response.temporaryRedirect(location).build();
        }
        else{
            return null;
        }
    }
   
}
