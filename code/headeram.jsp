<%-- 
    Document   : headeram
    Created on : 27 Sep, 2015, 1:00:17 AM
    Author     : Sharan
--%>
<%@page import="Mypackage.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>ASSIGNMENT 1</title>
        <link rel="stylesheet" href="style/headeram.css">
        <link rel="stylesheet" href="style/style.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

        <script type="text/javascript">
            jQuery(document).ready(function($){
                 var url = window.location.pathname, 
            urlRegExp = new RegExp(url.replace(/\/$/,'') + "$"); // create regexp to match current url pathname and remove trailing slash if present as it could collide with the link in navigation in case trailing slash wasn't present there
            // now grab every link from the navigation
            $('.menu-li a').each(function(){
                // and test its normalized href against the url pathname regexp
                if(urlRegExp.test(this.href.replace(/\/$/,''))){
                    $(this).addClass('active');
                }
            });
            });
        </script>
       </head>
    <body>
    
<header>
    <div class="side-header">
        <div class="head_title"><a href="user?action=home">Researchers Exchange Participations</a></div>
        <div class="menu">
            <ul>
                <li class="menu-li"><a href="user?action=about">About us</a></li>
                <li class="menu-li"><a href="user?action=how">How it works</a></li>
                <%  String name;
                    User user= (User)request.getSession().getAttribute("theUser") ;
                    name=user.getName();
                %>
                <li class="name"><b>Hello, <% out.print(name); %>  </b></li>
            </ul>
            
        </div>
        
    </div>

    <div class="clear"></div>

</header>
        
