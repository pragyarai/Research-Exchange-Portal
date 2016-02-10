<%-- 
    Document   : header
    Created on : Sep 23, 2015, 12:47:32 AM
    Author     : Sharan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
        <meta charset="utf-8">
        <title>ASSIGNMENT 1</title>
        <link rel="stylesheet" href="style/header.css">
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
       
    <body>
    
<header>
    <div>
        <div class="head_title"><a href="user?action=main">Researchers Exchange Participations</a></div>
        <div class="menu">
            <ul>
                <li class="menu-li"><a href="user?action=about">About us</a></li>
                <li class="menu-li"><a href="user?action=how">How it works</a></li>
                <li class="menu-li"><a href="user?action=login">Login</a></li>
            </ul>
        </div>
        
    </div>

    <div class="clear"></div>     
</header>

       