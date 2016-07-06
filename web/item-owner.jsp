<%@ page import="DataBase.DBFactory" %>
<%@ page import="DataBase.DBConnection" %>
<%@ page import="Objects.*" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Cart</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <link href="css/main.css" rel="stylesheet">
    <link href="css/usercss.css" rel="stylesheet">
    <link href="css/item-css.css" rel="stylesheet">
    <script src="Javascript/AjaxSending.js"></script>
    <script src="Javascript/passwordscheck.js"></script>
    <script src="Javascript/loginAjax.js"></script>
    <script src="Javascript/item.js"></script>
</head>
<body>
<!-- Header -->
<div class="header">
    <div class="header1">
        <a href="\index.jsp" id="logo">Food-Online</a>

    </div>
    <%
        Boolean b = (Boolean) session.getAttribute("loggedIn");
        if (!b) {
    %>
    <script src="Javascript/script.js"></script>
    <%@include file="visitor.jsp" %>

    <% } else {
    %>
    <%@include file="user-panel.jsp" %>
    <% }%>
</div>
<!-- Header -->
<%
    DBConnection dbc = DBFactory.getDBConnection();
    Item item = dbc.getItemById((Integer) request.getSession().getAttribute("itemID"));
    Seller seller = dbc.getSellerByID(item.getOwnerID());

%>
<!-- Main -->
<div>
    <div class="user-container">
        <div class="type-header">
            <span><%=item.getName()%></span>
        </div>
        <div class="type-header">

        </div>
        <div class="left-side-item">
            <div style="float:right;">
                <form id="img-edit">
                    <div class="image-upload">
                        <label id="input-label" for="img-edit-input">
                            <img id="pic-edit-icon" class="edit-icon" src="edit.png"/>
                        </label>
                        <input id="img-edit-input" name="image" accept="image/gif, image/jpeg, image/png" type="file"/>
                        <input name="ID" type="hidden" value="<%=item.getID()%>">
                    </div>
                </form>
            </div>

            <img id="item-page-main-pic" src="ImageLoader?FileName=<%=item.getImage()%>">
            <div style="color: #990099">
                <span style="margin-right: 5px">rating: <%=item.getRating()%></span>
                <span>voters: <%=item.getVoters()%></span>
            </div>
        </div>
        <div class="center-side-item">
            <form class="edit-forms">
                <label> Company:</label>
                <input type="text" name="company-name" class="user-fields" value="<%=seller.getName()%>" readonly
                       title="Mobile">
            </form>
            <form class="edit-forms" id="edit-price" style="">
                <label> Price:</label>
                <input name="ID" type="hidden" value="<%=item.getID()%>">
                <input type="number" step="0.01" style="width: 80px" name="price" value="<%=item.getPrice()%>"
                       class="user-fields"
                       readonly
                       title="Mobile">
                <img src="edit.png" class="edit-icon ">
            </form>
            <form class="edit-forms" id="edit-desc" style="">
                <label> Description:</label>
                <input name="ID" type="hidden" value="<%=item.getID()%>">
                <textarea type="number" style="resize: none;font-size: 15px" rows="8" cols="60" name="description"
                          class="user-fields"
                          readonly
                          title="Description"><%=item.getDescription()%></textarea>
                <img src="edit.png" style="float:right;" class="edit-icon ">
            </form>
            <form class="edit-forms" id="Tags" style="">
                <label> Tag: </label>
                <input name="ID" type="hidden" value="<%=item.getID()%>">
                <textarea name="tag"  style="display: none; resize: none;font-size: 15px" rows="1" cols="60" ></textarea>
                <img src="edit.png" style="float:right;" class="edit-icon ">
            </form>
        </div>

    </div>
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
                    <%      Statistic stat = dbc.getStatistic(item.getID());
                           if(stat != null){
                            int avg =stat.getOverall()/stat.getDifDays();
                            int avg2 = stat.getOverall()%stat.getDifDays();
                            int price =(int)(item.getPrice()/1);
                            int price2 =(int)(item.getPrice()%1);
                    %>
                    <div class="row-stat">
                        <div class="cell">
                            <%=item.getName()%>
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
                        <%}%>
                    </div>
                </div>
            </div>
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
                        int id = Integer.valueOf(request.getParameter("ID"));
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
                                    "                            <span style=\"font-size: 15px; margin-top: 20px;text-align: center\">" + writer.getName() + "</span>\n" +
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


<div id="footer"></div>
<!-- Footer -->
<div id="myModal" class="modal">

    <!-- Registration Modal content -->
    <div class="modal-content">
        <div class="modal-header">
            <span class="close">×</span>
            <h2>Registration</h2>
        </div>
        <div class="modal-body">
            <div class="user-type">
                <label style="margin-right: 5%" class=""><input id="buyer-bt" type="radio"
                                                                name="user-type"
                                                                checked="checked">buyer</label>
                <label class=""><input id="seller-bt" type="radio" name="user-type">Seller</label>
            </div>
            <div class="row buyer">
                <form id="buyer-registration" action="" method="" enctype="multipart/form-data">
                    <input type="hidden" value="buyer" name="type">
                    <div id="buyer-reg-message" style="display: none; text-align: center"><label style="color:red">Username
                        is already used</label></div>
                    <div>
                        <label class="reg-label">UserName:</label>
                        <input id="username-reg-b" type="text" class=" search-form-control reg-form"
                               name="username"
                               value=""
                               placeholder="UserName">
                    </div>
                    <div>
                        <label class=" reg-label">E-Mail:</label>
                        <input id="buyer-email-eg" type="email" class=" search-form-control reg-form"
                               name="email"
                               value=""
                               placeholder="E-mail">
                        <label style="display: none; color:red" id="email-reg-msg">email is already used</label>
                    </div>
                    <div>
                        <label class="reg-label">Password:</label>
                        <input id="reg-buy-pass" type="password" class=" search-form-control reg-form "
                               name="password"
                               value=""
                               placeholder="Password">
                        <span id="resultb"></span>

                    </div>
                    <div>
                        <label class="reg-label">confirm Password:</label>
                        <input id="reg-buy-pass-con" type="password" class=" search-form-control reg-form "
                               name="password"
                               value=""
                               placeholder="Password">
                    </div>
                    <div>
                        <label class=" reg-label">Name:</label>
                        <input type="text" class=" search-form-control reg-form"
                               name="name"
                               value=""
                               placeholder="Name">
                    </div>
                    <div>
                        <label class=" reg-label">Surname</label>
                        <input type="text" class="search-form-control reg-form"
                               name="surname"
                               value=""
                               placeholder="Surname">
                    </div>
                    <div>
                        <label class=" reg-label">Mobile Number:</label>
                        <input type="number" class="search-form-control reg-form"
                               name="mobile"
                               value=""
                               placeholder="Mobile Number">
                    </div>
                    <div>
                        <label class=" reg-label">Profile Picture</label>
                        <input type="file" accept="image/gif, image/jpeg, image/png" name="file">
                    </div>
                    <div>
                        <button type="submit" class="button registration-submit-buy">Confirm</button>
                    </div>
                </form>
            </div>

            <div class="row seller" style="visibility: hidden; display:none;">
                <form id="seller-registration" action="" method="">
                    <input type="hidden" value="seller" name="type">
                    <div id="seller-reg-message" style="display: none; text-align: center"><label style="color:red">Username
                        is already used</label></div>
                    <div>
                        <label class="reg-label">UserName:</label>
                        <input id="username-reg-s" type="text" class=" search-form-control reg-form"
                               name="username"
                               value=""
                               placeholder="UserName">
                    </div>
                    <div>
                        <label class=" reg-label">E-Mail:</label>
                        <input id="seller-email-msg" type="email" class=" search-form-control reg-form"
                               name="email"
                               value=""
                               placeholder="E-mail">
                        <label style="display: none; color:red" id="email-reg-msg-sel">email
                            is already used</label>


                    </div>
                    <div>
                        <label class="reg-label">Password:</label>
                        <input id="reg-sel-pass" type="password" class=" search-form-control reg-form "
                               name="password"
                               value=""
                               placeholder="Password">
                        <span id="results"></span>
                    </div>
                    <div>
                        <label class="reg-label">confirm Password:</label>
                        <input type="password" id="reg-sel-pass-con" class=" search-form-control reg-form "
                               name="password"
                               value=""
                               placeholder="Password">
                    </div>
                    <div>
                        <label class=" reg-label">Company Name:</label>
                        <input type="text" class=" search-form-control reg-form"
                               name="company"
                               value=""
                               placeholder="Name">
                    </div>

                    <div>
                        <label class=" reg-label">Mobile Number:</label>
                        <input type="number" class="search-form-control reg-form"
                               name="mobile"
                               value=""
                               placeholder="Mobile Number">
                    </div>
                    <div>
                        <label class=" reg-label">Profile Picture</label>
                        <input type="file" accept="image/gif, image/jpeg, image/png" name="file">
                    </div>
                    <div>
                        <button type="submit" class="button registration-submit-buy">Confirm</button>
                    </div>
                </form>
            </div>
        </div>

    </div>

</div>

</body>
</html>
