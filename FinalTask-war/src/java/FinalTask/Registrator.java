/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalTask;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lesya
 */
public class Registrator extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

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
        String msg;
        String param = request.getParameter("param");
        String value = request.getParameter("value");
        msg = checkParam(param, value);
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Data storage system</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>System's report: " + msg + "</h1>");
            if (msg.equalsIgnoreCase("Ok")) {
                out.println(nextStage(param, value));
            }
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    private String checkParam (String param, String value) {
        String msg = "Ok";
        if (param.isEmpty()) {
            msg = "Parameter name cannot be empty. Go back and try again!";
        }
        
        if (param.length() > 255) {
            msg = "Parameter name is too long. Go back and try again!";
        }
        
        if (!(value.matches("\\d+"))) {
            msg = "Parameter value should be integer. Go back and try again!";
        }   
        return msg;
    }
    
    private String nextStage(String param, String value) {
        return "<form action=\"Attribute\" method=\"Get\">\n" +
"            <p>\n" +
"                <h2>For adding parameter " + param + ", " + value + " push the button below</h2></br>\n" +               
"                <input type=\"submit\" name=\"input\" value=\"Add parameter\"/>\n" +
"            </p>\n" +
"        </form>\n" +
         "<form action=\"ViewList\" method=\"Get\">\n" +
"            <p>\n" +
"                <h2>If you want to view a list of all parameters added earlier, push the button below</h2></br>\n" +               
"                <input type=\"submit\" name=\"input\" value=\"View a parameter list\"/>\n" +
"            </p>\n" +
"        </form>";
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
