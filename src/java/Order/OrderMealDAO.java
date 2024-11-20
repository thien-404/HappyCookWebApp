/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import mylib.DButil;

/**
 *
 * @author HP
 */
public class OrderMealDAO {
    public boolean insertOrderMeal(OrderMealDTO orderMeal) {
        String sql = "insert into dbo.OrderMeal(MealID,OrderId, Quantity, IsIngredient) values (?,?,?,?)";
        boolean rs = false;
        Connection cn = null;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(sql);               
                pst.setInt(1, orderMeal.getMealId());
                pst.setInt(2, orderMeal.getOrderId());
                pst.setInt(3, orderMeal.getQuantity()); 
                pst.setInt(4, orderMeal.getIsIngredient());
                rs = pst.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rs;

    }
}
