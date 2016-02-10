<%-- 
    Document   : signup
    Created on : Sep 23, 2015, 7:52:38 PM
    Author     : pragyarai
--%>


        <%@include file="header1.jsp" %>
         <div class="clear"></div>
            <div class="content">
                        <p>Reset Password</p>
                        <form class="align-form"  action="user" method="post">
                            <input type="hidden" name="formname" value="resetpassword" />
                            <table>
                                <tbody>
                                    <tr>
                                        <td><label>Email </label></td>
                                        <td><input type="text" name="email" value="${user.email}" required="" readonly="" ></td>

                                    </tr>
                                    <tr>
                                        <td><label></label></td>
                                        <td><input type="hidden" name="token" value="${userResetToken}" required="" ></td>

                                    </tr>
                                    <tr>
                                        <td><label>Password *</label></td>
                                        <td><input type="password" title="Password must contain at least 6 characters, including UPPER/lowercase and numbers."  name="password" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" ></td>
                                        
                                    </tr>
                                    <tr>
                                        <td><label>Confirm Password * </label></td>
                                        <td><input type="password" title="Please enter the same Password as above."  name="cpass" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}"></td>
                                    </tr>
                                    <tr>
                                        <td> <input class="participate" type="submit" value="Reset Password"></td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>
             </div>
        
        <%@include file="footer.jsp" %>
 