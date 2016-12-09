/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Img;
import service.ImgController;

/**
 *
 * @author Mokhtar
 */

@WebServlet(name = "UploadFile", urlPatterns = {"/UploadFile"})
@MultipartConfig(location="/var/www/html/img")
public class UploadFile extends HttpServlet {
    
    @EJB
    private ImgController controller;

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
        try{
            

        Img newImg = new Img();
        newImg.setDescription(request.getParameter("Description"));
        newImg.setName(request.getParameter("Title"));
        newImg.setUrl("//10.114.32.59/img/" + request.getPart("filename").getSubmittedFileName());
        
        String startDate = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Date myDate = formatter.parse(startDate);
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        newImg.setUploadDate(sqlDate);
        
        
        controller.uploadImg(newImg);
        //upload(request);
        request.getPart("filename").write(request.getPart("filename").getSubmittedFileName());
        response.sendRedirect("index.html");
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(UploadFile.class.getName()).log(Level.SEVERE, null, ex);
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
