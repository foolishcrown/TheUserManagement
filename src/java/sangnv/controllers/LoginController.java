/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sangnv.controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sangnv.daos.UserDAO;
import sangnv.dtos.UserDTO;

/**
 *
 * @author Shang
 */
public class LoginController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String INVALID = "index.jsp";
    private static final String ADMIN = "AdminLoadController";
    private static final String SUB = "SubLoadController";

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
        String url = ERROR;
        try {
            String userID = request.getParameter("userID");
            String password = request.getParameter("password");
            UserDAO dao = new UserDAO();
            String role = dao.checkRole(userID, password).trim();
            HttpSession session = request.getSession();
            if ("fail".equals(role)) {
                url = INVALID;
                request.setAttribute("INVALID", "Wrong UserID or Password, please try again!");
            } else if ("Admin".equals(role)) {
                url = ADMIN;
                UserDTO dto = dao.findByPrimaryKey(userID);
                session.setAttribute("USER", dto);
            } else if ("Sub".equals(role)) {
                url = SUB;
                UserDTO dto = dao.findByPrimaryKey(userID);
                session.setAttribute("USER", dto);
            } else {
                url = INVALID;
                request.setAttribute("INVALID", "Your role is not supported");
            }
        } catch (SQLException | NamingException | NoSuchAlgorithmException e) {
            log(e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
        processRequest(request, response);
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
