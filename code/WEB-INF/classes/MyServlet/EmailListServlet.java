package MyServlet;

import MyData.MailUtilLocal;
import MyData.UserDB;
import Mypackage.User;
import java.io.*;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.*;
import javax.servlet.http.*;



public class EmailListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/main.jsp";
        String hosturl = request.getRequestURL().toString();
        String baseURL = hosturl.substring(0, hosturl.length() - request.getRequestURI().length()) + request.getContextPath() + "/";
        UUID uId = UUID.randomUUID();   
        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "join";  // default action
        }

        // perform action and set URL to appropriate page
        if (action.equals("join")) {
            url = "/main.jsp";    // the "join" page
        } 
         else if (action.equals("contact")) {
            // get parameters from the request
            String firstName = request.getParameter("name");
            String email = request.getParameter("email");
            String message = request.getParameter("message");
            // store data in User object
            /*
            User user = new User();
            user.setEmail(email);
            user.setName(firstName);
            */
            // validate the parameters
            HttpSession session = request.getSession();
            User theUser=(User)session.getAttribute("theUser");
                message = "Please contact "+firstName+
                        "he has the following issue \n"+message+"\n";
                        
                
            
            //request.setAttribute("user", user);
            
            String to="testnbad3@gmail.com";
            String from=email;
            String subject="Contact US";
            String body=message;
            boolean bodyIsHTML=false;
            try
            {
                MailUtilLocal.sendMail(to, from, subject, body, bodyIsHTML);
                System.out.println("mail sent");
                url = "/confirmc.jsp";
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
         
         
        else if (action.equals("add")) {
            // get parameters from the request
            User user=new User();
            String firstName = request.getParameter("name");
            String email = request.getParameter("email");
            String fremail = request.getParameter("fremail");
            String message = request.getParameter("message");
            if(fremail.equalsIgnoreCase(email))
            {
                String errorMessage ="Friends email address cannot be same";
                request.setAttribute("errorMessage", errorMessage);
                url = "/recommend.jsp";
            }
            else
            {
                 HttpSession session = request.getSession();
                User theUser=(User)session.getAttribute("theUser");
                message = "To "+firstName+","
                        +message+"\n"+
                        baseURL+"user?action=create&token="+uId+"\n"
                        +"From  "+theUser.getName();
            
            String to=fremail;
            String from=email;
            String subject="Reccomendation from your friend";
            String body=message;
            user.setEmail(email);
            user.setName("name");
            boolean bodyIsHTML=false;
            try
            {
                UserDB.tempUser(user,uId);
                MailUtilLocal.sendMail(to, from, subject, body, bodyIsHTML);
                System.out.println("mail sent");
                url = "/confirmr.jsp";
            }
            catch( MessagingException e)
            {
                String errorMessage
                        ="ERROR: Unable to send email."
                        +"ERROR MESSAGE:"+e.getMessage();
                System.out.println(errorMessage);
                request.setAttribute("errorMessage", errorMessage);
                url = "/recommend.jsp";
            }
            }
           
            
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String url = "/main.jsp";
         String action = request.getParameter("action");
        if (action == null) {
            action = "join";  // default action
        }
        else if(action.equals("recommendation"))
        {
            User user;
            String token = request.getParameter("token");
            user=UserDB.activateUser(token);
            if(user!=null)
            {
                
            }
        }
         
         getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
}
}
