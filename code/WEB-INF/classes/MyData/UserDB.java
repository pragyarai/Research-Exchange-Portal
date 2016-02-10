/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyData;

import Mypackage.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

/**
 *
 * @author pragyarai
 */
public  class UserDB {
    
    
    public static HashMap<String,User> myUser = new HashMap<String,User>();
    public static  java.util.Date dt = new java.util.Date();
    public static  java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String getStudy(String code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   public UserDB()
   {
       
   }
   
   public static boolean validateUser(String email, String password)
   {
       System.out.println("password"+password);
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String salt;
        String saltPassword;
        String query = "SELECT * FROM UserPass "
                + "WHERE email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if(rs.next())
            { 
               saltPassword= rs.getString("password");
               
               if(saltPassword.equals(password))
               {
                   return true;
               }
            }
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return false;
   }
   public static void deleteTemp(String token)
   {
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
          String query = "DELETE FROM TempUser "
                + "WHERE token = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, token);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e);
            
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
   }
   public static void deleteTempEmail(String email)
   {
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
          String query = "DELETE FROM TempUser "
                + "WHERE email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e);
            
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
   }
   public static String getSalt(String email)
   {
       String salt=null;
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM UserPass "
                + "WHERE email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            
            if (rs.next()) {
              
               salt= rs.getString("salt");
                
            }
            return salt;
           
            
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
   }
    public static User activateUser(String token)
   {
       User user= new User();
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM TempUser "
                + "WHERE token = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, token);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                user = new User();
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                 return user;
            }
            else
            {
                return null;
            }
           
           
            
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
   }

    
    public static String getTime(String token)
   {
       String expiryTime=null;
       
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM TempUser "
                + "WHERE token = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, token);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                
             expiryTime= rs.getString("issueDate");
               
                
            }
            return expiryTime;
           
            
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
   }
    
   public static void addUser(User user,String password, String salt)
   {
       
       System.out.println("Empty or not"+myUser.isEmpty());
       myUser.put(user.getEmail(), user);
    
       try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                = "INSERT INTO UserPass ( email,password,salt) "
                + "VALUES (?, ?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getEmail());
            ps.setString(2, password);
            ps.setString(3, salt);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
           
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
           
        }
        
        
   }
   public void addUser(User user)
   {
       try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
       String query
                = "INSERT INTO User (name, email) "
                + "VALUES (?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
           
        } 
        finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
      
       
   }
    public static void tempUser(User user, UUID token)
   {
       String sToken=token.toString();
       String currentTime = sdf.format(dt);
       try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
       String query
                = "INSERT INTO TempUser (name, email,issueDate,token) "
                + "VALUES (?, ?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, currentTime);
            ps.setString(4, sToken);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
           
        } 
        finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
      
       
   }
   public static User getUser(String email) 
   {
        User user =null;
        
        try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM User "
                + "WHERE email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                user = new User();
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setParticipants(rs.getInt("participants"));
                user.setParticipation(rs.getInt("participation"));
                user.setCoins(rs.getInt("coins"));
            }
            return user;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
   }
   public static void update(User user) {
        try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE User SET "
                + "name = ?, "
                + "participation = ?, "
                + "participants = ?, "
                + "coins = ? "
                + "WHERE email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getName());
            ps.setInt(2, user.getParticipation());
            ps.setInt(3, user.getParticipants());
            ps.setInt(4, user.getCoins());
            ps.setString(5, user.getEmail());
             ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
   
   public static void updatePassword(User user, String password, String salt) {
        try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE UserPass SET "
                + "password = ?,"
                + "salt=?"
                + "WHERE email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1,password );
            ps.setString(2, salt);
            ps.setString(3, user.getEmail());
             ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    public User getUser() 
   {
       return null;
   }

    
}

