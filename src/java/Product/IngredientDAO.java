/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import mylib.DButil;

/**
 *
 * @author acer
 */
public class IngredientDAO {

    public ArrayList<IngredientDTO> getAllIngredient() {
        ArrayList<IngredientDTO> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                String sql = "Select * from Ingredient where IngredientStatus = 1";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                if (rs != null) {
                    while (rs.next()) {
                        int ingredientId = rs.getInt("IngredientID");
                        String ingredientName = rs.getString("IngredientName");
                        double ingredientPrice = rs.getDouble("IngredientPrice");
                        int ingredientStatus = rs.getInt("IngredientStatus");
                        String unit = rs.getString("Unit");
                        String role = rs.getString("Role");
                        IngredientDTO ingre = new IngredientDTO(ingredientId, ingredientName, ingredientPrice, ingredientStatus, unit, role);
                        list.add(ingre);
                    }
                }
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
        return list;
    }
    
    
        public ArrayList<IngredientDTO> getAllDeletedIngredient() {
        ArrayList<IngredientDTO> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                String sql = "Select * from Ingredient where IngredientStatus = 0";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                if (rs != null) {
                    while (rs.next()) {
                        int ingredientId = rs.getInt("IngredientID");
                        String ingredientName = rs.getString("IngredientName");
                        double ingredientPrice = rs.getDouble("IngredientPrice");
                        int ingredientStatus = rs.getInt("IngredientStatus");
                        String unit = rs.getString("Unit");
                        String role = rs.getString("Role");
                        IngredientDTO ingre = new IngredientDTO(ingredientId, ingredientName, ingredientPrice, ingredientStatus, unit, role);
                        list.add(ingre);
                    }
                }
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
        return list;
    }
    //lay danh sach nguyen lieu theo mealid
    public ArrayList<IngredientDTO> getMealIngredient(String mealId) {
        ArrayList<IngredientDTO> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                String sql = "select mi.IngredientID,i.IngredientName,i.IngredientPrice ,mi.Quantity,i.Unit, i.Role from dbo.Meal m inner join dbo.MealIngredient mi on m.MealID=mi.MealID inner join dbo.Ingredient i on mi.IngredientID=i.IngredientID where m.MealID = " + mealId;
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                if (rs != null) {
                    while (rs.next()) {
                        int ingredientId = rs.getInt("IngredientID");
                        String ingredientName = rs.getString("IngredientName");
                        double ingredientPrice = rs.getDouble("IngredientPrice");
                        int ingredientQuantity = rs.getInt("Quantity");
                        String unit = rs.getString("Unit");
                        String role = rs.getString("Role");
                        IngredientDTO ingre = new IngredientDTO(ingredientId, ingredientName, ingredientPrice, unit, role, ingredientQuantity);
                        list.add(ingre);
                    }
                }
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
        return list;
    }

    // chưa dùng
    public int getIngredientIdByName(String ingredientName) {
        String sql = "SELECT IngredientID FROM Ingredient WHERE IngredientName like ?";
        Connection cn = null;
        int ingredientId = 0;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + ingredientName + "%");
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    ingredientId = rs.getInt("IngredientID");
                }
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
        return ingredientId;

    }
//save ingredient in meal at editMeal.jsp

    public boolean saveIngredientMeal(ArrayList<IngredientDTO> ingredients) {
        String sql = "INSERT INTO MealIngredient (MealID, IngredientID, Quantity) VALUES (?, ?, ?)";
        Connection cn = null;
        PreparedStatement pst = null;
        boolean success = false;

        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false); // Bắt đầu transaction

                pst = cn.prepareStatement(sql);
                for (IngredientDTO ingredient : ingredients) {
                    pst.setInt(1, ingredient.getMealId());
                    pst.setInt(2, ingredient.getIngredientId());
                    pst.setInt(3, ingredient.getQuantity());
                    pst.addBatch();
                }

                int[] rs = pst.executeBatch(); // Thực hiện batch insert

                // Kiểm tra kết quả
                if (rs.length == ingredients.size()) {
                    success = true;
                }

                if (success) {
                    cn.commit(); // Commit transaction nếu thành công
                } else {
                    cn.rollback(); // Rollback transaction nếu có lỗi
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (cn != null) {
                    cn.setAutoCommit(true); // Đặt lại auto commit về true
                    cn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return success;
    }

    public boolean insertIngredient(IngredientDTO ingredient) {
        String sql = "INSERT INTO Ingredient (IngredientName, IngredientPrice, IngredientStatus, Unit, Role) VALUES (?, ?, ?, ?,?)";
        Connection cn = null;
        boolean rs = false;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, ingredient.getIngredientName());
                pst.setDouble(2, ingredient.getIngredientPrice());
                pst.setInt(3, ingredient.getIngredientStatus());
                pst.setString(4, ingredient.getUnit());
                pst.setString(5, ingredient.getRole());
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

    public boolean updateIngredient(IngredientDTO ingredient) {
        String sql = "UPDATE Ingredient SET IngredientName = ?, IngredientPrice = ? , Unit = ?, Role = ? WHERE IngredientID = ?";
        Connection cn = null;
        boolean rs = false;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setNString(1, ingredient.getIngredientName());
                pst.setDouble(2, ingredient.getIngredientPrice());
                pst.setNString(3, ingredient.getUnit());
                pst.setNString(4, ingredient.getRole());
                pst.setInt(5, ingredient.getIngredientId());
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

    
    //dùng để update mealStatus = 0 khi mà có ingredientStatus = 0
    public boolean updateIngredientForMeal() {
        String sql = "UPDATE Meal SET MealStatus = 0\n"
                + "FROM Meal me\n"
                + "JOIN MealIngredient mi ON me.MealID = mi.MealID\n"
                + "JOIN Ingredient i ON mi.IngredientID = i.IngredientID\n"
                + "WHERE i.IngredientStatus = 0";
        Connection cn = null;
        boolean rs = false;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(sql);
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
    
    public boolean deleteIngredient(IngredientDTO ingredient) {
        String sql = "UPDATE Ingredient SET IngredientStatus = 0 WHERE IngredientID = ?";
        Connection cn = null;
        boolean rs = false;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, ingredient.getIngredientId());
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
    
    public boolean restoreIngredient(IngredientDTO ingredient) {
        String sql = "UPDATE Ingredient SET IngredientStatus = 1 WHERE IngredientID = ?";
        Connection cn = null;
        boolean rs = false;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, ingredient.getIngredientId());
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
