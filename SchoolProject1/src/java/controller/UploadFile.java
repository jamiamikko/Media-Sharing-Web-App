/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Img;
import model.Usr;



/**
 *
 * @author Mokhtar
 */
@WebServlet(name = "UploadFile", urlPatterns = {"/UploadFile"})
@MultipartConfig(location = "/var/www/html/img")
public class UploadFile extends HttpServlet {

    @EJB
    private SessionBean controller;

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
        try {
            
            //gets the image from front-end and adds atributes in addition when inserted in database
            //attributes: filename, Title, description, date and user id
            Img newImg = new Img("//10.114.32.59/img/" + request.getPart("filename").getSubmittedFileName(), request.getParameter("Title"), request.getParameter("Description"), new Date(Calendar.getInstance().getTimeInMillis()), new Usr(Integer.parseInt(request.getParameter("userId"))));
            
            //calls insertImg from sessionBean and inserts the image to database
            controller.insertImg(newImg);

            //inserts the image to server
            request.getPart("filename").write(request.getPart("filename").getSubmittedFileName());
            //shows the image in front-end
            response.sendRedirect("index.html");
            //catches all the errors and shows them in the log
        } catch (Exception pokemon) {
            java.util.logging.Logger.getLogger(UploadFile.class.getName()).log(Level.SEVERE, null, pokemon);
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
    }

}
