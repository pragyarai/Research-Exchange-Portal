<%-- 
    Document   : home
    Created on : Sep 23, 2015, 7:52:38 PM
    Author     : pragyarai
--%>


        <%@include file="header1.jsp" %>
         <div class="clear"></div>
        <div class="img-home"> 
            
            <%
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
                %>
            <img alt="profile" src="images/images.png" >    
    
        </div>
        
        <%@include file="footer.jsp" %>
 