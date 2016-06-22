<%@ page import="DataBase.DBConnection" %>
<%@ page import="Objects.Category" %>
<%@ page import="Objects.Item" %>
<%@ page import="Objects.Seller" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Cart</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <link href="css/main.css" rel="stylesheet">
    <script src="Javascript/AjaxSending.js"></script>
    <script src="Javascript/passwordscheck.js"></script>
    <script src="Javascript/loginAjax.js"></script>
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
<!-- Main -->
<div>
    <div id="main">
        <div class="left-menu">
            <% DBConnection dbc = (DBConnection) getServletConfig().getServletContext().getAttribute("dbc");
                List<Category> categoryList = (List<Category>) dbc.getAllCategories();
                for (Category category : categoryList) {
                    out.println(" <a class=\"left-menu-item\" href=\"#\">" + category.getName() + "</a>");
                }
            %>

        </div>
        <div id="product-list">
            <%
                List<Item> list = dbc.getTopItems(20);
                for (Item item : list) {
                    Seller owner = dbc.getSellerByID(item.getOwnerID());
                    String ownerName = owner.getName();
                    String itemName = item.getName();
                    String itemImage = item.getImage();
                    if (ownerName == null) ownerName = "";
                    if (itemName == null) itemName = "";
                    if (itemImage == null) itemImage = "";
                    out.println("<div class=\"product\">\n" +
                            "                <div>" + ownerName + "</div>\n" +
                            "                <img src=\"ImageLoader?FileName=" + itemImage + "\">\n" +
                            "               " + "<div>" + itemName + "</div>\n" +
                            "                <div>Price:" + item.getPrice() + "</div>" +
                            "<form action=\"item\" method=\"get\">" +
                            "<input name=\"ID\" type=\"hidden\" value=\"" + item.getID() + "\">\n" +
                            "<button  type=\"submit\" class=\"button\"> დეტალურად</button>\n" +
                            "</form>" + "</div>");
                }


            %>
        </div>

    </div>
</div>
<!-- Main -->


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
                        <input id="buyer-email" type="email" class=" search-form-control reg-form"
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
                        <input type="text" id="buyer-name" class=" search-form-control reg-form"
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
                        <button id="reg-sub-b" class=" button registration-submit-buy">Confirm</button>
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
                        <input id="seller-email" type="email" class=" search-form-control reg-form"
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
                        <input type="text" id="seller-name" class=" search-form-control reg-form"
                               name="name"
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
                        <button id="reg-sub-s" class="button registration-submit-buy">Confirm</button>
                    </div>
                </form>
            </div>
        </div>

    </div>

</div>

</body>
</html>
