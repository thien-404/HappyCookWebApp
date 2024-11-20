/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Product;

/**
 *
 * @author HP
 */
public class MealDTO {
    private int mealId;
    private String mealName;
    private double mealPrice;
    private String content;
    private String decription;
    private String imageUrl;
    private int mealStatus;
    private int discountId;
    private int typeOfFood;
    
    public MealDTO() {
    }

    public MealDTO(int mealId, String mealName, double mealPrice, String content, String decription, String imageUrl, int mealStatus, int discountId, int typeOfFood) {
        this.mealId = mealId;
        this.mealName = mealName;
        this.mealPrice = mealPrice;
        this.content = content;
        this.decription = decription;
        this.imageUrl = imageUrl;
        this.mealStatus = mealStatus;
        this.discountId = discountId;
        this.typeOfFood = typeOfFood;
    }

    public MealDTO(String mealName, double mealPrice, String content, String decription, String imageUrl, int mealStatus, int discountId, int typeOfFood) {
        this.mealName = mealName;
        this.mealPrice = mealPrice;
        this.content = content;
        this.decription = decription;
        this.imageUrl = imageUrl;
        this.mealStatus = mealStatus;
        this.discountId = discountId;
        this.typeOfFood = typeOfFood;
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

    public double getMealPrice() {
        return mealPrice;
    }

    public void setMealPrice(double mealPrice) {
        this.mealPrice = mealPrice;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getMealStatus() {
        return mealStatus;
    }

    public void setMealStatus(int mealStatus) {
        this.mealStatus = mealStatus;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public int getTypeOfFood() {
        return typeOfFood;
    }

    public void setTypeOfFood(int typeOfFood) {
        this.typeOfFood = typeOfFood;
    }
    
}
