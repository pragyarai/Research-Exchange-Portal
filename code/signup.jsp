<%-- 
    Document   : signup
    Created on : Sep 23, 2015, 7:52:38 PM
    Author     : pragyarai
--%>


        <%@include file="header1.jsp" %>
         <div class="clear"></div>
            <div class="content">
                        <p> ${message}</p>
                        <form class="align-form"  action="user" method="post">
                            <input type="hidden" name="formname" value="create"/>
                            <table>
                                <tbody>
                                    <tr>
                                        <td><label>Name *</label></td>
                                        <td><input type="text" name="name" value="${user.name}" required="" ></td>
                                    </tr>
                                    <tr>
                                        <td><label>Email *</label></td>
                                        <td><input type="email" name="email" value="${user.email}" required="" ></td>

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
                                        <td><label> </label></td>
                                        <td><input type="hidden"  name="token" value="${token}"></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td> <input class="participate" type="submit" value="Create Account"></td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>
             </div>
        
        <%@include file="footer.jsp" %>
 