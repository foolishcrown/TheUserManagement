/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sangnv.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sangnv.daos.PromotionDAO;
import sangnv.dtos.ProListDTO;
import sangnv.dtos.PromotionDTO;

/**
 *
 * @author Shang
 */
public class AddPromotionController extends HttpServlet {

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

        try {
            HttpSession session = request.getSession();
            ProListDTO proList = (ProListDTO) session.getAttribute("PROLIST");
            if (proList == null) {
                proList = new ProListDTO();
            }
            String userID = request.getParameter("userID");
            PromotionDTO dto = new PromotionDTO(userID);
            PromotionDAO dao = new PromotionDAO();
            List<PromotionDTO> dbList = dao.getProList();
            if (!dbList.contains(dto)) {
                boolean check = proList.addToList(dto);
                if (check) {
                    request.setAttribute("MSG", "Added Successed");
                    session.setAttribute("PROLIST", proList);
                } else {
                    request.setAttribute("INVALID", "Account has already added in your promotion list!");
                }
            } else {
                request.setAttribute("INVALID", "Account has already stored in the promotion list DB!");
            }
        } catch (SQLException | NamingException e) {
            log("Error at AddPromotionController : " + e.getMessage());
        } finally {
            request.getRequestDispatcher("AdminLoadController").forward(request, response);
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
