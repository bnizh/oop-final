<%@ page import="static Managers.SiteConstants.ADMIN_LOGGED_IN" %>
<%@ page import="Objects.Admin" %>
<%@ page import="static Managers.SiteConstants.ADMIN" %>
<%@ page import="static Managers.SiteConstants.SUPER_ADMIN_TYPE" %>
<%@ page import="java.util.List" %>
<%@ page import="Objects.Message" %>
<%@ page import="DataBase.DBConnection" %>
<%@ page import="DataBase.DBFactory" %>
<%@ page import="static Managers.SiteConstants.*" %>
<%@ page import="Objects.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Admin Panel</title>

    <!-- CSS -->
    <link href="Adminstyle/css/transdmin.css" rel="stylesheet" type="text/css" media="screen"/>
    <link rel="stylesheet" type="text/css" media="screen" href="Adminstyle/css/ie6.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="Adminstyle/css/ie7.css"/>
    <script src="https://code.jquery.com/jquery-3.0.0-beta1.min.js"></script>
    <script type="text/javascript" src="Adminstyle/js/jNice.js"></script>
</head>

<body>
<div id="wrapper">
    <!-- h1 tag stays for the logo, you can use the a tag for linking the index page -->
    <h1><a href="#"><span>Admin Panel</span></a></h1>
        <%
        Boolean b = (Boolean) request.getSession().getAttribute(ADMIN_LOGGED_IN);
        if (!b) {
            out.println("<script type=\"text/javascript\">  window.location.href = \"http://localhost:8080/error.html\"; </script>");
        }
        Admin admin = (Admin) request.getSession().getAttribute(ADMIN);
        boolean isSuper = false;
        if (admin.getTypeOfAdmin() == SUPER_ADMIN_TYPE) isSuper = true;
    %>
    <!-- You can name the links with lowercase, they will be transformed to uppercase by CSS, we prefered to name them with uppercase to have the same effect with disabled stylesheet -->
    <ul id="mainNav" style="margin-left: 100px">
        <li><a href="admin.jsp">Users</a></li> <!-- Use the "active" class for the active menu item  -->
        <%
            if (isSuper) {
        %>
        <li><a href="superadmin.jsp">Admins</a></li>
        <li><a href="add-admin.jsp">Add New Admin</a></li>
        <li><a href="admin-category.jsp">Categories</a></li>

        <%
            }
        %>
        <li><a href="admin-product.jsp">Products</a></li>
        <li><a href="admin-message-inbox.jsp" >Inbox</a></li>
        <li><a href="admin-new-message.jsp" class="active">New Message</a></li>
        <li><a href="index.jsp">Main</a></li>
        <li class="logout"><a href="${pageContext.request.contextPath}/admin-login?">LOGOUT</a></li>
    </ul>
    <!-- // #end mainNav -->

    <div>
        <div class="user-container">
            <h2 style="text-align: center">Message</h2>
            <form action="message" method="post">
                <div style="width: 100%">
                    <label style="margin-left: 40%;margin-right: 5%;font-size: 20px" class=""><input
                            id="user"
                            class="type-of-message"
                            type="radio"
                            name="type"
                            value="one"
                            checked="checked">To one User</label>
                    <label style="font-size: 20px" class=""><input type="radio"
                                                                   id="admin"
                                                                   value="all"
                                                                   class="type-of-message"
                                                                   name="type">To all User</label>
                </div>
                <div id="addressee" style="margin-left:10%;margin-bottom:30px;width: 80%">
                    <span style="font-size: 20px;margin-left: 70px;margin-right: 30px">Username Of addressee</span><input

                        name="receiver" placeholder="Username" type="text" style="border:2px solid #6b3a99;width: 60%">
                </div>
        <textarea placeholder="Message text" name="text"
                  style="margin-left:5%; resize: none;width:90%;background-color: white; height: 180px"></textarea>
                <button type="submit" style="width: 10%; float: right;margin-right:20%;border: 2px solid #6b3a99;
         background-color: white; border-radius: 10px; margin-top: 20px ">
                    Send
                </button>
            </form>

        </div>
    </div>
    <script>  $('.type-of-message').change(function () {

        if ($('#admin').is(':checked')) {
            $('#addressee').css("visibility", "hidden").css("display", "none");
        }
        else {
            $('#addressee').css("visibility", "visible  ").css("display", "block");

        }
    });</script>
</body>

</html>


