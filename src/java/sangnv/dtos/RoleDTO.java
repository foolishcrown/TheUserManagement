/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sangnv.dtos;

import java.io.Serializable;

/**
 *
 * @author Shang
 */
public class RoleDTO implements Serializable {
    private int roleID;
    private String description;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public RoleDTO(int roleID, String description, int count) {
        this.roleID = roleID;
        this.description = description;
        this.count = count;
    }

    public RoleDTO() {
    }

    public RoleDTO(int roleID, String description) {
        this.roleID = roleID;
        this.description = description;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
    
}
