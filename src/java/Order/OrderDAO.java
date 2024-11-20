package Order;

import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import mylib.DButil;

public class OrderDAO {

    public boolean insertOrder(OrderDTO order) {
        String sql = "insert into Orders ( AccId, order_date, TotalPrices, Status) values (?,?,?,?)";
        boolean rs = false;
        Connection cn = null;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, order.getAccId());
                pst.setTimestamp(2, order.getOrderDate());
                pst.setDouble(3, order.getTotalPrice());
                pst.setInt(4, order.getStatus());
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

    public ArrayList<OrderDTO> getActiveOrders() {
        ArrayList<OrderDTO> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                String sql = "select o.OrderId, a.FullName,  o.TotalPrices, o.Status, a.Address, o.order_date\n"
                        + "from Orders o, Account a where o.AccId = a.AccId\n  AND o.Status in (1,2)"
                        + "order by o.Status asc, OrderId desc, a.Address desc";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                if (rs != null) {
                    while (rs.next()) {
                        int orderId = rs.getInt("OrderId");
                        String FullName = rs.getString("FullName");
                        double totalPrice = rs.getDouble("TotalPrices");
                        int status = rs.getInt("Status");
                        String address = rs.getString("Address");
                        Timestamp orderDate = rs.getTimestamp("order_date");
                        OrderDTO order = new OrderDTO(orderId, orderDate, totalPrice, status, FullName, address);
                        list.add(order);
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

    public int getNewestOrderId() {
        int orderId = 0;
        Connection cn = null;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                String sql = "select orderId from dbo.Orders where OrderId = (select MAX(OrderId) from Orders)";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                if (rs != null) {
                    while (rs.next()) {
                        orderId = rs.getInt("orderId");
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
        return orderId;
    }

    public ArrayList<OrderDTO> getListOfOrders() {
        ArrayList<OrderDTO> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                String sql = "select o.OrderId, a.FullName, o.order_date, o.TotalPrices, o.Status, a.Address \n"
                        + "from Orders o, Account a where o.AccId = a.AccId\n"
                        + "order by o.Status asc, OrderId desc, a.Address desc";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                if (rs != null) {
                    while (rs.next()) {
                        int orderId = rs.getInt("OrderId");
                        String name = rs.getString("FullName");
                        Timestamp orderDate = rs.getTimestamp("order_date");
                        double totalPrice = rs.getDouble("TotalPrices");
                        int status = rs.getInt("Status");
                        OrderDTO order = new OrderDTO(orderId, orderDate, totalPrice, status, name, "");
                        list.add(order);
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

    public ArrayList<OrderDTO> getOrdersByDate(Date searchDate) {
        ArrayList<OrderDTO> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                String sql = "select o.OrderId, a.FullName, o.order_date, o.TotalPrices, o.Status\n"
                        + "from Orders o, Account a where o.AccId = a.AccId and CAST(order_date AS DATE) = ?\n"
                        + "order by o.Status asc, OrderId desc";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setDate(1, searchDate);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int orderId = rs.getInt("OrderId");
                        String name = rs.getString("FullName");
                        Timestamp orderDate = rs.getTimestamp("order_date");
                        double totalPrice = rs.getDouble("TotalPrices");
                        int status = rs.getInt("Status");
                        OrderDTO order = new OrderDTO(orderId, orderDate, totalPrice, status, name, "");
                        list.add(order);
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

    public int getTotalOrders() {
        int totalOrder = 0;
        Connection cn = null;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                String sql = "SELECT COUNT(*) FROM Orders";
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    totalOrder = rs.getInt(1);
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
        return totalOrder;
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        Connection cn = null;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                String sql = "select sum(TotalPrices) FROM Orders where Status = 3";
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    totalPrice = rs.getDouble(1);
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
        return totalPrice;
    }

    public boolean UpdateOrders(OrderDTO order) {
        Connection cn = null;
        boolean rs = false;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                String sql = "Update Orders set Status = ? where OrderId = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, order.getStatus());
                pst.setInt(2, order.getOrderId());
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

    public OrderDTO getOrderById(int orderId) {
        Connection cn = null;
        OrderDTO order = null;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                String sql = "SELECT * FROM Orders WHERE OrderId = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, orderId);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    int accId = rs.getInt("AccId");
                    Timestamp orderDate = rs.getTimestamp("order_date");
                    double totalPrice = rs.getDouble("TotalPrices");
                    int status = rs.getInt("Status");
                    order = new OrderDTO(orderId, accId, orderDate, totalPrice, status);
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
        return order;
    }

    public List<OrderMealDTO> getOrderMealDetails(int orderId) {
        Connection cn = null;
        List<OrderMealDTO> meals = new ArrayList<>();
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                String sql = "SELECT m.MealID, m.MealName, m.ImageUrl, m.MealPrice, om.Quantity, m.typeOfFood, om.IsIngredient\n"
                        + "FROM Meal m \n"
                        + "JOIN OrderMeal om ON m.MealID = om.MealID \n"
                        + "WHERE om.OrderId = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, orderId);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    int mealId = rs.getInt("MealID");
                    String mealName = rs.getString("MealName");
                    String imageUrl = rs.getString("ImageUrl");
                    double mealPrice = rs.getDouble("MealPrice");
                    int quantity = rs.getInt("Quantity");
                    int typeOfFood = rs.getInt("typeOfFood");
                    int IsIngredient = rs.getInt("IsIngredient");
                    meals.add(new OrderMealDTO(mealId, orderId, quantity, mealName, mealPrice, imageUrl, typeOfFood, IsIngredient));
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
        return meals;
    }

    public Map<String, List<OrderDTO>> getOrderHistory(int userId) {
        Map<String, List<OrderDTO>> orderHistory = new LinkedHashMap<>();
        Connection cn = null;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                String sql = "select OrderId, order_date, TotalPrices, Status\n"
                        + "from Orders where AccId = ?\n"
                        + "order by  order_date desc, status asc, OrderId desc";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setInt(1, userId);
                ResultSet rs = st.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int orderId = rs.getInt("OrderId");
                        Timestamp orderDate = rs.getTimestamp("order_date");
                        double totalPrice = rs.getDouble("TotalPrices");
                        int status = rs.getInt("Status");
                        OrderDTO order = new OrderDTO(orderId, userId, orderDate, totalPrice, status);

                        String date = new SimpleDateFormat("yyyy-MM-dd").format(orderDate);
                        if (!orderHistory.containsKey(date)) {
                            orderHistory.put(date, new ArrayList<>());
                        }
                        orderHistory.get(date).add(order);
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
        return orderHistory;
    }

    public boolean CancelOrders(int order) {
        Connection cn = null;
        boolean rs = false;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                String sql = "Update Orders set Status = 4 where OrderId = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, order);

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
































    /*

    */
