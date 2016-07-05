<%@ page import="static Managers.SiteConstants.ADMIN_LOGGED_IN" %>
<%@ page import="Objects.Admin" %>
<%@ page import="static Managers.SiteConstants.ADMIN" %>
<%@ page import="static Managers.SiteConstants.SUPER_ADMIN_TYPE" %>
<%@ page import="java.util.List" %>
<%@ page import="Objects.Item" %>
<%@ page import="DataBase.DBConnection" %>
<%@ page import="DataBase.DBFactory" %>
<%@ page import="Objects.Seller" %>
<%@ page import="Objects.Category" %>
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
        <li><a href="admin.jsp" >Users</a></li> <!-- Use the "active" class for the active menu item  -->
        <%
            if (isSuper) {
        %>
        <li><a href="superadmin.jsp">Admins</a></li>
        <li><a href="add-admin.jsp">Add New Admin</a></li>
        <li><a href="admin-category.jsp">Categories</a></li>

        <%
            }
        %>
        <li><a href="#" class="active">Products</a></li>
        <li><a href="admin-message-inbox.jsp">Inbox</a></li>
        <li><a href="admin-new-message.jsp" >New Message</a></li>
        <li><a href="index.jsp">Main</a></li>
        <li class="logout"><a href="/admin-login?">LOGOUT</a></li>
    </ul>
    <!-- // #end mainNav -->

    <div id="containerHolder">
        <div id="container">
            <main>
                <table class="admin-table" style="margin-top: 20px">
                    <thead>
                    <tr>
                        <th class="row-1 row-ID">ID</th>
                        <th class="row-2 row-image">image</th>
                        <th class="row-3 row-username">name</th>
                        <th class="row-3 row-name">owner</th>
                        <th class="row-5 row-type">category</th>
                        <th class="row-6 row-banned">price</th>
                        <th class="row-6 row-banned">rating</th>
                        <th class="row-9 row-x">X</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        DBConnection dbc = DBFactory.getDBConnection();
                        List<Item> itemList = dbc.getTopItems(100000, 0);
                        for (Item item : itemList) {
                            Seller seller = dbc.getSellerByID(item.getOwnerID());
                            Category cat = dbc.getCategory(item.getID());
                            out.println(" <tr>\n" +
                                    "<td class=\"item-id\">" + item.getID() + "</td>\n" +
                                    "<td><img src=\"ImageLoader?FileName=" + item.getImage() + "\" style=\";height: 40px\"\n" +
                                    "alt=\"\"></td>\n" +
                                    "<td valign=\"center\">" + item.getName() + "</td>\n" +
                                    "<td valign=\"center\">" + seller.getUserName() + "</td>\n");
                            if(cat!=null)
                            out.print(
                                    "<td valign=\"center\">" + cat.getName() + "</td>\n");
                            else out.println( "<td valign=\"center\">No Category</td>\n");
                            out.println(
                                    "<td valign=\"center\">" + item.getPrice() + "</td>\n" +
                                            "<td valign=\"center\">" + item.getRating() + "</td>\n" +
                                            "<td> <a class=\"delete-item\" style=\"color: red;text-decoration:none;font-size: 20px\">X</a></td>\n" +
                                            "                    </tr>");
                        }
                    %>

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


