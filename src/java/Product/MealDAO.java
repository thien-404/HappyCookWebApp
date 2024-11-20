/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Product;

import Product.MealDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import mylib.DButil;

/**
 *
 * @author HP
 */
public class MealDAO {

    //Lay het tat ca mon an ra
    public ArrayList<MealDTO> getAllMeal() {
        ArrayList<MealDTO> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                String sql = "Select * from Meal where MealStatus = 1";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                if (rs != null) {
                    while (rs.next()) {
                        int mealId = rs.getInt("MealID");
                        String mealName = rs.getString("MealName");
                        double mealPrice = rs.getDouble("MealPrice");
                        String content = rs.getString("Content");
                        String decription = rs.getString("Description");
                        String imageUrl = rs.getString("ImageUrl");
                        int mealStatus = rs.getInt("MealStatus");
                        int discountId = rs.getInt("discountID");
                        int typeOfFood = rs.getInt("typeOfFood");
                        MealDTO meal = new MealDTO(mealId, mealName, mealPrice, content, decription, imageUrl, mealStatus, discountId, typeOfFood);
                        list.add(meal);
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

    //Lay mon an theo loai
    public ArrayList<MealDTO> getMealType(String type) {
        ArrayList<MealDTO> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                String sql = "Select * from Meal where MealStatus = 1 and typeOfFood=" + type;
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                if (rs != null) {
                    while (rs.next()) {
                        int mealId = rs.getInt("MealID");
                        String mealName = rs.getString("MealName");
                        double mealPrice = rs.getDouble("MealPrice");
                        String content = rs.getString("Content");
                        String decription = rs.getString("Description");
                        String imageUrl = rs.getString("ImageUrl");
                        int mealStatus = rs.getInt("MealStatus");
                        int discountId = rs.getInt("discountID");
                        int typeOfFood = rs.getInt("typeOfFood");
                        MealDTO meal = new MealDTO(mealId, mealName, mealPrice, content, decription, imageUrl, mealStatus, discountId, typeOfFood);
                        list.add(meal);
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

    //lay danh sach mon an theo ten
    public ArrayList<MealDTO> getMealToName(String mName) {
        ArrayList<MealDTO> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                String sql = "select * from Meal where [MealName] like '%" + mName + "%'";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                if (rs != null) {
                    while (rs.next()) {
                        int mealId = rs.getInt("MealID");
                        String mealName = rs.getString("MealName");
                        double mealPrice = rs.getDouble("MealPrice");
                        String content = rs.getString("Content");
                        String decription = rs.getString("Description");
                        String imageUrl = rs.getString("ImageUrl");
                        int mealStatus = rs.getInt("MealStatus");
                        int discountId = rs.getInt("discountID");
                        int typeOfFood = rs.getInt("typeOfFood");
                        MealDTO meal = new MealDTO(mealId, mealName, mealPrice, content, decription, imageUrl, mealStatus, discountId, typeOfFood);
                        list.add(meal);
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

    //lay mon an moi nhat
    public MealDTO getLatestMeal() {
        MealDTO meal = new MealDTO();
        Connection cn = null;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                String sql = "select * from dbo.Meal where MealStatus = 1 and MealID=(select MAX(MealID)from dbo.Meal)";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                if (rs != null) {
                    while (rs.next()) {
                        int mealId = rs.getInt("MealID");
                        String mealName = rs.getString("MealName");
                        double mealPrice = rs.getDouble("MealPrice");
                        String content = rs.getString("Content");
                        String decription = rs.getString("Description");
                        String imageUrl = rs.getString("ImageUrl");
                        int mealStatus = rs.getInt("MealStatus");
                        int discountId = rs.getInt("discountID");
                        int typeOfFood = rs.getInt("typeOfFood");
                        meal = new MealDTO(mealId, mealName, mealPrice, content, decription, imageUrl, mealStatus, discountId, typeOfFood);
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
        return meal;
    }

    //lay mon an bị delete
    public ArrayList<MealDTO> getAllMealDeleted() {
        ArrayList<MealDTO> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                String sql = "Select * from Meal where MealStatus = 0";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                if (rs != null) {
                    while (rs.next()) {
                        int mealId = rs.getInt("MealID");
                        String mealName = rs.getString("MealName");
                        double mealPrice = rs.getDouble("MealPrice");
                        String content = rs.getString("Content");
                        String decription = rs.getString("Description");
                        String imageUrl = rs.getString("ImageUrl");
                        int mealStatus = rs.getInt("MealStatus");
                        int discountId = rs.getInt("discountID");
                        int typeOfFood = rs.getInt("typeOfFood");
                        MealDTO meal = new MealDTO(mealId, mealName, mealPrice, content, decription, imageUrl, mealStatus, discountId, typeOfFood);
                        list.add(meal);
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

    //lay mon an rieng le
    public MealDTO getMealToId(String mId) {
        MealDTO meal = new MealDTO();
        Connection cn = null;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                String sql = "select * from dbo.Meal where MealID=" + mId;
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                if (rs != null) {
                    while (rs.next()) {
                        int mealId = rs.getInt("MealID");
                        String mealName = rs.getString("MealName");
                        double mealPrice = rs.getDouble("MealPrice");
                        String content = rs.getString("Content");
                        String decription = rs.getString("Description");
                        String imageUrl = rs.getString("ImageUrl");
                        int mealStatus = rs.getInt("MealStatus");
                        int discountId = rs.getInt("discountID");
                        int typeOfFood = rs.getInt("typeOfFood");
                        meal = new MealDTO(mealId, mealName, mealPrice, content, decription, imageUrl, mealStatus, discountId, typeOfFood);
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
        return meal;
    }

    //Insert meal by admin
    public boolean insertMeal(MealDTO meal) {
        String sql = "INSERT INTO Meal (MealName, MealPrice, Content, Description, ImageUrl, MealStatus, typeOfFood,discountID) VALUES(?,?,?,?,?,?,?,?)";
        boolean rs = false;
        Connection cn = null;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setNString(1, meal.getMealName());
                pst.setDouble(2, meal.getMealPrice());
                pst.setNString(3, meal.getContent());
                pst.setNString(4, meal.getDecription());
                pst.setNString(5, meal.getImageUrl());
                pst.setInt(6, meal.getMealStatus());
                pst.setInt(7, meal.getTypeOfFood());
                pst.setInt(8, meal.getDiscountId());
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

    public boolean updateMeal(MealDTO meal) {
        String sql = "UPDATE Meal set MealName = ?, Content = ?, Description = ?, MealPrice = ?,ImageUrl = ?, discountID = ?, typeOfFood = ? where MealID = ?";
        boolean rs = false;
        Connection cn = null;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setNString(1, meal.getMealName());
                pst.setNString(2, meal.getContent());
                pst.setNString(3, meal.getDecription());
                pst.setDouble(4, meal.getMealPrice());
                pst.setNString(5, meal.getImageUrl());
                pst.setInt(6, meal.getDiscountId());
                pst.setInt(7, meal.getTypeOfFood());
                pst.setInt(8, meal.getMealId());
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

    public boolean RestoreMeal(MealDTO meal) {
        String sql = "UPDATE Meal set MealStatus = 1 where MealID = ?";
        boolean rs = false;
        Connection cn = null;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, meal.getMealId());
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

    public boolean deleteMeal(MealDTO meal) {
        String sql = "UPDATE Meal set MealStatus = 0 where MealID = ?";
        boolean rs = false;
        Connection cn = null;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, meal.getMealId());
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
