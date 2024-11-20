/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Order;

public class OrderMealDTO {

    private int mealId;
    private int orderId;
    private int quantity;
    private String mealName;
    private double mealPrice;
    private String imageUrl;
    private int typeOfFood;
    private int isIngredient;
    private int userId;

    public OrderMealDTO() {
    }
    
    
    
    public OrderMealDTO(int mealId, int orderId, int quantity, String mealName, double mealPrice, String imageUrl, int typeOfFood, int isIngredient) {
        this.mealId = mealId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.mealName = mealName;
        this.mealPrice = mealPrice;
        this.imageUrl = imageUrl;
        this.typeOfFood = typeOfFood;
        this.isIngredient = isIngredient;
    }

    public OrderMealDTO(int mealId, int orderId, int quantity, String mealName, double mealPrice, String imageUrl, int typeOfFood) {
        this.mealId = mealId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.mealName = mealName;
        this.mealPrice = mealPrice;
        this.imageUrl = imageUrl;
        this.typeOfFood = typeOfFood;
    }

    public OrderMealDTO(int mealId, int orderId, int quantity) {
        this.mealId = mealId;
        this.orderId = orderId;
        this.quantity = quantity;
    }

    public OrderMealDTO(int mealId, int orderId, int quantity, int isIngredient) {
        this.mealId = mealId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.isIngredient = isIngredient;
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public double getMealPrice() {
        return mealPrice;
    }

    public void setMealPrice(double mealPrice) {
        this.mealPrice = mealPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getTypeOfFood() {
        return typeOfFood;
    }

    public void setTypeOfFood(int typeOfFood) {
        this.typeOfFood = typeOfFood;
    }

    public int getIsIngredient() {
        return isIngredient;
    }

    public void setIsIngredient(int isIngredient) {
        this.isIngredient = isIngredient;
    }

}
