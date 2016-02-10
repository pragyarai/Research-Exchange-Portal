/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyData;

import java.util.*;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;





/**
 *
 * @author pragyarai
 */
public class MailUtilLocal {
    
    public static void sendMail(String to, String from, String subject, String body, boolean bodyIsHTML)
            throws javax.mail.MessagingException
    {
        //get a mail session
        Properties props=new Properties();
        props.put("mail.transport.protocol","smtps");
        props.put("mail.smtps.host","smtp.gmail.com");
        props.put("mail.smtps.port",465);
        props.put("mail.smtps.auth","true");
        props.put("mail.smtps.quitwait","false");
       
      //Authenticator auth = new SMTPAuthenticator();
      // Get the default Session object.
      Session session = Session.getDefaultInstance(props);
      session.setDebug(true);

        //
      MimeMessage message=new MimeMessage(session);
      message.setSubject(subject);
      if(bodyIsHTML)
      {
          message.setContent(body,"text/html");
          
      }
      else
          message.setText(body);
      
      //address
      Address fromAddress= new InternetAddress(from);
      Address toAddress= new InternetAddress(to);
      message.setFrom(fromAddress);
      message.setRecipient(Message.RecipientType.TO, toAddress);
      
      //send message
       Transport transport = session.getTransport();
        transport.connect("testnbad3@gmail.com","Mytest_site1");
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
     
    }
    
    
}
