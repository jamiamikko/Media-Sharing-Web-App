/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Img;

/**
 *
 * @author Mokhtar
 */
@Stateless
public class ImgController {
    @PersistenceContext(name= "SchoolProject1PU")
    private EntityManager em;
    
    public ImgController(){
        
    }
    
    public void uploadImg(Img img){
        em.persist(img);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
