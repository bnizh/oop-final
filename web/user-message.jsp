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
    <link href="css/usercss.css" rel="stylesheet">
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
        <%
            DBConnection dbc = DBFactory.getDBConnection();
            Message ms = (Message) request.getSession().getAttribute("message");
            if (ms == null) {
                String redirectURL = "/user-message-inbox.jsp";
                response.sendRedirect(redirectURL);
                return;
            }
            ms.setRead(true);
            if (ms.getWriterID() != 0) {
                User us = dbc.getBuyerByID(ms.getWriterID());
                if (us == null) us = dbc.getSellerByID(ms.getWriterID());
        %>
        <h2 style="text-align: center">Message</h2>
        <div style="overflow: hidden;width:100%;border-top: 2px solid #990099;border-bottom: 2px solid #990099;margin-bottom: 40px;margin-top: 20px;padding-bottom: 20px;text-align: center">
            <span style="width:100%; margin-bottom:15px;font-size: 20px;">Author:</span>
            <table class="user-table" style="margin-top: 20px">
                <thead>
                <tr>
                    <th style="width: 15%" class="row-1 row-ID">ID</th>
                    <th style="width: 15%" class="row-1 row-image">Image</th>
                    <th style="width: 35%" class="row-3 row-username">Username</th>
                    <th style="width: 35%" class="row-5 row-type">Name</th>
                </tr>
                </thead>
                <tbody>
                <td><%=us.getID()%>
                </td>
                <%if (us.getImage().contains("https") || us.getImage().contains("http")) {%>
                <td><img src="<%=us.getImage()%>" style="height: 40px"></td>
                <%} else {%>
                <td><img src="ImageLoader?FileName=<%=us.getImage()%>" style="height: 40px"></td>
                <%}%>
                <td><%=us.getUserName()%>
                </td>
                <td><%=us.getName()%>
                </td>
                </tbody>
            </table>
        </div>
        <%} else {%>
        <h2 style="text-align: center">Message</h2>
        <div style="overflow: hidden;width:100%;border-top: 2px solid #990099;border-bottom: 2px solid #990099;margin-bottom: 40px;margin-top: 20px;padding-bottom: 20px;text-align: center">
            <span style="width:100%; margin-bottom:15px;font-size: 20px;">Author:Administration</span>
        </div><%}%>
            <textarea readonly
                      style="margin-left:5%; resize: none;width:90%;background-color: white; height: 180px"><%=ms.getMessageContent()%></textarea>

    </div>
</div>
<%
    request.getSession().removeAttribute("message");
%>
</body>

</html>
