<%-- 
    Document   : question
    Created on : Sep 23, 2015, 7:52:38 PM
    Author     : pragyarai
--%>

        <%@include file="header.jsp" %>

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
            <div class="wrapper">
                <span class="link-back">
                    <b>Question</b>
                </span>
                
            </div>

        </div>

        <div class="tree-con">
              
            <form action="study" method="post">
            <img alt="tree" src="images/tree.jpeg" > 
            <div class="que">
                
                <span>${study.getQuestion()}</span>
              
                    <table>
                     
                        <tbody>
                            <tr>
                                <td>
                                      <select name="choice">
                                          <option>1</option>
                                            <option>2</option>
                                            <option>3</option>
                                            <option>4</option>
                                            <option>5</option>
                                            <option>6</option>
                                            <option>7</option>
                                        </select>
                                </td>
                                <td>
                                    <input type="hidden" name="studycode" value="${study.getCode()}"> 
                                    
                                     <input type="submit" name="action" value="Answer" class="participate"  />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                 </div>
            </form>
        </div>

   
    <%@include file="footer.jsp" %>

