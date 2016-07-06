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
        <li><a href="admin-message-inbox.jsp" class="active">Inbox</a></li>
        <li><a href="admin-new-message.jsp" class="active">New Message</a></li>
        <li><a href="index.jsp">Main</a></li>
        <li class="logout"><a href="${pageContext.request.contextPath}/admin-login?">LOGOUT</a></li>
    </ul>
    <!-- // #end mainNav -->

    <div>
        <div class="user-container">
            <%
                DBConnection dbc = DBFactory.getDBConnection();
                Message ms = (Message) request.getSession().getAttribute("message");
                if (ms == null) {
                    String redirectURL = "/admin-message-inbox.jsp";
                    response.sendRedirect(redirectURL);
                    return;
                }
                User us = dbc.getBuyerByID(ms.getWriterID());
                if (us == null) us = dbc.getSellerByID(ms.getWriterID());


            %>
            <h2 style="text-align: center">Message</h2>
            <div style="overflow: hidden;width:100%;border-top: 2px solid #990099;border-bottom: 2px solid #990099;margin-bottom: 40px;margin-top: 20px;padding-bottom: 20px;text-align: center">
                <span style="width:100%; margin-bottom:15px;font-size: 20px;">Author:</span>
                <table class="admin-table" style="margin-top: 20px">
                    <thead>
                    <tr>
                        <th style="width: 15%" class="row-1 row-ID">ID</th>
                        <th style="width: 15%" class="row-1 row-image">Image</th>
                        <th style="width: 35%" class="row-3 row-username">Username</th>
                        <th style="width: 35%" class="row-5 row-type">Name</th>
                    </tr>
                    </thead>
                    <tbody>
                    <td><%=us.getID()%>
                    </td>
                    <%if (us.getImage().contains("https") || us.getImage().contains("http")) {%>
                    <td><img src="<%=us.getImage()%>" style="height: 40px"></td>
                    <%} else {%>
                    <td><img src="ImageLoader?FileName=<%=us.getImage()%>" style="height: 40px"></td>
                    <%}%>
                    <td><%=us.getUserName()%>
                    </td>
                    <td><%=us.getName()%>
                    </td>
                    </tbody>
                </table>
            </div>
        <textarea readonly
                  style="margin-left:5%; resize: none;width:90%;background-color: white; height: 180px"><%=ms.getMessageContent()%></textarea>

        </div>
    </div>
        <%
    request.getSession().removeAttribute("message");
%>
</body>

</html>


