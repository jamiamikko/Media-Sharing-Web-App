/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Feedback;

/**
 *
 * @author Mokhtar
 */
@WebServlet(urlPatterns = {"/Commentor"})

public class Commentor extends HttpServlet {
    
    private EntityManager em;
    private EntityTransaction t;

    public Commentor() {

        em = Persistence.createEntityManagerFactory("SchoolProject1PU").createEntityManager();

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (null == em  || !em.isOpen()){
            em = Persistence.createEntityManagerFactory("SchoolProject1PU").createEntityManager();
        }
        
        List<Feedback> lst = em.createNamedQuery("Feedback.findAll").getResultList();
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet test</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet test at " + request.getContextPath() + "</h1>");
            
            for(Feedback element : lst){
                out.println("<p>" + element + "</p>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idc = request.getParameter("Content");

        try (PrintWriter out = response.getWriter()) {
            out.println(idc);
            
            Feedback feedback = new Feedback();
            
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate = (Date) formatter.parse("2016-12-01");
            java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

            try {
                
                feedback.setContent(idc);
                feedback.setUploadDate(sqlDate);

                //feedback.setComment(idc);
                
                t = em.getTransaction();
                t.begin();
                em.persist(feedback);
                t.commit();
                out.println("Added successfully");
            } catch (Exception e) {
                System.err.println("Caught Exception: " + e.getMessage());
                t.rollback();
                out.println("Something bad happened: " + e);
            }
            em.close();
        } catch (ParseException ex) {
            Logger.getLogger(Commentor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
