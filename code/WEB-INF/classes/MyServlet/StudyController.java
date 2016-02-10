package MyServlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import MyData.StudyDB;
import MyData.UserDB;
import Mypackage.Study;
import Mypackage.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author pragyarai
 */
public class StudyController extends HttpServlet {
    
     StudyDB studyDB = new StudyDB();
                                                     
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
  

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // processRequest(request, response);
        String url ="/home.jsp";
        response.setContentType("text/html");
        String action = request.getParameter("action");
        PrintWriter out = response.getWriter();   
        HttpSession session = request.getSession();
       
               
        out.print(action);
        
        if(action == null)
        {
            User UserObj = (User)session.getAttribute("theUser");
            if(UserObj != null)
            {
                out.print("object is not  null");
                url = "/main.jsp";
                out.print("url is" + url);
            }
            else
            {
                out.print("object is null");
                url = "/home.jsp";
            }
        }
        else if (action.equalsIgnoreCase("studies"))
        {
             out.print("Action is" + action); 
             User UserObj_add = (User)session.getAttribute("theUser");
             String email = UserObj_add.getEmail(); 
             if(UserObj_add != null)
             { 
                 List<Study> allStudies = studyDB.getStudies(email);
                 if(allStudies.size()>0)
                 {
                 request.setAttribute("allStudies", allStudies);
                 }
                 else
                 {
                     
                     request.setAttribute("allStudies", null);
                 }
                             
                 url = ("/studies.jsp");
             }
             else
             { 
                 out.print("object is null");
                 url = ("/login.jsp");
             }         
        }
        //   -----------------------------Participate -----------------------------
        
        else if (action.equalsIgnoreCase("Participate"))
        {  
            out.print("inside GET");
           List<Study> allStudies=  studyDB.getAllStudies();
           out.print("inside participate"+allStudies.size());
           if(allStudies.size()>0)
                 {
                 request.setAttribute("allStudies", allStudies);
                 }
                 else
                 {
                     
                     request.setAttribute("allStudies", null);
                 }
                 url = ("/participate.jsp"); 
        }
        // ------------------------edit ----------------------------------------- 
        else if (action.equalsIgnoreCase("edit"))
        {
           out.println("Action is" + action);
           User UserObj_edit = (User)session.getAttribute("theUser");
           String email = UserObj_edit.getEmail();
             if(UserObj_edit != null)
             {                
                 String code = request.getParameter("studyCode");
                 out.println("In edit Code is" + code + "email is" + email);              
                 Study editStudy =studyDB.getStudy(code,email);
                 out.println(editStudy.getName()+editStudy.getEmail()+editStudy.getNumOfParticipants());
                 request.setAttribute("study", editStudy);
                 url = ("/editstudy.jsp"); 
                
             }
             else
             {
                 url = ("/login.jsp");
             }   
        }
        
       // -------------------------------------add --------------------------
        
        else if (action.equalsIgnoreCase("add"))
        {
             out.print("Action is" + action); 
             User session_obj = (User)session.getAttribute("theUser");
             if(session_obj != null)
             { 
                         
                 url = ("/newstudy.jsp");
             }
             else
             { 
                 out.print("object is null");
                 url = ("/login.jsp");
             }         
        }
        
       getServletContext().getRequestDispatcher(url).forward(request, response); 
        
    }
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String url ="";
        response.setContentType("text/html");
        String action= request.getParameter("action");
        PrintWriter out = response.getWriter();   
        HttpSession session = request.getSession();
        if (action.equalsIgnoreCase("Participate"))
        {   
           
            out.print("Action is" + action);
            User UserObj_participate = (User)session.getAttribute("theUser");
            if(UserObj_participate != null)
            {
                String code = request.getParameter("studycode");
                out.print("Code is" + code);
                if (code == null)
                {
                 List<Study> allStudies=  studyDB.getAllStudies();
                out.print("inside participate"+allStudies.size());
                request.setAttribute("allStudies", allStudies);
                 url = ("/participate.jsp");
                }
                else
                {
                
                 Study study=studyDB.getStudy(code);
                 request.setAttribute("study", study);
                  out.print("inside participate"+study.getQuestion());
                 url = ("/question.jsp");
                }
            }
            else
            {
                url = ("/login.jsp");
            }
            
        }
        if (action.equalsIgnoreCase("add"))
        {
            int user_part;
             out.print("Action is" + action); 
             User UserObj_add = (User)session.getAttribute("theUser");
             String email = UserObj_add.getEmail(); 
             if(UserObj_add != null)
             { 
                 
                 User user=UserDB.getUser(email);
                 System.out.println("user name"+user.getName());
                 user_part=user.getParticipants();
                 System.out.println("user_part"+user_part);
                 user_part++;
                 user.setParticipants(user_part);
                 System.out.println("user.getParticipants"+user.getParticipants());
                 UserDB.update(user);
                 UserObj_add.setParticipants(user_part);
                 
                 String image = request.getParameter("image");
                 out.println("image"+image);
                 String study_name = request.getParameter("study");
                 String question = request.getParameter("question");
                 int participants = Integer.parseInt(request.getParameter("participants"));
                 String description = request.getParameter("description");
                 String status = "start";
                 
                 Study study = new Study(study_name,email,question,participants,description,status);
                 study.setStatus(status);
                 studyDB.addStudies(study);
                 List<Study> allStudies = studyDB.getStudies(email);
                
                 request.setAttribute("allStudies", allStudies);
                             
                 url = ("/studies.jsp");
             }
             else
             { 
                 out.print("object is null");
                 url = ("/login.jsp");
             }         
        }
        if (action.equalsIgnoreCase("Edit"))
        {
             out.print("Action is" + action); 
             User UserObj_add = (User)session.getAttribute("theUser");
             String email = UserObj_add.getEmail(); 
             if(UserObj_add != null)
             { 
               String code= request.getParameter("studyCode");
                if(code!=null)
                {
                    out.println("code "+code);
                   Study study= studyDB.getStudy(code);
                   out.println("study "+study.getDescription());
                   request.setAttribute("study", study); 
                }
                             
                 url = ("/editstudy.jsp");
             }
             else
             { 
                 out.print("object is null");
                 url = ("/login.jsp");
             }         
        }
        
        if (action.equalsIgnoreCase("answer"))
        {   
           int coins;
           int user_part;
           int study_part;
            out.print("Action is" + action);
            User UserObj_participate = (User)session.getAttribute("theUser");
            if(UserObj_participate != null)
            {
                String code = request.getParameter("studycode");
               int choice = Integer.parseInt(request.getParameter("choice"));
                
                String email=UserObj_participate.getEmail();
                System.out.println("email"+email);
                System.out.println("code"+code);
                if (code != null )
                {
                    
                    if(studyDB.getAnswer(code, email)==null)
                    {
                        
                        coins=UserObj_participate.getCoins();
                        user_part=UserObj_participate.getParticipation();
                        user_part++;
                        coins++;
                        UserObj_participate.setCoins(coins);
                        UserObj_participate.setParticipation(user_part);
                        User user=UserDB.getUser(email);
                        user.setCoins(coins);
                        user.setParticipation(user_part);
                        UserDB.update(user);
                        Study study= studyDB.getStudy(code);
                        if(study!=null)
                        {
                            study_part=study.getNumOfParticipants();
                            study_part++;
                            System.out.println("study_part"+study_part);
                            study.setNumOfParticipants(study_part);
                            System.out.println("setting"+study.getNumOfParticipants());
                            studyDB.updateStudy(study,study.getCode());
                            //--------------------update code from here--------------------------------
                            study.setAnswerObj(email,choice);
                            studyDB.addAnswer(study, choice,email);
                            //---------------end-----------------------------------------
                            
                        }
                        
                        List<Study> allStudies=  studyDB.getAllStudies();
                        out.print("inside participate"+allStudies.size());
                        request.setAttribute("allStudies", allStudies);
                       
                        url = ("/participate.jsp");
                    
                    }
                    else
                    {
                        System.out.println("inside else");
                        Object errorMessage;
                        errorMessage="Already submitted the answer";
                        
                        request.setAttribute("errorMessage", errorMessage);
                        List<Study> allStudies=  studyDB.getAllStudies();
                        out.print("inside participate"+allStudies.size());
                        request.setAttribute("allStudies", allStudies);
                        url = ("/participate.jsp");
                    }
                        
                
                }
               
            }
            else
            {
                url = ("/login.jsp");
            }
            
            
        }
        if (action.equalsIgnoreCase("update"))
        {   
           
            out.print("Action is" + action);
            User UserObj_participate = (User)session.getAttribute("theUser");
            String email = UserObj_participate.getEmail();
            if(UserObj_participate != null)
            {
                 String code = request.getParameter("studyCode");
                  String name = request.getParameter("study");
                 String question = request.getParameter("question");
                 int participants = Integer.parseInt(request.getParameter("participants"));
                 String description = request.getParameter("description");
                 out.print("Action is" + code);
                 Study study = studyDB.getStudy(code);
                 study.setName(name);
                 study.setQuestion(question);
                 study.setRequestedParticipants(participants);
                 study.setDescription(description);
                 studyDB.updateStudy( study , code);
                 List<Study> allStudies = studyDB.getStudies(email);
                 request.setAttribute("allStudies", allStudies);
                 url = ("/studies.jsp");                 
               
              
            }
            else
            {
                url = ("/login.jsp");
            }
            
            
        }
        if (action.equalsIgnoreCase("stop"))
        {   
            String status="stop";
             String code = request.getParameter("studyCode");
             User UserObj = (User)session.getAttribute("theUser");
             String email = UserObj.getEmail();
             if(UserObj!= null)
             {   
                
                 
                 studyDB.updateStudyStatus(code,status);
                 List<Study> allStudies = studyDB.getStudies(email);
                 request.setAttribute("allStudies", allStudies);

                 url = ("/studies.jsp");               
             }
             
        } 
        if (action.equalsIgnoreCase("start"))
        {   
             String status="start";
             String code = request.getParameter("studyCode");
             User UserObj = (User)session.getAttribute("theUser");
             String email = UserObj.getEmail();
             
             if(UserObj!= null)
             {   
                 
                 studyDB.updateStudyStatus(code,status);                
                 List<Study> allStudies = studyDB.getStudies(email);
                 request.setAttribute("allStudies", allStudies);

                 url = ("/studies.jsp");               
             }
             
        }
        
      getServletContext().getRequestDispatcher(url).forward(request, response); 
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
