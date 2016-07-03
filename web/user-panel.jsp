<%@ page import="static Managers.SiteConstants.LOGGED_IN" %>
<%@ page import="static Managers.SiteConstants.USER" %>
<%@ page import="static Managers.SiteConstants.ADMIN" %>
<%@ page import="Objects.Admin" %>
<%@ page import="static Managers.SiteConstants.*" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>

<%
    Boolean logged = (Boolean) request.getSession().getAttribute(LOGGED_IN);
    Boolean adminLogged = (Boolean) request.getSession().getAttribute(ADMIN_LOGGED_IN);
    Admin admin = (Admin) request.getSession().getAttribute(ADMIN);
    User user = (User) request.getSession().getAttribute(USER);

%>



<%--<%
    if(!user.isConfirmed()) {
    %><script type="text/javascript">  window.location.href = "http://localhost:8080/activationNeeded.jsp"; </script><%
    }
    %>--%>
<% if (logged) {%>
<%@include file="chat-page.jsp" %>
<div class="user-panel">

    <%if(user.getImage().contains("https")||user.getImage().contains("http")){%>
     <img style="width:80px; height: 60px; margin-left: 60px" class="profile-user-panel"
           src=<%=user.getImage()%>>
    <%}else{%>
    <img style="width:80px; height: 60px; margin-left: 60px" class="profile-user-panel"
         src="ImageLoader?FileName=<%=user.getImage()%>">
    <%}%>
    <span class="profile-info"><%=user.getName()%></span>
    <div id="profile-menu">
        <a href="#" id="profile-down-menu" class="profile-info">Profile <img id="arrow" src="arrow.png">
        </a>
        <div id="profile-menu-content">
            <div style="width: 100%;text-align: center">
                <span href="">You Are: <%= request.getSession().getAttribute("type")%> </span></div>
            <%if(user.getImage().contains("https")||user.getImage().contains("http")){%>
            <img style="width:80px; height: 60px; margin-left: 60px" class="profile-user-panel"
                 src=<%=user.getImage()%>>
            <%}else{%>
            <img style="width:80px; height: 60px; margin-left: 60px" class="profile-user-panel"
                 src="ImageLoader?FileName=<%=user.getImage()%>">
            <%}%>
            <div style=""><span href=""><%=user.getName()%></span></div>
            <div style="width: 100%;text-align: center"><a href="\user-page.jsp">Go to private page</a></div>
            <div style="width: 100%;text-align: center"><a href="\user-page.jsp">Message Inbox</a></div>
            <%
                String type = (String) request.getSession().getAttribute("type");
                if (type.equals("seller")) {
                    out.println(" <div style=\"width: 100%;text-align: center\"><a href=\"\\add-product.jsp\">Add New Product</a></div>");
                }
            %>
            <div style="width: 100%;text-align: center"><a onclick="fblogout();">Log Out</a>
            </div>
        </div>
    </div>

</div>
<%
} else if (adminLogged) {
%>
<div class="user-panel">
    <img class="profile-user-panel" style="margin-right: 20px" src="ImageLoader?FileName=<%=admin.getImageURL()%>">
    <span class="profile-info"><%=admin.getUserName()%></span>
    <div id="profile-menu">
        <a href="#" id="profile-down-menu" class="profile-info">Profile <img id="arrow" src="arrow.png">
        </a>
        <div id="profile-menu-content">
            <div style="width: 100%;text-align: center">
                <span href="">You Are: <%
                    if (admin.getTypeOfAdmin() == SUPER_ADMIN_TYPE) out.print("Super Admin");
                    else {
                        out.println("Admin");
                    }
                    ;
                %> </span></div>
            <img style="width:80px; height: 60px; margin-left: 60px" class="profile-user-panel"
                 src="ImageLoader?FileName=<%=admin.getImageURL()%>">
            <div style=""><span href=""><%=admin.getName()%></span></div>
            <div style="width: 100%;text-align: center"><a href="/admin.jsp">Go to AdminPanel</a></div>
            <div style="width: 100%;text-align: center"><a href="/admin-login?">Log Out</a>
            </div>
        </div>
    </div>

</div>
<%}%>