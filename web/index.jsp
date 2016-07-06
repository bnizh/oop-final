<%@ page import="DataBase.DBConnection" %>
<%@ page import="Objects.Category" %>
<%@ page import="Objects.Item" %>
<%@ page import="Objects.Seller" %>
<%@ page import="Objects.Admin" %>
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
        Boolean b=false;
        Boolean adm = false;
        if(session.getAttribute(LOGGED_IN)!=null)
           b = (Boolean) session.getAttribute(LOGGED_IN);

        if (session.getAttribute(ADMIN_LOGGED_IN)!=null)
            adm=(Boolean) session.getAttribute(ADMIN_LOGGED_IN);
        if (!b&&!adm) {
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
        <div>
            <button id="searchConfirm" onclick="selectItem()" class="login-button" style="margin-left: 1010px; height: 48px; width: 100px; margin-bottom: 0px; ">SEARCH</button>
            <input id="searchArea"  style="width: 50%;  margin: auto; margin-top: -48px;
                 " class="search-form-control">
            <select id="selector" class="search-form-control" style="margin-top: -48px; height: 48px; margin-left: 210px;">
                <option value="ITEM" selected="selected">ITEM</option>
                <option value="USER" >USER</option>
            </select>
            <script type="text/javascript">
                $('#searchArea').keyup(function(event){
                    if(event.keyCode === 13){
                        selectItem();
                    }
                });
            </script>
        </div>
        <div class="left-menu">
            <% DBConnection dbc = DBFactory.getDBConnection();
                List<Category> categoryList = (List<Category>) dbc.getAllCategories();
                for (Category category : categoryList) {
                    out.println(" <a class=\"left-menu-item\" href=\"cat?cat=" + category.getID() + "\">" + category.getName() + "</a>");
                }
            %>

        </div>
        <div id="product-list">
            <%
                int currentPage;
                if (request.getParameter("page") != null) {
                    currentPage = Integer.valueOf(request.getParameter("page"));
                } else {
                    currentPage = 0;
                }
                List<Item> list = (List<Item>) request.getSession().getAttribute("itemList");
                if (list == null) {
                    list = dbc.getTopItems(NUMBER_OF_ITEMS_ON_PAGE, currentPage * NUMBER_OF_ITEMS_ON_PAGE);
                } else {
                    request.getSession().removeAttribute("itemList");
                }
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
                            "<button  type=\"submit\" class=\"button\"> Details</button>\n" +
                            "</form>" + "</div>");
                }
                if (request.getParameter("cat") == null)
                    out.println("<input type=\"hidden\" id=\"current-page\" name=\"page\" value=\"1\">");
                else {
                    out.println("<input type=\"hidden\" id=\"current-page\" name=\"page\" value=\"1\">");
                    out.println("<input type=\"hidden\" id=\"current-category\" name=\"cat\" value=\"" + request.getParameter("cat") + "\">");
                }

            %>
        </div>

    </div>
</div>
<!-- Main -->
<p id="loading" style="text-align:center; <%if(list.size()<NUMBER_OF_ITEMS_ON_PAGE){
out.println("display:none;");
}
 %>">
    <img src="loading.gif" alt="Loading…"/>
</p>

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
<div  id="newItem"></div>
</div>

</body>
</html>
