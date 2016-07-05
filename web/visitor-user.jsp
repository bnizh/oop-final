<%@ page import="DataBase.DBConnection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>food-Online</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <link href="css/main.css" rel="stylesheet">
    <link href="css/usercss.css" rel="stylesheet">
    <script src="Javascript/AjaxSending.js"></script>
    <script src="Javascript/loginAjax.js"></script>
    <script src="Javascript/user-edit.js"></script>
    <script src="Javascript/comment.js"></script>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}//netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
</head>
<body>
<!-- Header -->
<div class="header">
    <div class="header1">
        <a href="\index.jsp" id="logo">Food-Online</a>
    </div>
    <%
        Boolean b = (Boolean) session.getAttribute(LOGGED_IN);
        Boolean adm=(Boolean) session.getAttribute(ADMIN_LOGGED_IN);
        if (!b&&!adm) {
    %>
    <script src="Javascript/script.js"></script>
    <%@include file="visitor.jsp" %>
    <script src="Javascript/script.js"></script>
    <% } else {
    %>
    <%@include file="user-panel.jsp" %>

    <% }%>
</div>
<%@ page import="static Managers.SiteConstants.LOGGED_IN" %>
<%@ page import="static Managers.SiteConstants.USER" %>
<%@ page import="Objects.Seller" %>
<%@ page import="DataBase.DBFactory" %>
<%
    int id = Integer.valueOf(request.getParameter("ID"));
    DBConnection dbc = DBFactory.getDBConnection();
    Seller seller = dbc.getSellerByID(id);%>

<%
    if (seller!=null) {
%>
<%@include file="visitor-user-seller.jsp" %>

<% } else {
%>
<%@include file="visitor-user-buyer.jsp" %>
<% }%>

<div id="footer"></div>
<!-- Footer -->


</body>
</html>
