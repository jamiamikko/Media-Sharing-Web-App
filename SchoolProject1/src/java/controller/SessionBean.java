/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.ejb.Stateless;
import model.Feedback;
import model.Img;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Usr;
/**
 *
 * @author Mokhtar
 */
@Stateless
public class SessionBean {

    @PersistenceContext(name = "SchoolProject1PU")
    private EntityManager em;

    public List<Usr> getAllUser(){
        return em.createNamedQuery("Usr.findAll").getResultList();
    }
    
    
    
    public Usr getUserById(int id){
        return em.find(Usr.class, id);
    }
    
    public Usr getGuest(){
        return getUserByName("guest");
    }
    
    public Usr getUserByName(String name){
        //Why not simply 
        //return (Usr)em.createNamedQuery("Usr.findByUserName").getSingleResult();
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
    
    //insert, update(?), delete, (select?) comments/feedback
    public List<Feedback> getCommentForImage(int imgId){
        return em.createNamedQuery("Feedback.findAll").getResultList();
    }
    
    public Feedback insertFeedback(Feedback f){
        em.persist(f);
        return f;
    }
    
    //insert, update, delete, select images
    
    public Img getImgByID(int id) {
        return em.find(Img.class, id);
    }
    
     public Feedback getFeedbackByID(int id) {
        return em.find(Feedback.class, id);
    }

    public List<Img> getAllImages() {
        List<Img> img = em.createNamedQuery("Img.findAll").getResultList();
        ArrayList<Img> newList = new ArrayList<Img>(img);
        Collections.reverse(newList);
        return newList;
    }
    
    public List<Feedback> getAllComments() {
        return em.createNamedQuery("Feedback.findAll").getResultList();
    }
    
    public Img getOneOfImgWithMostFeedback(){
        return (Img)em.createNamedQuery("Img.findOneByMaxFeedback").getSingleResult();
    }
    
    public List<Feedback> getImgMostFeedback(){
        return em.createNamedQuery("Feedback.findMostCommentedPost").getResultList();
    }
    
    public List<Feedback> getUserMostFeedback(){
        return em.createNamedQuery("Feedback.findMostActiveUser").getResultList();
    }

    public Img insertImg(Img c) {
        em.persist(c);
        return c;
    }

    public void updateImg(Img c) {
        em.merge(c);
    }

    public void deleteImage(Img c) {
        em.remove(em.merge(c));
    }
    
    public void deleteUser(Usr c) {
        em.remove(em.merge(c));
    }
    
    public void deleteFeedback(Feedback c) {
        em.remove(em.merge(c));
    }
}
