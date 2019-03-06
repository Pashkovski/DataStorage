/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalTask;

import FinalTaskBeans.SelectBeanLocal;
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
public class ViewList extends HttpServlet {
    
    @EJB
    private SelectBeanLocal selectBean;
    

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
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Data storage system</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println(viewPage());
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    private String viewPage() {        
        return "<form action=\"ViewList\" method=\"Post\">\n" +
"            <p>\n" +
"               <h1>Please, select which parameter list do you want to view: </h1>\n" +               
"                   <input type=\"submit\" name=\"select\" value=\"Select all\"></br>\n" +            
"                   <input type=\"submit\" name=\"select\" value=\"Select by name\">\n" +
"                   <input type=\"text\" name=\"paramName\" size=\"20\"/></br>\n" +
"                   <input type=\"submit\" name=\"select\" value=\"Select in range \">\n" +  
"                   <input type=\"text\" name=\"paramRange\" size=\"20\"/></br>\n" + 
"            </p>\n" +
"        </form>";
//"        <form action=\"Users\" method=\"Get\">\n" +
//"            <p>\n" +
//"               <h2>Or if you want to add something new, please, push the button below: </h2>\n" + 
//"                   <input type=\"submit\" name=\"backToReg\" value=\"Add new\" size=\"20\"/>\n" +
//"            </p>\n" +
//"        </form>";
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String ans = null;
            String operation = request.getParameter("select");
            if (operation.equalsIgnoreCase("Select all")) {
                ans = selectBean.selectAll();
            }
            if (operation.equalsIgnoreCase("Select by name")) {
                ans = selectBean.selectOne(request.getParameter("paramName"));
            }
            if (operation.equalsIgnoreCase("Select in range")) {
                ans = selectBean.selectByParam();
            }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Data storage system</title>");            
            out.println("</head>");
            out.println("<body>");
            if (selectBean != null) {
            out.println(ans);
            } else {
                out.println("Something wrong");
            }
            out.println("</body>");
            out.println("</html>");
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
