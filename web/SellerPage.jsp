<%@ page import="Objects.User" %>
<%@ page import="Objects.Item" %>
<%@ page import="Objects.Statistic" %>
<%@ page import="java.util.List" %>
<%@ page import="DataBase.DBConnection" %>
<%@ page import="DataBase.DBFactory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <div class="user-container">
        <div class="type-header">
            <span>Seller Page</span>
        </div>
        <div class="left-side-user">
            <div style="float:right;">
                <form id="img-edit">
                    <div class="image-upload">
                        <label id="input-label" for="img-edit-input">
                            <img id="pic-edit-icon" class="edit-icon" src="edit.png"/>
                        </label>
                        <input id="img-edit-input" name="simage" accept="image/gif, image/jpeg, image/png" type="file"/>
                    </div>
                </form>
            </div>

            <%User us = (User) request.getSession().getAttribute("user"); %>
            <%if (us.getImage().contains("https") || us.getImage().contains("http")) {%>
            <img src="<%=us.getImage()%>" style="width: 200px; height: 200px">
            <%} else {%>
            <img src="ImageLoader?FileName=<%=us.getImage()%>" style="width: 200px; height: 200px">
            <%}%>
            <div style="color: #990099">
                <span><%=us.getRating()%>  </span>
                <span><%=us.getVoters()%></span>
            </div>
        </div>
        <div class="center-side-user">

            <div>
                <span style="font-size: 30px"><%=us.getUserName()%></span>
            </div>
            <form id="edit-name" class="edit-forms">
                <input type="text" name='sname' class="user-fields" value="<%=us.getName()%>" readonly>
                <img src="edit.png" class="edit-icon edit-icon-uname">
            </form>
            <form class="edit-forms">
                <label> Email:</label>
                <input type="text" name="semail" class="user-fields" value="<%=us.getEmail()%>" readonly>
            </form>
            <form class="edit-forms" id="edit-mob">
                <label> Mobil:</label>
                <input type="text" name="smob" class="user-fields" value=" <%=us.getMobileNumber()%>" readonly
                       title="Mobile">
                <img src="edit.png" class="edit-icon ">
            </form>
            <form class="edit-forms" id="Tag">
                <label>Tag: </label>
                <textarea name="tag" style="display: none; resize: none;font-size: 15px" rows="1" cols="20"></textarea>
                <img src="edit.png" class="edit-icon ">
            </form>
            <form>
                <input type="text" style="float:left;" id="pass-change-link" class="user-fields" value="Change Password"
                       readonly
                       title="change Password">
            </form>
        </div>
        <div class="right-side-user">
            <div>
                <a href="add-product.jsp">Add New Product</a>
            </div>
            <div>
                <a href="#my-prod">My Products</a>
            </div>
            <div>
                <a href="#stats">My statistics</a>
            </div>
            <div>
                <a href="#com">Comments</a>

            </div>
        </div>
    </div>

    <div class="type-header" style="width:50%; margin-left:25%;font-size: 20px">
        <div style="width: 100%">
            <span id="pass-change-err-msg"
                  style="color:red; display: none ">Error has occurred during password change</span>
            <span id="pass-change-suc-msg" style="color:green; display: none ">Password has Changed Successfully</span>
            <span id="pass-change-wrong-msg" style="color:red; display: none ">Current password is wrong</span></div>
    </div>
    <form class="edit-forms" id="pass-change"
          style="display: none; width: 30%;    margin-left: 30%;   margin-bottom: 80px;">
        <label> Current Password:</label>
        <input type="password" name="curpassword" class="registration-input"
               title="current password">
        <label> New Password:</label>
        <input type="password" name="newpassword" class="registration-input"
               title=" NewPassword">
        <button id="pass-chnage-submit" style="width: 35%;margin-top: 10px " type="submit"
                class="button registration-submit-buy">Update
        </button>
    </form>
</div>
<div class="div-separator"></div>
<div>
    <div class="user-container">
        <div class="type-header">
            <span id="stats">Statistics</span>
        </div>
        <div>
            <div class="wrapper">

                <div class="table">

                    <div class="row-stat header">
                        <div class="cell">
                            Product Name
                        </div>
                        <div class="cell">
                            Product Price
                        </div>
                        <div class="cell">
                            Overall Sell
                        </div>
                        <div class="cell">
                            Per Day
                        </div>
                    </div>
                    <% DBConnection dbc = DBFactory.getDBConnection();
                        List<Statistic> ls = dbc.getTopSoldItems(us.getID());
                        for (Statistic stat : ls) {
                            Item it = dbc.getItemById(stat.getItemID());
                            int avg = stat.getOverall() / stat.getDifDays();
                            int avg2 = stat.getOverall() % stat.getDifDays();
                            int price = (int) (it.getPrice() / 1);
                            int price2 = (int) (it.getPrice() % 1);
                    %>
                    <div class="row-stat">
                        <div class="cell">
                            <%=it.getName()%>
                        </div>
                        <div class="cell">
                            <%=price%>.<%=price2%>
                        </div>
                        <div class="cell">
                            <%=stat.getOverall()%>
                        </div>
                        <div class="cell">
                            <%=avg%>.<%=avg2%>
                        </div>
                    </div>
                    <%}%>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<div class="div-separator"></div>

<div>
    <div id="main">
        <div class="type-header">
            <span id="my-prod">My Products</span>
        </div>
        <div id="product-list-user">
            <%
                List<Item> items = dbc.getItemsBySeller(us.getID());
                for (Item item : items) {
                    out.println("<div class=\"product-user\">" +
                            "                <div>" + item.getName() + "</div>\n" +
                            "                <img src=\"ImageLoader?FileName=" + item.getImage() + " \">\n" +
                            "             <form action=\"item\" method=\"get\">" +
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
                        int id = us.getID();
                        List<Comment> comList = dbc.getUserCommentsByOwner(id, 0, NUMBER_OF_COMMENTS_ON_PAGE);
                        for (Comment comment : comList) {
                            User user = dbc.getSellerByID(comment.getWriterID());
                            if (user == null) user = dbc.getBuyerByID(comment.getWriterID());
                            String image = "";
                            if (user.getImage().contains("https") || user.getImage().contains("http")) {
                                image += user.getImage();
                            } else {
                                image += "ImageLoader?FileName=" + user.getImage();
                            }
                            out.println(" <div class=\"dialogbox\">\n" +
                                    "                    <div style=\"margin-left: 10%\">\n" +
                                    "                        <div style=\"border: 1px solid #ff5e01;border-radius:15%;padding-left: 5px;padding-right:5px;    float:left\">\n" +
                                    "                            <span style=\"font-size: 15px; margin-top: 20px;text-align: center\">" + user.getName() + "</span>\n" +
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

                <form id="comment-form" style="width: 80%; margin-left: 10% ">
                    <textarea style="width: 100%" onkeyup="textAreaAdjust(this)" name="comment" id="comment"
                              placeholder="Comment"></textarea><br>
                    <button style="margin-right: 10%;float:right;margin-top: 10px; color:#990099; border-radius: 10%; font-size: 20px"
                            type="submit" class="button"> submit
                    </button>
                </form>

            </div>
        </div>
    </div>
</div>
