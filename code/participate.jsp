<%-- 
    Document   : participate
    Created on : Sep 23, 2015, 7:52:38 PM
    Author     : pragyarai
--%>

<%@page import="Mypackage.Study"%>
<%@include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"   %>
<script>
function myFunction(clicked_id) {
    document.forms[0].studycode.value=clicked_id;
    //alert(document.forms[0].studycode.value);
    document.forms[0].action = "study";    
     document.forms[0].submit();
}
</script>
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
            <div class=" wrapper">
                <span class="link-back">
                    <b>Studies</b>
                </span>
                
            </div>

        </div>

        <div class="content">
            <p> ${requestScope.errorMessage}</p>
             <form action="study" method="post">
                <table class="tb-border">
                    <thead>
                        
                         <c:if test="${not empty allStudies}">
                        <tr class="tb-head">
                            <th>Study Name</th>
                            <th>Image</th>
                            <th>Question</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    
                    <c:set var="count" value="1" scope="page" />
                    <tr class="hidden">
                       
                            <td colspan="4"><input type="hidden"  name="studycode" value=""></td>
                    </tr>
                    <c:forEach var="asd" items="${requestScope.allStudies}">
                         <tr class="<c:out value="${count%2 ne 0 ? 'odd': 'even'}"/>">
                           
                            <td><c:out value="${asd.name}"/></td>
                            <td>
                                <img alt="tree" src="images/download.jpeg"> 
                            </td>
                            <td><c:out value="${asd.question}"/></td>
                            <td>
                                
                                <input onclick="myFunction(${asd.code})" class="participate" name="action" type="submit" value ="Participate"/>
                                
                            </td>
                        </tr>
                       
                        
                    
                     <c:set var="count" value="${count + 1}" scope="page"/>    
                    </c:forEach>      
                 </c:if>
                 <c:if test="${empty allStudies}">
                            <h3> no entries found </h3>
                 </c:if>
                           

                  
                </table>
        </form>
        </div>

    <%@include file="footer.jsp" %>
