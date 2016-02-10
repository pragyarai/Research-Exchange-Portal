/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyServlet;

import MyData.MailUtilLocal;
import MyData.UserDB;
import Mypackage.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author pragyarai
 */
@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {
    HashMap<String,String> userPassword = new HashMap<String,String>();
    java.util.Date dt = new java.util.Date();
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

   UserDB userDB=new UserDB();
   String action;
   public static String salt;
    int flag=0;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // processRequest(request, response);
        Object message;
        Object userResetToken;
        String url="/main.jsp";
        action=request.getParameter("action");
        System.out.println("action"+action);
        PrintWriter writer = response.getWriter();
        HttpSession session = request.getSession();
        User theUser=(User)session.getAttribute("theUser");
         writer.println("Inside get"+action);
        
        if(theUser != null)
        {
            //writer.println("Inside user");
            if(action.equals("about"))
            {
                 url="/aboutl.jsp";
                 
            }
            if(action.equals("how"))
            {
                url="/main.jsp";
            }
            if(action.equals("home"))
            {
                 url="/main.jsp";
            }
            if(action.equals("main"))
            {
                url="/main.jsp";
            }
            if(action.equals("login"))
            {
                url="/login.jsp";
            }
            if(action.equals("create"))
            {
                try {
                    String currentTime = sdf.format(dt);
                    String token = request.getParameter("token");
                    String expiryTime =UserDB.getTime(token);
                    Date date1 = sdf.parse(expiryTime);
                    Date date2 = sdf.parse(currentTime);
                    long differenceInMillis = date2.getTime() - date1.getTime();
                    if(differenceInMillis<3600000)
                    {   
                        request.setAttribute("token", token);
                        url="/signup.jsp";
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            if(action.equals("activation"))
            {
                String currentTime = sdf.format(dt); 
                String value;
                String userToken;
                String password;
                userToken=request.getParameter("activationcode");
                System.out.println("userToken if"+userToken);
                String expiryTime =UserDB.getTime(userToken);
                
               try {
                    Date date1 = sdf.parse(expiryTime);
                    Date date2 = sdf.parse(currentTime);
                    
                    long differenceInMillis = date2.getTime() - date1.getTime();
                    if(differenceInMillis<3600000)
                    {
                        User user= UserDB.activateUser(userToken);
                    
                        if(user!=null)
                        {
                            value=userPassword.get(user.getEmail());
                            session.setAttribute("theUser", user);
                            try {

                                password=hashAndSalt(value);
                                userDB.addUser(user,password,salt);
                                userDB.addUser(user);
                                userDB.deleteTemp(userToken);
                            } catch (NoSuchAlgorithmException ex) {
                                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            url="/login.jsp";
                        }
                        else
                        {
                            url="/signup.jsp";
                        }
                    }
                    
                           
                } catch (ParseException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(action.equals("resetpassword"))
            {
                try {
                    String token;
                    String currentTime = sdf.format(dt);
                    token=request.getParameter("token");
                    System.out.println("userToken else"+token);
                    String expiryTime =UserDB.getTime(token);
                    Date date1 = sdf.parse(expiryTime);
                    Date date2 = sdf.parse(currentTime);
                    long differenceInMillis = date2.getTime() - date1.getTime();
                    if(differenceInMillis<3600000)
                    {
                        User user= UserDB.activateUser(token);
                        if(user!=null)
                        {
                            request.setAttribute("user", user);
                            request.setAttribute("userResetToken", token);
                            url="/resetpassword.jsp";
                        }
                        else
                        {
                            url="/signup.jsp";
                        }
                    
                    }
                    else
                    {
                        message = "Token is expired!!";
                        request.setAttribute("message", message);
                         url="/signup.jsp";
                    }
                    //url="/login.jsp";
                } catch (ParseException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        else
        {
            if(action.equals("about"))
            {
                 url="/about.jsp";
            }
            if(action.equals("how"))
            {
                url="/how.jsp";
            }
            if(action.equals("home"))
            {
                if(flag==0)
                {
                    
                    int i = request.getServerPort();
                    String port = String.valueOf(i);
                    Cookie myCookie = new Cookie("HostName", request.getServerName());
                    myCookie.setMaxAge(60*60*24*365);
                    myCookie.setPath("/");
                    response.addCookie(myCookie);
                    Cookie cookiePort=new Cookie("Port",port );
                    myCookie.setMaxAge(60*60*24*365);
                    myCookie.setPath("/");
                    response.addCookie(cookiePort);
                }
                 url="/home.jsp";
                 flag++;
            }
            if(action.equals("main"))
            {
                url="/login.jsp";
            }
             if(action.equals("login"))
            {
                
                url="/login.jsp";
            }
             if(action.equals("activation"))
            {
                String currentTime = sdf.format(dt);
                String value;
                String userToken;
                String password;
                userToken=request.getParameter("activationcode");
                System.out.println("userToken else"+userToken);
                String expiryTime =UserDB.getTime(userToken);
                try {
                    Date date1 = sdf.parse(expiryTime);
                    Date date2 = sdf.parse(currentTime);
                    
                    long differenceInMillis = date2.getTime() - date1.getTime();
                    if(differenceInMillis<3600000)
                    {
                        User user= UserDB.activateUser(userToken);
                    
                        if(user!=null)
                        {
                            value=userPassword.get(user.getEmail());
                            session.setAttribute("theUser", user);
                            try {

                                password=hashAndSalt(value);
                                userDB.addUser(user,password,salt);
                                userDB.addUser(user);
                                userDB.deleteTemp(userToken);
                            } catch (NoSuchAlgorithmException ex) {
                                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            url="/login.jsp";
                        }
                        else
                        {
                            url="/signup.jsp";
                        }
                    }
                           
                } catch (ParseException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
              
            }
             if(action.equals("resetpassword"))
            {
                
                try {
                    String token;
                    String currentTime = sdf.format(dt);
                    token=request.getParameter("token");
                    System.out.println("userToken else"+token);
                    String expiryTime =UserDB.getTime(token);
                    Date date1 = sdf.parse(expiryTime);
                    Date date2 = sdf.parse(currentTime);
                    long differenceInMillis = date2.getTime() - date1.getTime();
                    if(differenceInMillis<3600000)
                    {
                        User user= UserDB.activateUser(token);
                        if(user!=null)
                        {
                            request.setAttribute("user", user);
                            request.setAttribute("userResetToken", token);
                            url="/resetpassword.jsp";
                        }
                        else
                        {
                            url="/signup.jsp";
                        }
                    
                    }
                    else
                    {
                        message = "Token is expired!!";
                        request.setAttribute("message", message);
                         url="/signup.jsp";
                    }
                    //url="/login.jsp";
                } catch (ParseException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
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
        String url="/main.jsp";
        Object message;
        action = request.getServletPath();
        PrintWriter writer = response.getWriter();
        String formName = request.getParameter("formname");
        HttpSession session = request.getSession();
        writer.println("formName  :"+formName);
        System.out.println("Inside post user");
        if(formName.equals("create"))
        {
            String hosturl = request.getRequestURL().toString();
            String baseURL = hosturl.substring(0, hosturl.length() - request.getRequestURI().length()) + request.getContextPath() + "/";
            System.out.println("hosturl"+hosturl);
             System.out.println("baseURL"+baseURL);
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String cpass = request.getParameter("cpass");
            String token = request.getParameter("token");
            
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            request.setAttribute("user", user);
            if(password.equals(cpass))
            {
                if(userDB.getUser(email)==null)
                {
                    if(token!=null)
                    {
                        int recomCoins;
                        User userRecom,newUser;
                        userRecom=UserDB.activateUser(token);
                        if(userRecom!=null)
                        {
                            newUser=UserDB.getUser(userRecom.getEmail());
                            recomCoins=newUser.getCoins();
                            newUser.setCoins(recomCoins+2);
                            UserDB.update(newUser);
                            UserDB.deleteTemp(token);
                        }
                        
                    }
                    UUID uId = UUID.randomUUID();
                    System.out.println("UUID One: " + uId);
                    //session.setAttribute("theUser", user);
                    userPassword.put(email,password);
                    userDB.tempUser(user, uId);
                    /*
                    userDB.addUser(user,password);
                    userDB.addUser(user);*/
                    String to=email;
                    String from=email;
                    String subject="Activation Link";
                    
                    String body=baseURL+"user?action=activation&activationcode="+uId;
                    boolean bodyIsHTML=false;
                    try
                    {
                        MailUtilLocal.sendMail(to, from, subject, body, bodyIsHTML);
                        System.out.println("mail sent");
                        message="Activation link sent to your email account";
                        request.setAttribute("message", message);
                        url="/login.jsp";
                    }
                    catch( MessagingException e)
                    {
                        String errorMessage
                                ="ERROR: Unable to send email."
                                +"ERROR MESSAGE:"+e.getMessage();
                        System.out.println(errorMessage);
                        request.setAttribute("errorMessage", errorMessage);
                        url = "/contact.jsp";
                    }
                   
                }
                else
                {
                    message = "Email address already exist!!";
                    request.setAttribute("message", message);
                    url="/signup.jsp";
                }
                
            }
            else
            {
                writer.println("Error");
                message = "Confirm Password doesnot match";
                request.setAttribute("message", message);
                 url="/signup.jsp";
            }
            
        }
        else if(formName.equals("login"))
        {
            
            User userLogin; 
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            writer.println("inside login"+userPassword.get(email));
            userLogin=userDB.getUser(email);
            if(userLogin==null)
            {
                writer.println("no user");
                message = "Not found email address : "+email;
                request.setAttribute("message", message);
                url="/login.jsp";
                 
            }
            else
            {
                  writer.println("inside else");
                try {
                    String salt=UserDB.getSalt(email);
                    if(salt!=null)
                    {
                        password=hashPassword(password+salt);
                        if(userDB.validateUser(email,password))
                        {
                            if (session.getAttribute("theUser")!= null) { 
                                 session.invalidate();
                             }
                             session = request.getSession();
                             session.setAttribute("theUser", userLogin);

                             url="/main.jsp";
                        }
                        else
                        {
                             message = "Password is incorrect!!";
                             request.setAttribute("message", message);
                             url="/login.jsp";

                        }
                    }
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
               
                
            }
           
            
        }
        else if(formName.equals("forgetpassword"))
        {
            String name="name";
            String email = request.getParameter("email");
            System.out.println("email"+email);
            if(userDB.getUser(email)!=null)
                {
                    UUID uId = UUID.randomUUID();
                    System.out.println("UUID One: " + uId);
                    //session.setAttribute("theUser", user);
                    String to=email;
                    String from=email;
                    String subject="Password Reset Link";
                    String hosturl = request.getRequestURL().toString();
                    String baseURL = hosturl.substring(0, hosturl.length() - request.getRequestURI().length()) + request.getContextPath() + "/";
                    
                    String body=baseURL+"user?action=resetpassword&token="+uId;
                    boolean bodyIsHTML=false;
                    try
                    {
                        User user = new User();
                        user.setName(name);
                        user.setEmail(email);
                        userDB.tempUser(user, uId);
                        MailUtilLocal.sendMail(to, from, subject, body, bodyIsHTML);
                        System.out.println("mail sent");
                        message="Please check your email account";
                        request.setAttribute("message", message);
                        url="/login.jsp";
                    }
                    catch( MessagingException e)
                    {
                        String errorMessage
                                ="ERROR: Unable to send email."
                                +"ERROR MESSAGE:"+e.getMessage();
                        System.out.println(errorMessage);
                        request.setAttribute("errorMessage", errorMessage);
                        url = "/contact.jsp";
                    }
                }
        }
        else if(formName.equals("resetpassword"))
            {
            try {
                String currentTime = sdf.format(dt);
                String password = request.getParameter("password");
                String cpass = request.getParameter("cpass");
                String email = request.getParameter("email");
                String token = request.getParameter("token");
                String expiryTime =UserDB.getTime(token);
                Date date1 = sdf.parse(expiryTime);
                Date date2 = sdf.parse(currentTime);
                long differenceInMillis = date2.getTime() - date1.getTime();
                if(differenceInMillis<3600000)
                {
                    User user = new User();
                    user.setEmail(email);
                    if(password.equals(cpass))
                    {
                        try {
                            password=hashAndSalt(password);
                        } catch (NoSuchAlgorithmException ex) {
                            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        UserDB.updatePassword(user,password,salt);
                        UserDB.deleteTempEmail(email);
                        url="/login.jsp";
                    }
                    else
                    {
                        
                        request.setAttribute("user", user);
                        request.setAttribute("userResetToken", token);
                        url="/resetpassword.jsp";
                    }
                }
                else
                {
                    message = "Token is expired!!";
                    request.setAttribute("message", message);
                    url="/signup.jsp";
                }
                //url="/login.jsp";
            } catch (ParseException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        
        
       
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    public static String hashAndSalt(String password) throws NoSuchAlgorithmException
    {
        Random r= new SecureRandom();
        byte[] saltBytes= new byte[32];
        r.nextBytes(saltBytes);
        salt=Base64.encodeBase64String(saltBytes);
        return hashPassword(password+salt);
    }
    public static String hashPassword(String password)
    throws NoSuchAlgorithmException{
        MessageDigest md=MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        byte[] mdArray=md.digest();
        StringBuilder sb= new StringBuilder(mdArray.length *2);
        for(byte b: mdArray)
        {
            int v=b & 0xff;
            if(v<16)
            {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString();
    }

}
