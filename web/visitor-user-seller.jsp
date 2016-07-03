<%@ page import="Objects.Item" %>
<%@ page import="DataBase.DBConnection" %>
<%@ page import="static Managers.SiteConstants.LOGGED_IN" %>
<%@ page import="static Managers.SiteConstants.USER" %>
<%@ page import="Objects.User" %>
<%@ page import="Objects.Rating" %>
<%@ page import="java.util.List" %>
<%@ page import="Objects.Comment" %>
<%@ page import="static Managers.SiteConstants.NUMBER_OF_ITEMS_ON_PAGE" %>
<%@ page import="static Managers.SiteConstants.*" %>
<% Boolean logged = (Boolean) request.getSession().getAttribute(LOGGED_IN);
    Objects.Seller us = dbc.getSellerByID(id);%>
<div>
    <div class="user-container">
        <div class="type-header">
            <span style="margin-right: 15px">Seller</span>
            <span><%=us.getUserName()%></span>

        </div>
        <div class="left-side-user-visitor">
            <%if(us.getImage().contains("https")||us.getImage().contains("http")){%>
            <img src="<%=us.getImage()%>" style="width: 200px; height: 200px">
            <%}else{%>
            <img src="ImageLoader?FileName=<%=us.getImage()%>" style="width: 200px; height: 200px">
            <%}%>

            <%

                if (logged) {

                    User visitor = (User) request.getSession().getAttribute(USER);
                    Rating rating = dbc.getRating(id, visitor.getID(), USER);

                    if (rating == null) {
                        out.println("<div id=\"rate-result\" style=\"display:none \">\n" +
                                "                <label class=\"user-fields\" style=\" text-align:center; margin-right: 10%\">Rating:" + us.getRating() + "</label>\n" +
                                "                <label class=\"user-fields\" style=\"text-align:center;\">voters: " + us.getVoters() + "</label></div>\n" +
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
                                "                <label class=\"user-fields\" style=\" text-align:center; margin-right: 10%\">Rating: " + us.getRating() + "</label>\n" +
                                "                <label class=\"user-fields\" style=\"text-align:center;\">voters: " + us.getVoters() + "</label></div>\n");

                    }
                } else {
                    out.println(" <div id=\"rate-result\">\n" +
                            "                <label class=\"user-fields\" style=\" text-align:center; margin-right: 10%\">Rating: " + us.getRating() + "</label>\n" +
                            "                <label class=\"user-fields\" style=\"text-align:center;\">voters: " + us.getVoters() + "</label></div>\n");
                }
                List<Item> items = dbc.getItemsBySeller(id);

            %>

        </div>
        <div class="center-side-user-visitor">

            <div class="profile-visitor">
                <label class="user-fields"><%=us.getName()%>
                </label>
            </div>
            <div class="profile-visitor">
                <label> Email:</label><label><%=us.getEmail()%>
            </label>
            </div>
            <div class="profile-visitor">
                <label> Mobile:</label> <label><%=us.getMobileNumber()%>
            </label>
            </div>
            <div class="profile-visitor">
                <% if (logged) {
                    User visitor = (User) request.getSession().getAttribute(USER);
                %>

                <input id="username" type="hidden" value="<%=visitor.getUserName()%>">
                <input id="reciver-username" type="hidden" value="<%=us.getUserName()%>">
                <input id="reciver-id" type="hidden" value="<%=us.getID()%>">
                <div style="width: 100%;text-align: center">
                    <button id="link_add" class="button" style="font-size: 30px;border-radius: 10%; color: #990099;"
                            href="#"><img src="chat.png" id="chat-icon"
                                          style="width: 40px;height: auto;margin-right: 8px">Start Chat
                    </button>
                </div>
                <% }%>
            </div>
        </div>


    </div>
</div>

<div>
    <div id="main">
        <div class="type-header">
            <span id="my-prod">Products</span>
        </div>
        <div id="product-list-user">
            <%
                for (Item item : items) {
                    out.println("<div class=\"product-user\">" +
                            "                <div>" + item.getName() + "</div>\n" +
                            "                <img src=\"ImageLoader?FileName=" + item.getImage() + " \">\n" +
                            "<form action=\"item\" method=\"get\">" +
                            "<input name=\"ID\" type=\"hidden\" value=\"" + item.getID() + "\">\n" +
                            "<button  type=\"submit\" class=\"button\"> Details</button>\n" +
                            "</form>" +
                            "            </div>");

                }
            %>
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
                        List<Comment> comList = dbc.getUserCommentsByOwner(id, 0, NUMBER_OF_COMMENTS_ON_PAGE);
                        for (Comment comment : comList) {
                            User user = dbc.getSellerByID(comment.getWriterID());
                            if (user == null) user = dbc.getBuyerByID(comment.getWriterID());
                            String image= "";
                            if(user.getImage().contains("https")||user.getImage().contains("http")) {
                                image += user.getImage();
                            }else{
                                image +="ImageLoader?FileName="+user.getImage();
                            }
                            out.println(" <div class=\"dialogbox\">\n" +
                                    "                    <div style=\"margin-left: 10%\">\n" +
                                    "                        <div style=\"border: 1px solid #ff5e01;border-radius:15%;padding-left: 5px;padding-right:5px;    float:left\">\n" +
                                    "                            <span style=\"font-size: 15px; margin-top: 20px;text-align: center\">" + user.getUserName() + "</span>\n" +
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
                        out.println("<input type=\"hidden\" id=\"comment-owner-type\" value=\"user\" >\n");

                    %>
                </div>
                <img style="display: none" src="loading.gif" alt="Loading…"/>
                <% if (comList.size() == NUMBER_OF_COMMENTS_ON_PAGE) {%>
                <button id="load-more-comment" style="text-align: center">Load More</button>
                <%}%> <input type="hidden" id="comment-owner-id" name="ID" value="<%=id%>">
                <%
                    if (logged) {
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