<%@ page import="Managers.SiteConstants" %>
<%@ page import="Objects.*" %>
<%@ page import="Managers.ManagerFactory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
</head>
<body>
<%@include file="chat-page.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<link href="css/main.css" rel="stylesheet">
<link href="css/usercss.css" rel="stylesheet">
<script src="Javascript/AjaxSending.js"></script>
<script src="Javascript/passwordscheck.js"></script>
<script src="Javascript/loginAjax.js"></script>
<script src="Javascript/user-edit.js"></script>
<!-- Header -->
<div class="header">
    <div class="header1">
        <a href="\index.jsp" id="logo">Food-Online</a>
    </div>
    <%
        if (session.getAttribute("loggedIn") == null || !(boolean) session.getAttribute("loggedIn")) {
    %>
    <script src="Javascript/script.js"></script>
    <%@include file="visitor.jsp" %>
    <script src="Javascript/script.js"></script>
    <% } else { %>
    <%@include file="user-panel.jsp" %>

    <% }%>
</div>

<%
    if (request.getSession().getAttribute(SiteConstants.TYPE).equals(SiteConstants.SELLER)) {
%>
<%@include file="SellerPage.jsp" %>
<% } else {
%>
<%@include file="BuyerPage.jsp" %>

<% }%>

<div id="footer" style="top:40%"></div>
<!-- Footer -->


</body>
</html>
