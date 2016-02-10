<%-- 
    Document   : aboutl
    Created on : Sep 26, 2015, 3:13:59 PM
    Author     : Sharan
--%>

<%@include file="headeram.jsp" %>

        <div class="clear"></div>
                 
            
            
            <div class="outer">
                <nav>
                    <table class="table">
                        
                        <tr><th>Coins(${theUser.coins})</th></tr>
                        <tr><th>Participants(${theUser.participants})</th></tr>
                        <tr><th>Participation(${theUser.participation})</th></tr>
                        <tr><th>&nbsp;&nbsp;&nbsp;&nbsp; </th></tr>
                        <tr><th><a href="home.jsp">Home</a></th></tr>
                        <tr><th><a href="participate.jsp">Participate</a></th></tr>
                        <tr><th><a href="studies.jsp">My Studies</a></th></tr>
                        <tr><th><a href="recommend.jsp">Recommend</a></th></tr>
                        <tr><th><a href="contact.jsp">Contact</a></th></tr>                     
                       
                    </table>
                    
                    
                </nav>
            <div class="content-wrapper">
                    <div class="container">
                        <h2>About us</h2>
                        <p>Research Exchange Participations is a platform for researchers to exchange participations</p>
                        <p>This aim of this platform is to encourage researchers participate in each others user studies.</p>
                        <p> Moreover, recruiting serious participants has been always a burden on a researcher's shoulder,</p>
                        <p> thus,this platform gives researchers the opportunity to do that effectively and in a suitable </p>
                        <p>  time.</p>
                    </div>
           </div>  
                    
        </div>
        
        
 <%@include file="footer.jsp" %>
