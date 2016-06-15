<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <link href="main.css" rel="stylesheet">
    <link href="usercss.css" rel="stylesheet">
    <script src="script.js"></script>
    <script src="AjaxSending.js"></script>
    <script src="passwordscheck.js"></script>
    <script src="loginAjax.js"></script>
    <script src="user-edit.js"></script>
</head>
<body>
<!-- Header -->
<div class="header">
    <div class="header1">
        <a href="#" id="logo">Food-Online</a>
    </div>
    <%
        if (session.getAttribute("loggedIn") == null || !(boolean) session.getAttribute("loggedIn")) {

    %>
    <%@include file="visitor.jsp" %>
    <script src="script.js"></script>
    <% } else {
    %>
    <%@include file="user-panel.jsp" %>

    <% }%>
</div>
<div>
    <div class="user-container">
        <div class="type-header">
            <span>Seller Page</span>
        </div>
        <div class="left-side-user">
            <div style="float:right;">
                <form id="img-edit" method="" action="">
                    <div class="image-upload">
                        <label for="img-edit-input">
                            <img class="edit-icon" src="edit.png"/>
                        </label>
                        <input id="img-edit-input" name="image" type="file"/>
                    </div>
                </form>
            </div>

            <%User us=(User) request.getSession().getAttribute("user"); %>
            <img src="ImageLoader?FileName=<%=us.getImage()%>" style="width: 200px; height: 200px">
            <div style="color: #990099">
                <span>Rating 8/10    </span>
                <span>voters:100</span>
            </div>
        </div>
        <div class="center-side-user">

            <div>
                <span style="font-size: 30px">Tengizo</span>
            </div>
            <div>
                <input type="text" class="user-fields" value="Tengiz Chilingarashvili" readonly>

            </div>
            <div>
                <input type="text" class="user-fields" value="email:chtengo@gmail.com" readonly>
                <img src="edit.png" class="edit-icon">

            </div>
            <div>
                <input type="text" class="user-fields" value="Mobile: 571119644" readonly>
                <img src="edit.png" class="edit-icon">

            </div>
        </div>
        <div class="right-side-user">
            <div>
                <span>Add New Product</span>
            </div>
            <div>
                <span>mobile:571119644</span>
            </div>
        </div>
    </div>
</div>


<div id="footer" style="top:40%"></div>
<!-- Footer -->


</body>
</html>
