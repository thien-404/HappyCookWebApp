/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Order;


import java.sql.Timestamp;

/**
 *
 * @author HP
 */
public class OrderDTO {
    private int orderId;
    private int accId;
    private Timestamp orderDate;
    private double totalPrice;
    private int status;   
    private String fullName;
    private String address;

    public OrderDTO(int orderId, Timestamp orderDate, double totalPrice, int status, String fullName, String address) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.status = status;
        this.fullName = fullName;
        this.address = address;
    }


    
    public OrderDTO() {
    }

    public OrderDTO(int orderId, int accId, Timestamp orderDate, double totalPrice, int status) {
        this.orderId = orderId;
        this.accId = accId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public OrderDTO(int accId, Timestamp orderDate, double totalPrice, int status) {
        this.accId = accId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.status = status;
    }
    

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }


    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
        public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
    
}
