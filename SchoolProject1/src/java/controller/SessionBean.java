/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Collections;
import javax.ejb.Stateless;
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

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(name = "SchoolProjectPU")
    private EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public Img selectByID(int id) {
        return em.find(Img.class, id);
    }

    public List<Img> selectAll() {
        List<Img> img = em.createNamedQuery("Img.findAll").getResultList();
        ArrayList<Img> newList = new ArrayList<Img>(img);
        Collections.reverse(newList);
        return newList;
    }
    
    public List<Feedback> selectAllComments() {
        return em.createNamedQuery("Feedback.findAll").getResultList();
    }
    
    public List<Usr> selectAllUsers() {
        return em.createNamedQuery("Usr.findAll").getResultList();
    }

    public Img insert(Img c) {
        em.persist(c);
        return c;
    }

    public void update(Img c) {
        em.merge(c);
    }

    public void delete(Img c) {
        em.remove(em.merge(c));
    }
}
