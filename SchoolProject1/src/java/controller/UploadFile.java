/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import static com.sun.xml.bind.util.CalendarConv.formatter;
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
import javax.ws.rs.core.Response;
import model.Img;
import org.jboss.logging.Logger;
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
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UploadFile</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UploadFile at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        processRequest(request, response);
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
        try{
            

        Img newImg = new Img();
        newImg.setDescription(request.getParameter("Description"));
        newImg.setName(request.getParameter("Title"));
        newImg.setUrl("//10.114.32.59/img/" + getFilename(request.getPart("filename")));
        
        String startDate = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Date myDate = formatter.parse(startDate);
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        newImg.setUploadDate(sqlDate);
        
        
        controller.uploadImg(newImg);
        upload(request);
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
    }// </editor-fold>
    
    public String upload (HttpServletRequest request) throws IOException, ServletException{
        request.getPart("filename").write(getFilename(request.getPart("filename")));
        return "success";
    }
    
    private static String getFilename(Part part){
        for(String content : part.getHeader("content-disposition").split(";")){
            if(content.trim().startsWith("filename")){
                return content.substring(content.indexOf('=') +1).trim().replace("\"", "");
            }
        }
        return null;
    }

}
