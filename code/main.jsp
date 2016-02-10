<%-- 
    Document   : mainhow
    Created on : Sep 26, 2015, 10:23:26 AM
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
                        <tr><th><a href="user?action=home">Home</a></th></tr>
                        <tr><th><a href="study?action=participate">Participate</a></th></tr>
                        <tr><th><a href="study?action=studies">My Studies</a></th></tr>
                        <tr><th><a href="recommend.jsp">Recommend</a></th></tr>
                        <tr><th><a href="contact.jsp">Contact</a></th></tr>                     
                       
                    </table>
                    
                    
                </nav>
            <div class=" content-wrapper"> 
                   
                 <div class="container">
                        <h1>How it works</h1>
                        <p>This site was built to help researcher conduct their studies.</p>
                        <p>1 participation= 1 coin</p>
                        <p> <b>To participate</b> go to the "participate" section and choose study to complete.</p>
                        <p><b>To get participants</b> submit your user studies here to start getting Participant.In order to do</p>
                        <p>so, you must have enough coin in your account.</p>
                 </div>
                    
        </div>
        </div>
        
 <%@include file="footer.jsp" %>
