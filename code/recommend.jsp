<%-- 
    Document   : recommend
    Created on : Sep 26, 2015, 5:25:33 PM
    Author     : Sharan
--%>
<%@include file="header.jsp" %>
         <div class="clear"></div>
         <div class="link-wrapper" >
            <span class="link-back">
              Recommend to a friend
            </span>
               <p><b>&#60;&#60;<a href="user?action=main">Back to the main page</a></b></p>
         </div>
         <div class="content">       
                     <p> ${errorMessage}</p>
               
                        <form class="align-form" action="email" method="post">
                            <input type="hidden" name="action" value="add"/>
                            <table>
                                <tbody>
                                    <tr>
                                        <td><label>Name *</label></td>
                                        <td><input  type="text" name="name" required="" ></td>

                                    </tr>
                                    <tr>
                                        <td><label>Email *</label></td>
                                        <td><input  type="email" name="email" required="" ></td>

                                    </tr>
                                    
                                    <tr>
                                        <td><label>Friend's Email *</label></td>
                                        <td><input type="email" name="fremail" required="" ></td>

                                    </tr>
                                    
                                    <tr>
                                        <td><label>Message *</label></td>
                                        <td><textarea name="message" rows="4" cols="20" required="" >
                                           
                                            </textarea></td>
                                    </tr>
                                   
                                    <tr>
                                        <td></td>
                                        <td><input class="participate" type="submit" value="Submit"></td>
                                    </tr>
                                </tbody>
                            </table>
                            <b>When your friend logs in and participates in one user study, you'll get 2 coin bonus</b>
                                    
                                                       
                        </form>
                   
           </div>
        
        <%@include file="footer.jsp" %>
 