/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sangnv.daos;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sangnv.conn.MyConnection;
import sangnv.dtos.RoleDTO;
import sangnv.dtos.UserDTO;

/**
 *
 * @author Shang
 */
public class UserDAO implements Serializable {

    public static final boolean ACTIVE = true;
    public static final boolean INACTIVE = false;

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public List<UserDTO> getALL() throws SQLException, NamingException, Exception {
        List<UserDTO> result = null;

        try {
            String sql = "Select userID, username, photo From Account Where status = 1";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                String userID = rs.getString("userID");
                String username = rs.getString("username");
                String photo = rs.getString("photo");
                UserDTO dto = new UserDTO(userID, username, photo);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<UserDTO> searchByName(String search) throws SQLException, NamingException, Exception {
        List<UserDTO> result = null;

        try {
            String sql = "Select userID, username, photo From Account Where status = 1 and username LIKE ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                String userID = rs.getString("userID");
                String username = rs.getString("username");
                String photo = rs.getString("photo");
                UserDTO dto = new UserDTO(userID, username, photo);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<UserDTO> getListByRole(int role) throws SQLException, NamingException, Exception {
        List<UserDTO> result = null;

        try {
            String sql = "Select userID, username, photo From Account Where status = 1 and role = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, role);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                String userID = rs.getString("userID");
                String username = rs.getString("username");
                String photo = rs.getString("photo");
                UserDTO dto = new UserDTO(userID, username, photo);
                dto.setRole(role);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public String checkRole(String userID, String password) throws SQLException, NamingException, NoSuchAlgorithmException {
        String role = "fail";
        try {
            String sql = "select A.Description as Role from Role A inner join Account B on A.RoleID = B.role where B.userID = ? and B.password = ? and B.status = 1";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, userID);
            String sha256 = toHexPassword(password);
            preStm.setString(2, sha256);
            rs = preStm.executeQuery();
            if (rs.next()) {
                role = rs.getString("Role");
            }
        } finally {
            closeConnection();
        }
        return role;

    }

    public List<RoleDTO> getRoleList() throws NamingException, SQLException {
        List<RoleDTO> result = null;
        RoleDTO dto = null;
        try {
            String sql = "Select RoleID, Description From Role";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                int roleID = rs.getInt("RoleID");
                String description = rs.getString("Description");
                dto = new RoleDTO(roleID, description);
                result.add(dto);
            }
        } finally {
            closeConnection();

        }
        return result;
    }

    public UserDTO findByPrimaryKey(String userID) throws NamingException, SQLException {
        UserDTO dto = null;
        try {
            String sql = "Select username, role From Account Where userID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, userID);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String username = rs.getString("username");
                int role = rs.getInt("role");
                dto = new UserDTO(userID, username, role);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public UserDTO getInforByPrimaryKey(String userID) throws NamingException, SQLException {
        UserDTO dto = null;
        try {
            String sql = "Select username, password, email, phone, photo, role From Account Where userID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, userID);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String photo = rs.getString("photo");
                int role = rs.getInt("role");
                dto = new UserDTO(userID, username, email, phone, photo, role);
                dto.setPassword(password);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public int countAccountByRole(int role) throws SQLException, NamingException {
        int result = 0;
        try {
            String sql = "Select Count(*) as Num From Account Where role = ? and status = 1";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, role);
            rs = preStm.executeQuery();
            if (rs.next()) {
                result = rs.getInt("Num");
            }
        } finally {
            closeConnection();
        }

        return result;
    }

    public boolean changeStatusAccount(String userID, boolean status) throws SQLException, NamingException {
        boolean check = false;
        try {
            String sql = "Update Account Set status = ? Where userID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setBoolean(1, status);
            preStm.setString(2, userID);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean insertAccount(UserDTO dto) throws SQLException, NamingException, NoSuchAlgorithmException {
        boolean check = false;
        try {
            String sql = "Insert Into Account(userID, username, password, email, phone, photo, role) Values(?, ?, ?, ? , ?, ?, ?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getUserID());
            preStm.setString(2, dto.getUsername());
            String sha256 = toHexPassword(dto.getPassword());
            preStm.setString(3, sha256);
            preStm.setString(4, dto.getEmail());
            preStm.setString(5, dto.getPhone());
            preStm.setString(6, dto.getPhoto());
            preStm.setInt(7, dto.getRole());
            check = preStm.executeUpdate() > 0;

        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean updateAccount(UserDTO dto) throws NamingException, SQLException {
        boolean check = false;
        try {
            String sql = "Update Account Set username = ?, email = ?, phone = ?, photo = ?, role = ? Where userID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getUsername());
            preStm.setString(2, dto.getEmail());
            preStm.setString(3, dto.getPhone());
            preStm.setString(4, dto.getPhoto());
            preStm.setInt(5, dto.getRole());
            preStm.setString(6, dto.getUserID());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean changePassword(String userID, String newPass, String oldPass) throws SQLException, NamingException, NoSuchAlgorithmException{
        boolean check = false;
        try {
            String sql = "Update Account Set password = ? Where userID = ? and password = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            String sha256 = toHexPassword(newPass);
            preStm.setString(1, sha256);
            preStm.setString(2, userID);
            String sha256oldpass = toHexPassword(oldPass);
            preStm.setString(3, sha256oldpass);
            check = preStm.executeUpdate() > 0;
        }finally {
          closeConnection();
        }
        return check;
    }
    
    
    
    
    public byte[] convertPassword(String password) throws NoSuchAlgorithmException {

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] result = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return result;

    }

    public String toHexPassword(String password) throws NoSuchAlgorithmException {
        StringBuffer hexString = new StringBuffer();
        byte[] hash = convertPassword(password);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append(0);
            }
            hexString.append(hex);

        }
        return hexString.toString();
    }

}
