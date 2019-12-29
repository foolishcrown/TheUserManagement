/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sangnv.dtos;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author Shang
 */
public class PromotionDTO implements Serializable {
    private String userID, username;
    private int rank;
    private Timestamp dateOfCreate;

    public PromotionDTO() {
    }
    public PromotionDTO(String userID, int rank) {
        this.userID = userID;
        this.rank = rank;
    }

    public PromotionDTO(String userID) {
        this.userID = userID;
        this.rank = 5;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Timestamp getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(Timestamp dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PromotionDTO other = (PromotionDTO) obj;
        if (!Objects.equals(this.userID, other.userID)) {
            return false;
        }
        return true;
    }
    
}
