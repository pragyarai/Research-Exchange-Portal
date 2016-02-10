<%-- 
    Document   : studies
    Created on : Sep 26, 2015, 6:28:57 PM
    Author     : Sharan
--%>


<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Mypackage.Study"%>
<%@include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"   %>
<script>
function myFunction(clicked_id) {
    document.forms[0].studyCode.value=clicked_id;
    //alert(document.forms[0].studyCode.value);
    document.forms[0].action = "study";    
     document.forms[0].submit();
}
</script>


        <div class="clear"></div>
    
             <div class="link-wrapper" >
            <span class="link-back">
             My Studies
            </span>
               <p><b><a href="study?action=add">Add a new study</a></b></p>  
               <p><b><a href="user?action=main"> Back to the main page</a></b></p>
         </div>
                      
        <div class="content">
            <form action="study" method="post">
                <table class="tb-border">
                  
                    <c:if test="${not empty allStudies}">
                    <thead>
                        <tr class="tb-head">
                            <th>Study Name</th>
                            <th>Requested Participants</th>
                            <th>Participations</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody class="table-body">
                       <tr class="hidden">
                            <td colspan="5"><input  type="hidden" name="studyCode" value=""></td>
                        </tr>
                       <c:set var="count" value="1" scope="page" />
                        <c:forEach var="asd" items="${requestScope.allStudies}">
                       
                        <tr class="<c:out value="${count%2 ne 0 ? 'odd': 'even'}"/>">
                           
                            <td><c:out value="${asd.name}"/></td>
                            <td><c:out value="${asd.requestedParticipants}"/></td>                            
                            <td><c:out value="${asd.numOfParticipants}"/></td>
                             
                            <td>

                                <input onclick="myFunction(${asd.code})" class="participate" name="action" type="submit" value ="<c:out value="${asd.status != 'start' ? 'Start': 'Stop'}"/>"/>
                                      
                            </td>
                            <td>
                                  <input  onclick="myFunction(${asd.code})"  type="submit" class="participate" name="action" value="Edit"> 
                                 
                            </td>
                            
                         
                        </tr>
                        <c:set var="count" value="${count + 1}" scope="page"/>
                        </c:forEach>                 
                    </tbody>
                        
                    </c:if>
                     
                <c:if test="${ empty allStudies}">
                    <h3> No entries found </h3>
                </c:if>
                </table>
                
                
            </form>
        </div>

    <%@include file="footer.jsp" %>
