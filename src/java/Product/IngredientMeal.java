/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Product;

import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class IngredientMeal {
    private int mealId;    
    private int ingredientId;
    private int quantity;
    private double totalPrice;
    private String mealName;
    private ArrayList<IngredientDTO> ingreList;
    
    public IngredientMeal() {
    }

    public IngredientMeal(int mealId, String mealName, ArrayList<IngredientDTO> ingreList) {
        this.mealId = mealId;
        this.mealName = mealName;
        this.ingreList = ingreList;
        this.totalPrice = findTotalPrice(ingreList);
    }

    public ArrayList<IngredientDTO> getIngreList() {
        return ingreList;
    }

    public void setIngreList(ArrayList<IngredientDTO> ingreList) {
        this.ingreList = ingreList;
    }
     
    
    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public static double findTotalPrice(ArrayList<IngredientDTO> ingreList){
        double sum = 0;
        for(IngredientDTO i : ingreList){
            sum += i.getIngredientPrice()*i.getQuantity();
        }
        return sum;
    }
}
