<%@ page import="DataBase.DBConnection" %>
<%@ page import="DataBase.DBFactory" %>
<%@ page import="Objects.Category" %>
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
    <script src="https://code.jquery.com/jquery-3.0.0-beta1.min.js"></script>

    <script type="text/javascript" src="Adminstyle/js/jNice.js"></script>

</head>

<body>
<div id="wrapper">
    <!-- h1 tag stays for the logo, you can use the a tag for linking the index page -->
    <h1><a href="#"><span>Admin panel</span></a></h1>

    <%
        Boolean b = (Boolean) request.getSession().getAttribute(ADMIN_LOGGED_IN);
        Admin admin = (Admin) request.getSession().getAttribute(ADMIN);
        boolean isSuper = false;
        if (admin.getTypeOfAdmin() == SUPER_ADMIN_TYPE) isSuper = true;
        if (!b||!isSuper) {
            out.println("<script type=\"text/javascript\">  window.location.href = \"http://localhost:8080/error.html\"; </script>");
        }
    %>
    <!-- You can name the links with lowercase, they will be transformed to uppercase by CSS, we prefered to name them with uppercase to have the same effect with disabled stylesheet -->
    <ul id="mainNav" style="margin-left: 100px">
        <li><a href="admin.jsp" class="active">Users</a></li> <!-- Use the "active" class for the active menu item  -->
        <%
            if (isSuper) {
        %>
        <li><a href="superadmin.jsp">Admins</a></li>
        <li><a href="add-admin.jsp">Add New Admin</a></li>
        <li><a href="admin-category.jsp">Categories</a></li>

        <%
            }
        %>
        <li><a href="#">Inbox</a></li>
        <li><a href="#">Main</a></li>
        <li><a href="#">Products</a></li>
        <li class="logout"><a href="/admin-login?">LOGOUT</a></li>
    </ul>
    <!-- // #end mainNav -->

    <div id="container">

        <main id="seller-table">
            <form action="cat" method="post">
                <div class="add-category">
                    <input type="text" name="category" title="">
                    <button type="submit" class="add-category-button">Add Category</button>
                </div>
            </form>
            <table class="admin-table" style="margin-top: 20px;">
                <thead>
                <tr>
                    <th class="row-1 row-ID">Ctegory id</th>
                    <th class="row-2 row-name">category name</th>
                    <th class="row-2 row-name">number of items</th>
                    <th class="row-2 row-x">X</th>
                    <th class="row-2 row-x">Edit</th>

                </tr>
                </thead>
                <tbody>
                <%
                    DBConnection dbc = DBFactory.getDBConnection();
                    List<Category> adminList = dbc.getAllCategories();
                    for (Category category : adminList) {
                        int size = dbc.getItemsByCategoryId(category.getID(), 10000, 0).size();
                        out.println("<tr>\n" +
                                " <td class=\"category-id\"style=\"font-size: 20px\">" + category.getID() + "</td>\n" +
                                "<td class=\"td-name\"  style=\"font-size: 20px\"><input " +
                                "readonly style=\"color:inherit;font-size: 20px;border:none;outline:\n" +
                                "none;background-color: transparent  \" value=\"" + category.getName() + "\" name=\"name\"></td>\n" +
                                "<td style=\"font-size: 20px\">" + size + "</td>\n" +
                                "<td> <a class=\"delete-category\" style=\"color: red;text-decoration:none;font-size: 20px\">X</a></td>\n" +
                                "<td style=\"font-size: 20px\"><img class=\"category-edit\" style=\"height: 30px\" src=\"edit.png\"></td>\n" +
                                "</tr>");

                    }
                %>
                </tbody>
            </table>
        </main>

        <div class="clear"></div>
    </div>

</div>
<!-- // #wrapper -->
</body>
</html>


