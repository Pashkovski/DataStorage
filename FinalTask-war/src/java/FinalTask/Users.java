/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalTask;

import FinalTaskBeans.DBManagerLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lesya
 */
public class Users extends HttpServlet {
    @EJB
    private DBManagerLocal dbManager;

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
        response.setContentType("text/html;charset=UTF-8");
        String user = request.getParameter("login");
//        if (dbManager.checkUser(user) == false) {
//            dbManager.addUser(user);
//        }
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Data storage system</title>");            
            out.println("</head>");
            out.println("<body>");
            if (user.isEmpty()) {
                out.println("<h1>Error: login cannot be empty. Go back and try again!</h1>");
            } else {
                if (!dbManager.checkUser(user)) {
                    out.println("<h1> Hi, new user " + user + "! Let's do some magic! </h1>");
                    dbManager.addUser(user);
                    out.println(nextStage());
                } else {
                    out.println("<h1> Nice to see you again, user " + user + "!</h1>");
                    out.println(nextStage());
                }
            }
            out.println("</body>");
            out.println("</html>");
    }
    }
    
        private String nextStage() {
        return "<form action=\"Registrator\" method=\"Get\">\n" +
"            <p>\n" +
"                <h2>Please, enter parameter name and its value below: </h2></br>\n" +
"                parameter <input type=\"text\" name=\"param\" size=\"20\"/>\n" +
"                value <input type=\"text\" name=\"value\" size=\"20\"/>\n" +                
"                <input type=\"submit\" name=\"input\" value=\"OK\"/>\n" +
"                <input type=\"reset\" value=\"Clear\"/>\n" +
"            </p>\n" +
"        </form>\n" +
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
