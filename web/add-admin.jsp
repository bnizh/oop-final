<%@ page import="Objects.Admin" %>
<%@ page import="static Managers.SiteConstants.ADMIN_LOGGED_IN" %>
<%@ page import="static Managers.SiteConstants.ADMIN" %>
<%@ page import="static Managers.SiteConstants.SUPER_ADMIN_TYPE" %>
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
    <h1><a href="#"><span>Admin panel</span></a></h1>
    <%
        Boolean b = (Boolean) request.getSession().getAttribute(ADMIN_LOGGED_IN);
        if (!b) {
            out.println("<script type=\"text/javascript\">  window.location.href = \"http://localhost:8080/activationNeeded.jsp\"; </script>");
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
        <li><a href="superadmin.jsp" class="active">Admins</a></li>
        <li><a href="add-admin.jsp">Add New Admin</a></li>
        <%
            }
        %>
        <li><a href="#">Main</a></li>
        <li><a href="#">Categories</a></li>
        <li><a href="#">Products</a></li>
        <li class="logout"><a href="/admin-login?">LOGOUT</a></li>
    </ul>
    <!-- // #end mainNav -->

    <div id="container">

        <!-- h2 stays for breadcrumbs -->
        <h2 style="text-align: center">Add New Admin</h2>
        <main>
            <form id="new-admin-form">
                <span id="succ-msg"
                      style="margin-top: 20px;color: #5cb85c;margin-left: 35%;font-size: 20px;display: none">Admin Has Successfully Added</span>
                <span id="error-msg"
                      style="margin-top: 20px;color: #FF0000;margin-left: 30%;font-size: 20px;display: none">Error Occurred Admin Has not Added </span>
                <div class="add-admin"><span>UserName</span>
                    <input type="text" name="username">
                </div>
                <div class="add-admin"><span>email</span>
                    <input type="email" name="email">
                </div>
                <div class="add-admin"><span>password</span>
                    <input type="password" name="password">
                </div>
                <div class="add-admin"><span>Name</span>
                    <input type="text" name="name">
                </div>
                <div class="add-admin"><span>Mobile Number</span>
                    <input type="text" name="mobile">
                </div>
                <div class="add-admin-file"><span>Image</span>
                    <input name="image" type="file" accept="image/gif, image/jpeg, image/png">
                </div>
                <button type="submit" class="add-admin-button">Submit</button>
            </form>
        </main>

        <div class="clear"></div>
    </div>
    <!-- // #container -->

    <p id="footer">Feel free to use and customize it. <a href="http://www.perspectived.com">Credit is appreciated.</a>
    </p>
</div>
<!-- // #wrapper -->
</body>
</html>


