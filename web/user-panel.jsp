<%@ page import="Objects.User" %>
<%
    User user = (User) request.getSession().getAttribute("user");
%>
<%@include file="chat-page.jsp" %>
<div class="user-panel">
    <img class="profile-user-panel" style="margin-right: 20px" src="ImageLoader?FileName=<%=user.getImage()%>">
    <span class="profile-info"><%=user.getUserName()%></span>
    <div id="profile-menu">
        <a href="#" id="profile-down-menu" class="profile-info">Profile <img id="arrow" src="arrow.png">
        </a>
        <div id="profile-menu-content">
            <div style="width: 100%;text-align: center">
                <span href="">You Are: <%= request.getSession().getAttribute("type")%> </span></div>
            <img style="width:80px; height: 60px; margin-left: 60px" class="profile-user-panel"
                 src="ImageLoader?FileName=<%=user.getImage()%>">
            <div style=""><span href=""><%=user.getName()%></span></div>
            <div style="width: 100%;text-align: center"><a href="\user-page.jsp">Go to private page</a></div>
            <%
                String type=(String) request.getSession().getAttribute("type");
                if (type.equals("seller")) {
                    out.println(" <div style=\"width: 100%;text-align: center\"><a href=\"\\add-product.jsp\">Add New Product</a></div>");
                }
            %>
            <div style="width: 100%;text-align: center"><a href="/logout?">Log Out</a></div>
        </div>
    </div>

</div>