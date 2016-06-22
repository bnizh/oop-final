<%@ page import="java.util.List" %>
<%@ page import="Objects.Category" %>
<%@ page import="DataBase.DBConnection" %>
<%@ page import="DataBase.DBFactory" %>
<html>
<head>
    <title>Food-Online</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <link href="css/main.css" rel="stylesheet">
    <link href="css/usercss.css" rel="stylesheet">
    <script src="Javascript/AjaxSending.js"></script>
    <script src="Javascript/passwordscheck.js"></script>
    <script src="Javascript/loginAjax.js"></script>
    <script src="Javascript/user-edit.js"></script>
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
<div>
    <div class="user-container">
        <div class="type-header">
            <span>Add New Product</span>
        </div>
        <div class="row buyer">
            <form id="item-add-form" enctype="multipart/form-data">
                <label id="add-item-err-msg"
                       style="display:none;color:#5cb85c; font-size: 20px; text-align: center">Product Has
                    Added Successfully</label>
                <div style="margin-top: 20px;">
                    <label class="reg-label">Product Name:</label>
                    <input type="text" class=" search-form-control item-add"
                           name="name"
                           value=""
                           placeholder="Name">
                </div>
                <div>
                    <label class=" reg-label">Price:</label>
                    <input type="number" class=" search-form-control item-add"
                           name="price"
                           value="0.0"
                           step="0.01"
                           placeholder="Price">
                </div>
                <div>
                    <label class=" reg-label">Choose Category:</label>
                    <select name="category" class="search-form-control item-add" title="Categories">
                        <%
                            DBConnection dbc= DBFactory.getDBConnection();
                            List<Category> list = (List<Category>) dbc.getAllCategories();
                            for (Category cat : list) {
                                out.println(" <option value=\"" + cat.getID() + "\">" + cat.getName());
                                out.println("</option>");
                            }

                        %>
                    </select>
                </div>

                <div>
                    <label class=" reg-label">Description</label>
                    <textarea type="text" class=""
                              name="description"
                              rows="7"
                              cols="70"
                              placeholder="Description"></textarea>
                </div>
                <div>
                    <label class=" reg-label">Product Image</label>
                    <input type="file" accept="image/gif, image/jpeg, image/png" name="file">
                </div>
                <div>
                    <button type="submit" class="button registration-submit-buy">Confirm</button>
                </div>
            </form>
        </div>
    </div>
</div>


<div id="footer" ></div>
<!-- Footer -->


<div id="voov-translate-extension-is-installed"></div>
</body>
</html>