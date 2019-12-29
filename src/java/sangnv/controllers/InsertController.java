/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sangnv.controllers;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import sangnv.daos.UserDAO;
import sangnv.dtos.UserDTO;

/**
 *
 * @author Shang
 */
public class InsertController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "AdminLoadController";
    private static final String INVALID = "LoadRoleInsertController";

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
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("USER");
        UserDTO dto = null;
        boolean isMultiPart = ServletFileUpload.isMultipartContent(request);

        try {
            if (user != null) {
                int role = user.getRole();
                if (role == 0) {
                    if (!isMultiPart) {
                        System.out.println("Not Multi");
                    } else {
                        FileItemFactory factory = new DiskFileItemFactory();
                        ServletFileUpload upload = new ServletFileUpload(factory);
                        List items = null;

                        items = upload.parseRequest(request);
                        Iterator iter = items.iterator();
                        Hashtable params = new Hashtable();
                        String filename = null;
                        while (iter.hasNext()) {
                            FileItem item = (FileItem) iter.next();
                            if (item.isFormField()) {
                                params.put(item.getFieldName(), item.getString());
                            } else {
                                String itemName = item.getName();
                                filename = itemName.substring(itemName.lastIndexOf("\\") + 1);
                                System.out.println("path: " + filename);
                                String realPath = getServletContext().getRealPath("/") + "images\\" + filename;
                                System.out.println("Real Path: " + realPath);
                                File savedFile = new File(realPath);
                                try {
                                    item.write(savedFile);
                                } catch (Exception ex) {
                                    log(ex.getMessage());
                                }

                            }
                        }
                        String userID = (String) params.get("txtUserID");
                        String username = (String) params.get("txtUsername");
                        String password = (String) params.get("txtPassword");
                        String email = (String) params.get("txtEmail");
                        String phone = (String) params.get("txtPhone");
                        String photo = filename;
                        int roleID = Integer.parseInt((String) params.get("cboRole"));
                        dto = new UserDTO(userID, username, email, phone, photo, roleID);
                        dto.setPassword(password);
                        UserDAO dao = new UserDAO();
                        boolean check = dao.insertAccount(dto);
                        if (check) {
                            url = SUCCESS;
                            request.setAttribute("MSG", "Insert Successed");
                        }
                    }
                }else{
                    url = "index.jsp";
                }

            } else {
                url = "index.jsp";
            }

        } catch (SQLException | NamingException | FileUploadException | NoSuchAlgorithmException ex) {
            if (ex.getMessage().contains("duplicate")) {
                request.setAttribute("DTO", dto);
                request.setAttribute("INVALID", "Your UserID is already exist!");
                url = INVALID;
            } else {
                log(ex.getMessage());
            }
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
