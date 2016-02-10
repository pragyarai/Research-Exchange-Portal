<%-- 
    Document   : signup
    Created on : Sep 23, 2015, 7:52:38 PM
    Author     : pragyarai
--%>


        <%@include file="header1.jsp" %>
         <div class="clear"></div>
            <div class="content">
                        <p> Enter Email Address</p>
                        <form class="align-form"  action="user" method="post">
                            <input type="hidden" name="formname" value="forgetpassword" />
                            <table>
                                <tbody>
                                    <tr>
                                        <td><input type="text" name="email"  ></td>
                                    </tr>
                                    <tr>
                                        <td> <input class="participate" type="submit" value="Send Email"></td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>
             </div>
        
        <%@include file="footer.jsp" %>
 