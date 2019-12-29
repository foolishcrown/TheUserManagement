/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sangnv.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import sangnv.conn.MyConnection;
import sangnv.dtos.PromotionDTO;

/**
 *
 * @author Shang
 */
public class PromotionDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    private void closeConnection() throws SQLException {
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

    public List<PromotionDTO> getProList() throws NamingException, SQLException {
        List<PromotionDTO> result = null;
        PromotionDTO dto = null;
        try {
            String sql = "Select userID, rank, DateOfCreate From PromotionList ORDER BY DateOfCreate DESC";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                String userID = rs.getString("userID");
                int rank = rs.getInt("rank");
                Timestamp dateCreate = rs.getTimestamp("DateOfCreate");
                dto = new PromotionDTO(userID, rank);
                dto.setDateOfCreate(dateCreate);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public int[] insertProList(List<PromotionDTO> proList) throws NamingException, SQLException {
        int[] result= null;
        try {
            String sql = "Insert Into PromotionList(userID, rank) Values(?, ?)";
            conn = MyConnection.getMyConnection();
            conn.setAutoCommit(false);
            preStm = conn.prepareStatement(sql);
            for (PromotionDTO dto : proList) {
                preStm.setString(1, dto.getUserID());
                preStm.setInt(2, dto.getRank());
                preStm.addBatch();
            }
            result = preStm.executeBatch();
            System.out.println(result.length);
            conn.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            conn.rollback();
        } finally {
            closeConnection();
        }

        return result;
    }

    
    
}
