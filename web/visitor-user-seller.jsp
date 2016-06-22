<%@ page import="Objects.Item" %>
<%@ page import="DataBase.DBConnection" %>
<%@ page import="static Managers.SiteConstants.LOGGED_IN" %>
<%@ page import="static Managers.SiteConstants.USER" %>
<%@ page import="Objects.User" %>
<%@ page import="Objects.Rating" %>
<%@ page import="java.util.List" %>
<% Boolean logged = (Boolean) request.getSession().getAttribute(LOGGED_IN);
    Objects.Seller us = dbc.getSellerByID(id);%>
<div>
    <div class="user-container">
        <div class="type-header">
            <span style="margin-right: 15px">Seller</span>
            <span><%=us.getUserName()%></span>
        </div>
        <div class="left-side-user-visitor">
            <img src="ImageLoader?FileName=<%=us.getImage()%>">

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
        </div>
    </div>
</div>