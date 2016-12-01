/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Img;
import model.Usr;

/**
 *
 * @author Mokhtar
 */
@Stateless
public class Codebox {
    @PersistenceContext(name = "SchoolProjectPU")
    private EntityManager em;
    public Codebox(){}
    
    public List<Usr> getAllUser(){
        return em.createNamedQuery("Usr.findAll").getResultList();
    }
    
    public Usr getUserById(String id){
        return em.find(Usr.class, id);
    }
    
    public Usr getGuest(){
        return getUserByName("guest");
    }
    
    public Usr getUserByName(String name){
        List<Usr> userList = getAllUser();
        for (Usr user : userList){
            if (user.getUserName().equals(name)){
                return user;
            }
        }
        return null;
        //return (Usr) em.createNamedQuery("Usr.findByUserName").setParameter("userName", name).getSingleResult();
    }
    
    public Collection<Img> getImgByUserName(String name){
        return getUserByName(name).getImgCollection();
    }
    
    public void insertUser(Usr user){
        em.persist(user);
    }
    
    public void updateUser(Usr user){
        em.merge(user);
    }
}
