<%@ page import="DataBase.DBConnection" %>
<%@ page import="DataBase.DBFactory" %>
<%@ page import="java.util.List" %>
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
    <script src="https://code.jquery.com/jquery-3.0.0-beta1.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="Adminstyle/js/jNice.js"></script>
</head>

<body>
<div id="wrapper">
    <!-- h1 tag stays for the logo, you can use the a tag for linking the index page -->
    <h1><a href="#"><span>Admin Panel</span></a></h1>
    <%
        Boolean b = (Boolean) request.getSession().getAttribute(ADMIN_LOGGED_IN);
        if (!b) {
            out.println("<script type=\"text/javascript\">  window.location.href = \"http://localhost:8080/activationNeeded.jsp\"; </script>");
        }
        Admin administrator = (Admin) request.getSession().getAttribute(ADMIN);
        boolean isSuper = false;
        if (administrator.getTypeOfAdmin() == SUPER_ADMIN_TYPE) isSuper = true;
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
        <li><a href="#">Inbox</a></li>
        <li><a href="#">Main</a></li>
        <li><a href="#">Categories</a></li>
        <li><a href="#">Products</a></li>
        <li class="logout"><a href="/admin-login?">LOGOUT</a></li>
    </ul>
    <!-- // #end mainNav -->

    <div id="containerHolder">
        <div id="container">
            <h2 style="text-align: center"> Admins <a href="add-admin.jsp"
                                                      style="color:#ff5e01;text-decoration: none; float: right;margin-right: 50px">Add
                New Admin</a></h2>
            <main>
                <table class="admin-table" style="margin-top: 20px">
                    <thead>
                    <tr>
                        <th class="row-1 row-ID">ID</th>
                        <th class="row-2 row-image">image</th>
                        <th class="row-3 row-username">Username</th>
                        <th class="row-4 row-name">name</th>
                        <th class="row-5 row-email">Email</th>
                        <th class="row-6 row-x">X</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <%
                            DBConnection dbc = DBFactory.getDBConnection();
                            List<Admin> adminList = dbc.getAllAdmin();
                            for (Admin admin : adminList) {

                                out.println("<td>" + admin.getId() + "</td>\n" +
                                        "                        <td><img src=\"ImageLoader?FileName=" + admin.getImageURL() + "\" style=\";height: 40px\" alt=\"\"/></td>\n" +
                                        "                        <td valign=\"center\">" + admin.getUserName() + "</td>\n" +
                                        "                        <td valign=\"center\">" + admin.getName() + "</td>\n" +
                                        "                        <td valign=\"center\">" + admin.getEmail() + "</td>\n");
                                out.println("<td> <a href=\"superadmin?delete=1&ID=" + admin.getId() + "\"" +
                                        " style=\"color: red;text-decoration:none;font-size: 20px\">X</a></td>" +
                                        "        </tr>");
                            }
                        %>

                    </tr>
                    </tbody>
                </table>

            </main>
            <div class="clear"></div>
        </div>
        <!-- // #container -->
    </div>
    <!-- // #containerHolder -->
</div>
<!-- // #wrapper -->
</body>
</html>


