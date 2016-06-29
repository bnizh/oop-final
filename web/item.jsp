<%@ page import="DataBase.DBConnection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <link href="css/main.css" rel="stylesheet">
    <link href="css/usercss.css" rel="stylesheet">
    <script src="Javascript/AjaxSending.js"></script>
    <script src="Javascript/loginAjax.js"></script>
    <script src="Javascript/item.js"></script>
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
        if (session.getAttribute("loggedIn") == null || !(boolean) session.getAttribute("loggedIn")) {
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
<%@ page import="Objects.Item" %>
<%
    int id = Integer.valueOf(request.getParameter("ID"));
    DBConnection dbc = (DBConnection) getServletConfig().getServletContext().getAttribute("dbc");
    Item item = dbc.getItemById(id);%>

<%@ page import="Objects.Rating" %>
<%@ page import="static Managers.SiteConstants.LOGGED_IN" %>
<%@ page import="static Managers.SiteConstants.USER" %>
<%@ page import="static Managers.SiteConstants.*" %>
<% Boolean logged = (Boolean) request.getSession().getAttribute(LOGGED_IN);
    Objects.Seller us = dbc.getSellerByID(item.getOwnerID());%>
<div>
    <div class="user-container">
        <div class="type-header">
            <span><%=item.getName()%></span>
        </div>
        <div class="left-side-user-visitor">
            <img src="ImageLoader?FileName=<%=item.getImage()%>">
            <%

                if (logged) {
                    User visitor = (User) request.getSession().getAttribute(USER);
                    Rating rating = dbc.getRating(id, visitor.getID(), ITEM);

                    if (rating == null) {
                        out.println("<div id=\"rate-result\" style=\"display:none \">\n" +
                                "                <label class=\"user-fields\" style=\" text-align:center; margin-right: 10%\">Rating:" + item.getRating() + "</label>\n" +
                                "                <label class=\"user-fields\" style=\"text-align:center;\">voters: " + item.getVoters() + "</label></div>\n" +
                                "            <div class=\"stars\" style=\"display: block;\">\n" +
                                "                <form id=\"rating-form\" action=\"\">\n" +
                                "                    <input type=\"hidden\" id=\"user-id-form\" name=\"ID\" value=\"" + id + "\">\n" +
                                "                    <input class=\"star star-5\" id=\"star-5\" value=\"5\" type=\"radio\" name=\"star\"/>\n" +
                                "                    <label class=\"star star-5\" for=\"star-5\"></label>\n" +
                                "                    <input class=\"star star-4\" id=\"star-4\" value=\"4\" type=\"radio\" name=\"star\"/>\n" +
                                "                    <label class=\"star star-4\" for=\"star-4\"></label>\n" +
                                "                    <input class=\"star star-3\" id=\"star-3\" value=\"3\" type=\"radio\" name=\"star\"/>\n" +
                                "                    <label class=\"star star-3\" for=\"star-3\"></label>\n" +
                                "                    <input class=\"star star-2\" id=\"star-2\" value=\"2\" type=\"radio\" name=\"star\"/>\n" +
                                "                    <label class=\"star star-2\" for=\"star-2\"></label>\n" +
                                "                    <input class=\"star star-1\" id=\"star-1\" value=\"1\" type=\"radio\" name=\"star\"/>\n" +
                                "                    <label class=\"star star-1\" for=\"star-1\"></label>\n" +
                                "                </form>\n" +
                                "            </div>");
                    } else {
                        out.println(" <div id=\"rate-result\">\n" +
                                "                <label class=\"user-fields\" style=\" text-align:center; margin-right: 10%\">Rating: " + item.getRating() + "</label>\n" +
                                "                <label class=\"user-fields\" style=\"text-align:center;\">voters: " + item.getVoters() + "</label></div>\n");

                    }
                } else {
                    out.println(" <div id=\"rate-result\">\n" +
                            "                <label class=\"user-fields\" style=\" text-align:center; margin-right: 10%\">Rating: " + us.getRating() + "</label>\n" +
                            "                <label class=\"user-fields\" style=\"text-align:center;\">voters: " + item.getVoters() + "</label></div>\n");
                }
            %>

        </div>

        <% if (logged) {
            User visitor = (User) request.getSession().getAttribute(USER);
        %>

        <input id="username" type="hidden" value="<%=visitor.getUserName()%>">
        <input id="reciver-username" type="hidden" value="<%=us.getUserName()%>">
        <input id="receiver-ID" type="hidden" value="<%=us.getID()%>">
        <input id="itemID" type="hidden" value="<%=item.getID()%>">
        <input type="number" id ="amount"
               name="amount"
               value="0"
               step="1"
               placeholder="Amount">
        <button id="sendNot" class="button" style="font-size: 15px;border-radius: 10%; color: #990099;"
                href="#"><img src="buy.png"
                              style="width: 40px;height: auto;margin-right: 8px">Buy Item
        </button>
        <div style="float:right">

                <button id="link_add" class="button" style="font-size: 15px;border-radius: 10%; color: #990099;"
                        href="#"><img src="chat.png" id="chat-icon"
                                      style="width: 40px;height: auto;margin-right: 8px">Start Chat with Seller
                </button>
        </div>

        <% }%>


        <div class="center-side-user-visitor">

            <div class="profile-visitor">
                <label class="user-fields"><%=item.getName()%>
                </label>
            </div>
            <div class="profile-visitor">
                <label style="color: #ff5e01"> Price:</label><label><%=item.getPrice()%>
            </label>
            </div>
            <div class="profile-visitor">
                <label style="color: #ff5e01"> Category:</label>
                <label><%=dbc.getCategory(item.getCategoryID()).getName()%>
                </label>
            </div>
            <div class="profile-visitor">
                <label style="color: #ff5e01"> Description:</label> <label
                    style="font-size: 15px"><%=item.getDescription()%>
            </label>
            </div>
        </div>

    </div>
</div>
<div class="div-separator"></div>
<div>
    <div class="user-container">
        <div class="type-header">
            <span id="com">Comments</span>
        </div>
    </div>
</div>
<div id="footer"></div>
<!-- Footer -->


</body>
</html>
