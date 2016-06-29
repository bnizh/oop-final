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

    <!-- You can name the links with lowercase, they will be transformed to uppercase by CSS, we prefered to name them with uppercase to have the same effect with disabled stylesheet -->
    <ul id="mainNav" style="margin-left: 100px">
        <li><a href="admin.jsp" class="active">Users</a></li> <!-- Use the "active" class for the active menu item  -->
        <li><a href="superadmin.jsp">Admins</a></li>
        <li><a href="#">Inbox</a></li>
        <li><a href="#">Main</a></li>
        <li><a href="#">Categories</a></li>
        <li><a href="#">Products</a></li>
        <li class="logout"><a href="#">LOGOUT</a></li>
    </ul>
    <!-- // #end mainNav -->

    <div id="containerHolder">
        <div id="container">
            <%@include file="users-in-adminpanel.jsp" %>

            <div class="clear"></div>
        </div>
        <!-- // #container -->
    </div>
    <!-- // #containerHolder -->
</div>
<!-- // #wrapper -->
</body>
</html>

