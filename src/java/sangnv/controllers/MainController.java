/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sangnv.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sangnv.dtos.UserDTO;

/**
 *
 * @author Shang
 */
public class MainController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String LOGIN = "LoginController";
    private static final String SEARCH = "AdminLoadController";
    private static final String DELETE = "DeleteController";
    private static final String UPDATE = "UpdateController";
    private static final String INSERT = "InsertController";
    private static final String EDIT = "EditController";
    private static final String ADDPRO = "AddPromotionController";
    private static final String DELPRO = "DeletePromotionController";
    private static final String UPDATEPRO = "UpdatePromotionController";
    private static final String CONFIRMPRO = "ConfirmPromotionController";
    private static final String CHANGEPASS = "ChangePasswordController";
    private static final String CHANGEPASSSUB = "SubChangePasswordController";

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
            String action = request.getParameter("action");
            if (action != null) {
                if ("Login".equals(action)) {
                    url = LOGIN;
                } else if ("Search".equals(action)) {
                    url = SEARCH;
                } else if ("Delete".equals(action)) {
                    url = DELETE;
                } else if ("Update".equals(action)) {
                    url = UPDATE;
                } else if ("Insert".equals(action)) {
                    url = INSERT;
                } else if ("Edit".equals(action)) {
                    url = EDIT;
                } else if ("AddPromotion".equals(action)) {
                    url = ADDPRO;
                } else if ("DeletePromotion".equals(action)) {
                    url = DELPRO;
                } else if ("UpdatePromotion".equals(action)) {
                    url = UPDATEPRO;
                } else if ("ConfirmDB".equals(action)) {
                    url = CONFIRMPRO;
                } else if ("ChangePass".equals(action)) {
                    url = CHANGEPASS;
                } else if ("ChangePassSub".equals(action)) {
                    url = CHANGEPASSSUB;
                } else {
                    request.setAttribute("ERROR", "Your action is not supported");
                }
            } else {
                request.setAttribute("ERROR", "Can't take your action request, try again!s");
            }
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("USER");

            if (url == LOGIN) {

            } else {
                if (user != null) {
                    int role = user.getRole();
                    if (url == CHANGEPASSSUB) {
                        if (role != 1) {
                            url = "index.jsp";
                        }
                    }else if(role != 0){
                        url = "index.jsp";
                    }
                }else{
                    url = "index.jsp";
                }
            }

        } catch (Exception e) {
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
