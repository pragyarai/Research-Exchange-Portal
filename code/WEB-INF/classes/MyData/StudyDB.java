package MyData;

import Mypackage.Answer;
import Mypackage.Study;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.*;

public class StudyDB {
          
    int count=1;
    java.util.Date dt = new java.util.Date();
    java.text.SimpleDateFormat sdf = 
     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static HashMap<String, Study> studies = new HashMap<String, Study>(); 
       
    public StudyDB() {
        
    }
         
    public void addStudies(Study study){         //take this function
     
        String code;
        code= codeGenerator();
        study.setCode(code);
        String currentTime = sdf.format(dt);
        studies.put(code,study);
        
        try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO Study (name, code, description, email, dateCreated, question, requestedParticipants , status) "
                + "VALUES (?, ?,?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, study.getName());
            ps.setString(2, code);
            ps.setString(3, study.getDescription());
            ps.setString(4, study.getEmail());
            ps.setString(5, currentTime);
            ps.setString(6, study.getQuestion());
            ps.setInt(7, study.getRequestedParticipants());
             ps.setString(8, study.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
           
        } 
        finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
       
    }
    public String codeGenerator()
    {
        StringBuilder sb=new StringBuilder();
        String code = "";
        Random randomGenerator = new Random();
        int num = randomGenerator.nextInt(100) + 1;
        sb.append(num);
        sb.append(count);
        code=sb.toString();
        
        count++;
        return code;
    }
    
    
    public static Study getStudy(String studyCode)                  
    {      
        System.out.print("studyCode in getStudy func" + studyCode);
        Study study = null;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Study "
                + "WHERE code = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, studyCode);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                study = new Study();
                study.setName(rs.getString("name"));
                study.setDescription(rs.getString("description"));
                study.setCode(rs.getString("code"));
                study.setEmail(rs.getString("email"));
                study.setQuestion(rs.getString("question"));
                study.setImageURL(rs.getString("imageURL"));
                study.setStatus(rs.getString("status"));
                study.setRequestedParticipants(rs.getInt("requestedParticipants"));
                study.setNumOfParticipants(rs.getInt("numOfParticipants"));
                
            }
            return study;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
       
    }
    
    // For start and stop update
     public static void updateStudyStatus(String studyCode, String status)                  
    {      
        System.out.println("studyCode in getStudy func" + studyCode);
        System.out.println("--------------STATUS----------" + status);
        Study study = null;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
       
        String query = "UPDATE Study SET "
                + "status = ?"
                + "WHERE code = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, status);
            ps.setString(2, studyCode);
            ps.executeUpdate();
            
           
        } catch (SQLException e) {
            System.out.println(e);
            
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
       
    }
      public static void updateStudy(Study study ,String code)                  
    {      
        System.out.println("inside update study");
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
       
           
          String query = "UPDATE Study SET "
                + "name = ?, "
                + "question = ?, "
                + "requestedParticipants = ?, "
                + "description = ?, "
                + "email = ?, "
                + "numOfParticipants = ? "
                + "WHERE code = ?";
        try {
             System.out.println("inside update study"+query); 
            ps = connection.prepareStatement(query);
            ps.setString(1, study.getName());
            ps.setString(2, study.getQuestion());
            ps.setInt(3, study.getRequestedParticipants());
            ps.setString(4, study.getDescription());
            ps.setString(5, study.getEmail());
            ps.setInt(6, study.getNumOfParticipants());
            ps.setString(7 , code);
            ps.executeUpdate();
           
            System.out.println("inside update study"+ps); 
           
        } catch (SQLException e) {
            System.out.println(e);
            
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
       
    }
    
      //update for 2 where condition
     public Study getStudy(String studyCode,String email)
    {
        System.out.print("studyCode in getStudy func" + studyCode);
        Study study = null;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Study "
                + "WHERE code = ?"
                + "and email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, studyCode);
            ps.setString(2, email);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                study = new Study();
                study.setName(rs.getString("name"));
                study.setDescription(rs.getString("description"));
                study.setCode(rs.getString("code"));
                study.setEmail(rs.getString("email"));
                study.setQuestion(rs.getString("question"));
                study.setImageURL(rs.getString("imageURL"));
                study.setStatus(rs.getString("status"));
                study.setRequestedParticipants(rs.getInt("requestedParticipants"));
                study.setNumOfParticipants(rs.getInt("numOfParticipants"));
                
            }
            return study;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }
    //For my studies
    public List<Study> getStudies(String email)                 //*************8
    {                       
        
        Study study;
        List<Study> newStudies = new ArrayList<Study>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Study "
                + "WHERE email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            
           while (rs.next())  {
                study = new Study();
                study.setName(rs.getString("name"));
                study.setDescription(rs.getString("description"));
                 study.setCode(rs.getString("code"));
                study.setEmail(rs.getString("email"));
                study.setQuestion(rs.getString("question"));
                study.setImageURL(rs.getString("imageURL"));
                study.setRequestedParticipants(rs.getInt("requestedParticipants"));
                study.setNumOfParticipants(rs.getInt("numOfParticipants"));
                study.setStatus(rs.getString("status"));
                newStudies.add(study);
                
            }
             return newStudies;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
      
      
    }
    //Function to fetch All the studies
    //For participate page
    public List<Study> getAllStudies()                 
    {                       
        
        Study study;
        String status="start";
        List<Study> newStudies = new ArrayList<Study>();
       
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Study "
                + "WHERE status = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, status);
            rs = ps.executeQuery();
            
           while (rs.next())  {
                study = new Study();
                study.setName(rs.getString("name"));
                study.setDescription(rs.getString("description"));
                study.setEmail(rs.getString("email"));
                study.setCode(rs.getString("code"));
                study.setQuestion(rs.getString("question"));
                study.setImageURL(rs.getString("imageURL"));
                study.setRequestedParticipants(rs.getInt("requestedParticipants"));
                study.setNumOfParticipants(rs.getInt("numOfParticipants"));
                study.setStatus(rs.getString("status"));
                newStudies.add(study);
                
            }
             return newStudies;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
          
      
    }
     public Answer getAnswer(String studyCode,String email)
    {
        System.out.print("studyCode in getStudy func" + studyCode);
        Answer answer = null;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Answer "
                + "WHERE code = ?"
                + "and email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, studyCode);
            ps.setString(2, email);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                answer = new Answer();
                
                answer.setCode(rs.getString("code"));
                answer.setEmail(rs.getString("email"));
                answer.setChoice(rs.getInt("choice"));
                System.out.println("answer"+answer.getCode());
                return answer;
            }
            else
            {
                System.out.println("inside else");
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
    public void addAnswer(Study study,int choice, String email){         //take this function
     
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
                = "INSERT INTO Answer ( email,choice,code,dateSubmitted) "
                + "VALUES (?, ?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setInt(2, choice);
            ps.setString(3, study.getCode());
            ps.setString(4, currentTime);
           
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
           
        } 
        finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
       
    }
    
    
   
}
    
    
 