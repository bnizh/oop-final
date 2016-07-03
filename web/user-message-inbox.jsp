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
        <table class="user-table" style="margin-top: 20px">
            <thead>
            <tr>
                <th style="width: 15%" class="row-1 row-ID"></th>
                <th style="width: 35%" class="row-3 row-username">Author</th>
                <th style="width: 35%" class="row-5 row-type">date</th>
                <th style="width: 15%" class="row-9 row-x">X</th>
            </tr>
            </thead>
            <tbody>
            <%
                User reciver = (User) request.getSession().getAttribute(USER);
                DBConnection dbc = DBFactory.getDBConnection();
                List<Message> messages = dbc.getMessageByReceiverId(reciver.getID(), MESSAGE_ADMIN_TO_USER);
                for (Message message : messages) {
                    User us = dbc.getBuyerByID(message.getWriterID());
                    if (us == null) us = dbc.getSellerByID(message.getWriterID());

                    out.println("<tr class=\"clickable-row\">" +
                            " <input type=\"hidden\" class=\"message-id\" value=\"" + message.getMessageID() + "\">");
                    if (message.isRead())
                        out.println(
                                "<td><img src=\"read.png\" style=\";height: 40px\" alt=\"\"></td>");
                    else out.println("<td><img src=\"unread.png\" style=\";height: 40px\" alt=\"\"></td>");
                    out.println(" <td>Administration </td>\n" +
                            " <td>" + message.getDateOfSend() + "</td>" +
                            "<td> <a class=\"delete-item\" style=\"color: red;text-decoration:none;font-size: 20px\">X</a></td>\n" +
                            "                    </tr>");

                }
            %>

            <%
                List<Message> messagesUser = dbc.getMessageByReceiverId(reciver.getID(), MESSAGE_USER_TO_USER);
                for (Message message : messagesUser) {
                    User us = dbc.getBuyerByID(message.getWriterID());
                    if (us == null) us = dbc.getSellerByID(message.getWriterID());
                    out.println("<tr class=\"clickable-row\">" +
                            " <input type=\"hidden\" class=\"message-id\" value=\"" + message.getMessageID() + "\">");
                    if (message.isRead())
                        out.println("<td><img src=\"read.png\" style=\";height: 40px\" alt=\"\"></td>");
                    else out.println("<td><img src=\"unread.png\" style=\";height: 40px\" alt=\"\"></td>");
                    out.println(" <td>" + us.getName() + "</td>\n" +
                            " <td>" + message.getDateOfSend() + "</td>" +
                            "<td> <a class=\"delete-item\" style=\"color: red;text-decoration:none;font-size: 20px\">X</a></td>\n" +
                            "                    </tr>");
                }
            %>
            </tbody>
        </table>
    </div>
</div>
</body>
<script>
    $(".clickable-row").click(function (event) {
        event.preventDefault();
        $.ajax({
            url: 'message',
            type: 'GET',
            data: {
                messageID: $(this).children('.message-id').val(),
                type: "user"
            },
            cache: false,
            dataType: "text",
        }).done(function (response) {
            window.location.href = "http://localhost:8080/" + response;
        });

        return false;
    })
</script>
</html>
