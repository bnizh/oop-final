<%@ page import="Managers.SiteConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <link href="main.css" rel="stylesheet">
    <link href="usercss.css" rel="stylesheet">
    <script src="AjaxSending.js"></script>
    <script src="passwordscheck.js"></script>
    <script src="loginAjax.js"></script>
    <script src="user-edit.js"></script>
</head>
<body>
<!-- Header -->
<div class="header">
    <div class="header1">
        <a href="\index.jsp" id="logo">Food-Online</a>
    </div>
    <%
        if (session.getAttribute("loggedIn") == null || !(boolean) session.getAttribute("loggedIn")) {
    %>
    <script src="script.js"></script>
    <%@include file="visitor.jsp" %>
    <script src="script.js"></script>
    <% } else {
    %>
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
