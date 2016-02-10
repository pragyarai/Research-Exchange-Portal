<%-- 
    Document   : login
    Created on : Sep 23, 2015, 7:52:38 PM
    Author     : pragyarai
--%>


        <%@include file="header1.jsp" %>
         
            <div class="content">
                        <p> ${message}</p>
                        <form class="align-form" action="user" method="post">
                            <input type="hidden" name="formname" value="login"/>
                            <table>

                                <tbody>
                                    <tr>
                                        <td><label>Email Address * :</label></td>
                                        <td><input type="email"  name="email" required="" ></td>

                                    </tr>
                                    <tr>
                                        <td><label>Password * :</label></td>
                                        <td><input type="password"  name="password" required=""></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td><input class="participate" type="submit" value="Login"></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td><a href="signup.jsp">Sign up for a new account</a></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td><a href="forgotpassword.jsp">Forgot Password</a></td>
                                    </tr>
                                </tbody>
                            </table>


                            
                        </form>
                
                
            </div>
        
        <%@include file="footer.jsp" %>
 
