<%@ page import="DataBase.DBConnection" %>
<%@ page import="Objects.Category" %>
<%@ page import="Objects.Item" %>
<%@ page import="Objects.Seller" %>
<%@ page import="Objects.Admin" %>
<%@ page import="Objects.Message" %>
<%@ page import="java.util.List" %>
<%@ page import="static Managers.SiteConstants.NUMBER_OF_ITEMS_ON_PAGE" %>
<%@ page import="static Managers.SiteConstants.ADMIN_LOGGED_IN" %>
<%@ page import="DataBase.DBFactory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Food-Online</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <link href="css/main.css" rel="stylesheet">
    <script src="Javascript/AjaxSending.js"></script>
    <script src="Javascript/passwordscheck.js"></script>
    <script src="Javascript/loginAjax.js"></script>
    <script src="Javascript/scrolling.js"></script>
    <script src="Javascript/search.js"></script>
</head>
<body>
<!-- Header -->

<div id="status">
</div>
<div class="header">
    <div class="header1">
        <a href="\index.jsp" id="logo">Food-Online</a>
    </div>
    <%
        Boolean b = false;
        Boolean adm = false;
        if (session.getAttribute(LOGGED_IN) != null)
            b = (Boolean) session.getAttribute(LOGGED_IN);

        if (session.getAttribute(ADMIN_LOGGED_IN) != null)
            adm = (Boolean) session.getAttribute(ADMIN_LOGGED_IN);
        if (!b && !adm) {
    %>
    <script src="Javascript/script.js"></script>
    <%@include file="visitor.jsp" %>

    <% } else {
    %>
    <%@include file="user-panel.jsp" %>
    <% }%>
</div>
<!-- Header -->
<div>
    <div class="user-container">
        <h2 style="text-align: center">Message</h2>
        <form action="message" method="post">
            <div style="width: 100%">
                <label style="margin-left: 40%;margin-right: 5%;font-size: 20px" class=""><input
                        id="user"
                        class="type-of-message"
                        type="radio"
                        name="type"
                        value="user"
                        checked="checked">To User</label>
                <label style="font-size: 20px" class=""><input type="radio"
                                                               id="admin"
                                                               value="admin"
                                                               class="type-of-message"
                                                               name="type">To Admin</label>
            </div>
            <div id="addressee" style="margin-left:10%;margin-bottom:30px;width: 80%">
                <span style="font-size: 20px;margin-left: 70px;margin-right: 30px">Username Of addressee</span><input

                    name="receiver" placeholder="Username" type="text" style="border:2px solid #6b3a99;width: 60%">
            </div>
        <textarea placeholder="Message text" name="text"
                  style="margin-left:5%; resize: none;width:90%;background-color: white; height: 180px"></textarea>
            <button type="submit" style="width: 10%; float: right;margin-right:20%;border: 2px solid #6b3a99;
         background-color: white; border-radius: 10px; margin-top: 20px ">
                Send
            </button>
        </form>

    </div>
</div>
<script>  $('.type-of-message').change(function () {

    if ($('#admin').is(':checked')) {
        $('#addressee').css("visibility", "hidden").css("display", "none");
    }
    else {
        $('#addressee').css("visibility", "visible  ").css("display", "block");

    }
});</script>
</body>
</html>
