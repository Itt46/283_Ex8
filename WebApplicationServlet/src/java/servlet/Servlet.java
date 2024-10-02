/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ittak
 */
public class Servlet extends HttpServlet {

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
        String circleData = request.getParameter("Circle input");
        double cd = Double.parseDouble(circleData);
        double ans;
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet webServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>For the circle with radius = " + circleData + "</h1>");
            if (request.getParameter("CircleArea") != null) {
                ans = Math.PI * cd * cd; 
                out.println("<h1>area is " + ans + "</h1>");
            }
            if (request.getParameter("CirclePerimiter") != null) {
                ans = 2 * Math.PI * cd; 
                out.println("<h1>perimeter is " + ans + "</h1>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }
}
