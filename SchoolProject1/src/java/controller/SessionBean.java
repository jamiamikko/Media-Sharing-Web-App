/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import model.Feedback;
import model.Img;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Usr;
/**
 *
 * @author Mokhtar
 */
@Stateless
public class SessionBean {

    @PersistenceContext(name = "SchoolProject1PU")
    //new variable em is created here
    private EntityManager em;
    //new function List is created here, it fetches a function
    //Usr.findAll from Usr class and creates a list of the users
    public List<Usr> getAllUser(){
        return em.createNamedQuery("Usr.findAll").getResultList();
    }
    
    
    //new function getUserById is created
    //it finds all users with their id's from Usr class
    public Usr getUserById(int id){
        return em.find(Usr.class, id);
    }
    
    //getGuest function created
    //creates a guess user when user is not logged in
    public Usr getGuest(){
        return getUserByName("guest");
    }
    
    //getUserByName created
    //when logged in, it fetches the users name and
    //adds it as the profile name
    public Usr getUserByName(String name){
        List<Usr> userList = getAllUser();
        for (Usr user : userList){
            if (user.getUserName().equals(name)){
                return user;
            }
        }
        return null;
    }
    
    //inserts the users name along with the image so the images owner is known
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
    
    //inserts the feedback to the back-end
    public Feedback insertFeedback(Feedback f){
        em.persist(f);
        return f;
    }
    
    //insert, update, delete, select images
    //gets image from database to the front-end 
    //it fetches it from image class by id
    public Img getImgByID(int id) {
        return em.find(Img.class, id);
    }
    
    //gets image from comment to the front-end 
    //it fetches it from comment class by id
     public Feedback getFeedbackByID(int id) {
        return em.find(Feedback.class, id);
    }

    //creates a list of all images to the fron-end and
    //reverses the order so the newest is on top
    //and the oldest on bottom
    public List<Img> getAllImages() {
        List<Img> img = em.createNamedQuery("Img.findAll").getResultList();
        ArrayList<Img> newList = new ArrayList<Img>(img);
        Collections.reverse(newList);
        return newList;
    }
    
    //creates a list of comments with the oldest on top
    //comments are shown under the images
    public List<Feedback> getAllComments() {
        return em.createNamedQuery("Feedback.findAll").getResultList();
    }
    
    //if multiple posts have same amount of comments
    //it chooses the newest one.
    //shows only one result
    public Img getOneOfImgWithMostFeedback(){
        return (Img)em.createNamedQuery("Img.findOneByMaxFeedback").getSingleResult();
    }
    
    //creates a group of images and goes through whitch one has the most comments
    //if multiple images have same amount of comments, the function above activates
    //shows only one result
    public List<Feedback> getImgMostFeedback(){
        return em.createNamedQuery("Feedback.findMostCommentedPost").getResultList();
    }
    
    //makes a list of users and sees which one has the most comments
    //the one with most comments is on top
    public List<Feedback> getUserMostFeedback(){
        return em.createNamedQuery("Feedback.findMostActiveUser").getResultList();
    }

    //inserts image in database
    public Img insertImg(Img c) {
        em.persist(c);
        return c;
    }

    //udates the database
    public void updateImg(Img c) {
        em.merge(c);
    }

    //removes the image from database
    public void deleteImage(Img c) {
        em.remove(em.merge(c));
    }
    
    //removes the user from the database
    public void deleteUser(Usr c) {
        em.remove(em.merge(c));
    }
    
    //removes feedback from database
    public void deleteFeedback(Feedback c) {
        em.remove(em.merge(c));
    }
}
