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
public class UserDTO implements Serializable {

    private String userID, username, password, email, phone, photo;
    private int role;

    public UserDTO() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public UserDTO(String userID, String username, String email, String phone, String photo, int role) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.photo = photo;
        this.role = role;
    }

    public UserDTO(String userID, String username, String photo) {
        this.userID = userID;
        this.username = username;
        this.photo = photo;
    }

    

    public UserDTO(String userID, String username, int role) {
        this.userID = userID;
        this.username = username;
        this.role = role;
    }

     
}
