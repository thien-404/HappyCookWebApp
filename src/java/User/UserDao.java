/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

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
public class UserDao {

    private static final String LOGIN = "SELECT Accid, fullName,Phone, role,Email ,address FROM Account WHERE Email = ? AND Password COLLATE Latin1_General_BIN = ?  AND status = 1";
    private static final String INSERT = "INSERT INTO Account (FullName, Phone, Email, Password, Address, Status, Role) "
            + "           VALUES(?,?,?,?,?,?,?)";
    private static final String GET_ALL_USERS_SORTED = "SELECT * FROM Account WHERE status = 1 ORDER BY Role, FullName";
    private static final String UPDATE = "UPDATE Account SET FullName = ?, Password = ?, Email = ?, Address = ?, Phone = ?  WHERE AccId = ?";
    private static final String SEARCHBYEMAILORPHONE = "SELECT AccId, FullName, Phone, Email, Password, Address, Status, Role FROM Account WHERE Status = 1 and Email LIKE ? OR Phone LIKE ?";
    private static final String BLOCK = "UPDATE Account SET Status = 0 WHERE AccId = ?";
    private static final String UNBLOCK = "UPDATE Account SET Status = 1 WHERE AccId = ?";
    private static final String SEARCHUSERBLOCK = "SELECT AccId, FullName, Phone, Email, Password, Address, Status, Role FROM Account WHERE Status = 0 and Email LIKE ? OR Phone LIKE ?";
    private static final String SEARCHBYEMAIL = "SELECT * FROM Account WHERE Email like ? AND status = 1";

    //
    public ArrayList<UserDTO> getAllUserSortedByName() {
        ArrayList<UserDTO> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                String sql = GET_ALL_USERS_SORTED;
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    int userID = rs.getInt("AccId");
                    String fullName = rs.getString("FullName");
                    String phone = rs.getString("Phone");
                    String email = rs.getString("Email");
                    String password = rs.getString("Password");
                    String address = rs.getString("Address");
                    int status = rs.getInt("Status");
                    String role = rs.getString("role");
                    UserDTO user = new UserDTO(userID, fullName, phone, email, password, address, status, role);
                    list.add(user);
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

    //search user by phone or email
    public ArrayList<UserDTO> searchListUsersByEmailOrPhone(String search) {
        ArrayList<UserDTO> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                String sql = SEARCHBYEMAILORPHONE;
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + search + "%");
                pst.setString(2, "%" + search + "%");
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int userID = rs.getInt("AccId");
                        String fullName = rs.getString("FullName");
                        String phone = rs.getString("Phone");
                        String email = rs.getString("Email"); // Lấy giá trị email từ cơ sở dữ liệu
                        String password = rs.getString("Password");
                        String address = rs.getString("Address");
                        int status = rs.getInt("Status");
                        String role = rs.getString("Role");
                        UserDTO user = new UserDTO(userID, fullName, phone, email, password, address, status, role);
                        list.add(user);
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
    
    public UserDTO getUser(String email) {
        UserDTO user = null;
        Connection cn = null;
        try {
            //B1 tạo kết nối
            cn = DButil.makeConnection();
            if (cn != null) {
                String sql = SEARCHBYEMAIL;
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + email + "%");
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int userID = rs.getInt("AccId");
                        String fullName = rs.getString("FullName");
                        String phone = rs.getString("Phone");
                        String password = rs.getString("Password");
                        String address = rs.getString("Address");
                        int status = rs.getInt("Status");
                        String role = rs.getString("role");
                        user = new UserDTO(userID, fullName, phone, email, password, address, status, role);
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
        return user;
    }

    //chưa dùng
    public UserDTO getUser(int userid) {
        UserDTO user = null;
        Connection cn = null;
        try {

            cn = DButil.makeConnection();
            if (cn != null) {
                String sql = "";// GET_USER;
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, userid);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int userID = rs.getInt("AccId");
                    String fullName = rs.getString("FullName");
                    String phone = rs.getString("Phone");
                    String email = rs.getString("Email");
                    String password = rs.getString("Password");
                    String address = rs.getString("Address");
                    int status = rs.getInt("Status");
                    String role = rs.getString("role");
                    user = new UserDTO(userID, fullName, phone, email, password, address, status, role);
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
        return user;
    }

    //Check Login
    public UserDTO checkLogin(String email, String password) {
        UserDTO user = null;
        Connection cn = null;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                String sql = LOGIN;
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, password);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int userID = rs.getInt("AccId");
                    String fullName = rs.getString("FullName");
                    String role = rs.getString("role");
                    String phone = rs.getString("Phone");
                    String address = rs.getString("Address");
                    user = new UserDTO(userID, fullName, phone, email, password, address, 1, role);
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
        return user;
    }

    //Insert user
    public int InsertUser(UserDTO user) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(INSERT);
                pst.setNString(1, user.getFullName());
                pst.setString(2, user.getPhone());
                pst.setString(3, user.getEmail());
                pst.setString(4, user.getPassword());
                pst.setNString(5, user.getAddress());
                pst.setInt(6, user.getStatus());
                pst.setString(7, user.getRole());
                rs = pst.executeUpdate();
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

    public int updateUser(UserDTO user) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(UPDATE);
                pst.setNString(1, user.getFullName());
                pst.setString(2, user.getPassword());
                pst.setString(3, user.getEmail());
                pst.setNString(4, user.getAddress());
                pst.setString(5, user.getPhone());
                pst.setInt(6, user.getUserID());
                rs = pst.executeUpdate();
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

    public ArrayList<UserDTO> getAllUserBlock() {
        ArrayList<UserDTO> list = new ArrayList<>();
        Connection cn = null;
        try {
            //B1 tạo kết nối
            cn = DButil.makeConnection();
            if (cn != null) {
                String sql = "Select * from Account where status = 0";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                if (rs != null) {
                    while (rs.next()) {
                        int userID = rs.getInt("AccId");
                        String fullName = rs.getString("FullName");
                        String phone = rs.getString("Phone");
                        String email = rs.getString("Email");
                        String password = rs.getString("Password");
                        String address = rs.getString("Address");
                        int status = rs.getInt("Status");
                        String role = rs.getString("role");
                        UserDTO user = new UserDTO(userID, fullName, phone, email, password, address, status, role);
                        list.add(user);
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
    
         //search blocked user by phone or email
    public ArrayList<UserDTO> searchListUsersBlock(String search) {
        ArrayList<UserDTO> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                String sql = SEARCHUSERBLOCK;
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + search + "%");
                pst.setString(2, "%" + search + "%");
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int userID = rs.getInt("AccId");
                        String fullName = rs.getString("FullName");
                        String phone = rs.getString("Phone");
                        String email = rs.getString("Email"); // Lấy giá trị email từ cơ sở dữ liệu
                        String password = rs.getString("Password");
                        String address = rs.getString("Address");
                        int status = rs.getInt("Status");
                        String role = rs.getString("Role");
                        UserDTO user = new UserDTO(userID, fullName, phone, email, password, address, status, role);
                        list.add(user);
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

    
    public int blockUser(UserDTO user) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {

                PreparedStatement pst = cn.prepareStatement(BLOCK);
                pst.setInt(1, user.getUserID());
                rs = pst.executeUpdate();
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

    public int unBlockUser(UserDTO user) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {

                PreparedStatement pst = cn.prepareStatement(UNBLOCK);
                pst.setInt(1, user.getUserID());
                rs = pst.executeUpdate();
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

    public int getTotalUser() {
        int total = 0;
        Connection cn = null;
        try {
            cn = DButil.makeConnection();
            if (cn != null) {
                String sql = "SELECT COUNT(*) FROM Account";
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    total = rs.getInt(1);
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
        return total;
    }
    
    
}
