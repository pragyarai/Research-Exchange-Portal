<%-- 
    Document   : editstudy
    Created on : Sep 23, 2015, 7:52:38 PM
    Author     : pragyarai
--%>

        <%@include file="header1.jsp" %>
         <div class="clear"></div>
         <div class="link-wrapper" >
            <span class="link-back">
              Editing a study
            </span>
               <p><b>&#60;&#60;<a href="user?action=main">Back to the main page</a></b></p>
         </div>
         
            <div class="content">
                        <form class="align-form" action="study" method="post">

                            <table>

                                <tbody>
                                    <tr>
                                        <td></td>
                                        <td>
                                            <input type="hidden" value="${study.getCode()}" name="studyCode" >
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><label>Study Name *</label></td>
                                        <td><input type="text" value="${study.getName()}" name="study" ></td>
                                    </tr>
                                    <tr>
                                        <td><label>Question Text *</label></td>
                                        <td><input type="text" value="${study.getQuestion()}" name="question" required="" ></td>
                                    </tr>
                                    <tr>
                                        <td><label>Image </label></td>
                                        <td> <img alt="image" src="//0" ><input type="file" name="image" required=""></td>
                                    </tr>
                                    <tr>
                                        <td><label>Participants *</label></td>
                                        <td><input type="text" name="participants" value="${study.getRequestedParticipants()}" required=""></td>
                                    </tr>
                                    <tr>
                                        <td><label>Description *</label></td>
                                        <td><textarea rows="3" name="description"  cols="20">${study.getDescription()}</textarea>
                                        </td>
                                    </tr>
                                     <tr>
                                        <td></td>
                                        <td><input type="submit" name="action" class="participate" value="Update">
                                        </td>
                                    </tr>
                                </tbody>
                            </table>


                            
                        </form>
           </div>
       
        <%@include file="footer.jsp" %>
    
