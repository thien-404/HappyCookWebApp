/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Product;

/**
 *
 * @author acer
 */
public class IngredientDTO {
    private int  ingredientId;
    private String ingredientName;
    private double ingredientPrice;
    private int ingredientStatus;
    private String unit;
    private String role;
    private int quantity;
    private int mealId;

    public IngredientDTO() {
    }

    public IngredientDTO(String ingredientName, double ingredientPrice, int ingredientStatus, String unit, String role) {
        this.ingredientName = ingredientName;
        this.ingredientPrice = ingredientPrice;
        this.ingredientStatus = ingredientStatus;
        this.unit = unit;
        this.role = role;
    }


    
    public IngredientDTO( int mealId, int ingredientId, int quantity) {
        this.ingredientId = ingredientId;
        this.quantity = quantity;
        this.mealId = mealId;
    }

    
    
    public IngredientDTO(int ingredientId, String ingredientName, double ingredientPrice, int ingredientStatus, String unit, String role) {
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.ingredientPrice = ingredientPrice;
        this.ingredientStatus = ingredientStatus;
        this.unit = unit;
        this.role = role;
    }

    public IngredientDTO(int ingredientId, String ingredientName, double ingredientPrice, String unit, String role, int quantity) {
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.ingredientPrice = ingredientPrice;
        this.unit = unit;
        this.role = role;
        this.quantity = quantity;
    }
    
    
    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public double getIngredientPrice() {
        return ingredientPrice;
    }

    public void setIngredientPrice(double ingredientPrice) {
        this.ingredientPrice = ingredientPrice;
    }

    public int getIngredientStatus() {
        return ingredientStatus;
    }

    public void setIngredientStatus(int ingredientStatus) {
        this.ingredientStatus = ingredientStatus;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
       
    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }
    
}
