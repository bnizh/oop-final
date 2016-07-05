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
        Boolean adm = (Boolean) session.getAttribute(ADMIN_LOGGED_IN);
        if (!b && !adm) {
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
<%
    int id = Integer.valueOf(request.getParameter("ID"));
    DBConnection dbc = (DBConnection) getServletConfig().getServletContext().getAttribute("dbc");
    Item item = dbc.getItemById(id);%>

<%@ page import="static Managers.SiteConstants.LOGGED_IN" %>
<%@ page import="static Managers.SiteConstants.USER" %>
<%@ page import="static Managers.SiteConstants.*" %>
<%@ page import="Objects.*" %>
<%@ page import="java.util.List" %>
<% Boolean loggedin = (Boolean) request.getSession().getAttribute(LOGGED_IN);
    Objects.Seller us = dbc.getSellerByID(item.getOwnerID());%>
<div>
    <div class="user-container">
        <div class="type-header">
            <span><%=item.getName()%></span>
        </div>
        <div class="left-side-user-visitor">
            <img src="ImageLoader?FileName=<%=item.getImage()%>">
            <%

                if (loggedin) {
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

        <% if (loggedin) {
            User visitor = (User) request.getSession().getAttribute(USER);
        %>

        <input id="username" type="hidden" value="<%=visitor.getUserName()%>">
        <input id="reciver-username" type="hidden" value="<%=us.getUserName()%>">
        <input id="receiver-ID" type="hidden" value="<%=us.getID()%>">
        <input id="receiver-ID1" type="hidden" value="<%=us.getID()%>">
        <input id="itemID" type="hidden" value="<%=item.getID()%>">
        <% if (request.getSession().getAttribute(TYPE).equals(BUYER)) {%>
        <input type="number" id="amount"
               name="amount"
               value="0"
               step="1"
               placeholder="Amount">
        <button id="sendNot" class="button" style="font-size: 15px;border-radius: 10%; color: #990099;"
                href="#"><img src="buy.png"
                              style="width: 40px;height: auto;margin-right: 8px">Buy Item
        </button>
        <%}%>
        <div style="float:right">
            <button id="link_add" class="button" style="font-size: 15px;border-radius: 10%; color: #990099;"
                    href="#"><img src="chat.png" id="chat-icon"
                                  style="width: 40px;height: auto;margin-right: 8px">Start Chat with Seller
            </button>
        </div>

        <% }%>


        <div class="center-side-user-visitor">

            <div class="profile-visitor">
                <label style="float:left; color: #ff5e01"> Owner:</label> <a
                    style="color: #990099;text-decoration: none;float: left;font-size: 20px"
                    href="user?ID=<%=us.getID()%>" class="user-fields"><%=us.getUserName()%>
            </a>
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
            <div class="container">
                <div id="comments-box">
                    <%
                        id = Integer.valueOf(request.getParameter("ID"));
                        List<Comment> comList = dbc.getItemCommentsByOwner(id, 0, NUMBER_OF_COMMENTS_ON_PAGE);
                        for (Comment comment : comList) {
                            User writer = dbc.getSellerByID(comment.getWriterID());
                            if (writer == null) writer = dbc.getBuyerByID(comment.getWriterID());
                            String image = "";
                            if (writer.getImage().contains("https") || writer.getImage().contains("http")) {
                                image += writer.getImage();
                            } else {
                                image += "ImageLoader?FileName=" + writer.getImage();
                            }
                            out.println(" <div class=\"dialogbox\">\n" +
                                    "                    <div style=\"margin-left: 10%\">\n" +
                                    "                        <div style=\"border: 1px solid #ff5e01;border-radius:15%;padding-left: 5px;padding-right:5px;    float:left\">\n" +
                                    "                            <span style=\"font-size: 15px; margin-top: 20px;text-align: center\">" + writer.getUserName() + "</span>\n" +
                                    "                            <div style=\"width: 100%\"><img src=\"" + image + "\"\n" +
                                    "                                                          style=\"width: 50px;height: 50px;text-align: center\"></div>\n" +
                                    "                        </div>\n" +
                                    "                        <div class=\"comment-body\">\n" +
                                    "                            <span class=\"tip tip-left\"></span>\n" +
                                    "                            <div class=\"message\">\n" + "<span style=\"float:right;padding-right:20px;color:red\"> " +
                                    "                                " + comment.getDateOfWrite() + "   </span>" + "<br>" +
                                    "                                <span>" + comment.getComment() + "</span>\n" +
                                    "                            </div>\n" +
                                    "                        </div>\n" +
                                    "                    </div>\n" +
                                    "                </div>");
                        }
                        out.println("<input type=\"hidden\" name=\"page\" id=\"comment-page\" value=\"" + 0 + "\" >");
                        out.println("<input type=\"hidden\" id=\"comment-owner-type\" value=\"item\" >\n");

                    %>
                </div>
                <img style="display: none" src="loading.gif" alt="Loading…"/>
                <% if (comList.size() == NUMBER_OF_COMMENTS_ON_PAGE) {%>
                <button id="load-more-comment" style="text-align: center">Load More</button>
                <%}%>
                <input type="hidden" id="comment-owner-id" name="ID" value="<%=id%>">
                <%
                    if (loggedin) {
                %>
                <form id="comment-form" style="width: 80%; margin-left: 10% ">
                    <textarea style="width: 100%" onkeyup="textAreaAdjust(this)" name="comment" id="comment"
                              placeholder="Comment"></textarea><br>
                    <button style="margin-right: 10%;float:right;margin-top: 10px; color:#990099; border-radius: 10%; font-size: 20px"
                            type="submit" class="button"> submit
                    </button>
                </form>
                <%}%>
            </div>
        </div>
    </div>
</div>
<script>


    function textAreaAdjust(o) {
        o.style.height = "1px";
        o.style.height = (25 + o.scrollHeight) + "px";
    }
</script>
<div id="footer"></div>
<!-- Footer -->


</body>
</html>
