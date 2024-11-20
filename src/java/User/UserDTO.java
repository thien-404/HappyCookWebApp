/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

/**
 *
 * @author acer
 */
public class UserDTO {
    private int userID;
    private String fullName;
    private String phone;
    private String email;
    private String password;
    private String address;  
    private int status;
    private String role;
    
    public UserDTO(String fullName, String phone, String email, String password, String address, int status, String role) {
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.address = address;
        this.status = status;
        this.role = role;
    }
    
    
    public UserDTO(int userID, String fullName, String phone, String email, String password, String address, int status, String role) {
        this.userID = userID;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.address = address;
        this.status = status;
        this.role = role;
    }

    public UserDTO() {
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
}
