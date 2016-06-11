<%@ page import="Objects.User" %>
<%
    User user = (User) request.getSession().getAttribute("user");
%>
<div class="user-panel">
    <img class="profile-user-panel" style="margin-right: 20px" src="ImageLoader?FileName=<%=user.getImage()%>">
    <span class="profile-info"><%=user.getUserName()%></span>
    <div id="profile-menu">
        <a href="#" id="profile-down-menu" class="profile-info">Profile <img id="arrow" src="arrow.png">
        </a>
        <div id="profile-menu-content">
            <div style="width: 100%;text-align: center">
                <span href="">You Are: <%= request.getSession().getAttribute("type")%> </span></div>
            <img style="width:50px; height: 50px; margin-left: 75px" class="profile-user-panel"
                 src="ImageLoader?FileName=<%=user.getImage()%>">
            <div style=""><span href=""><%=user.getName()%></span></div>
            <div style="width: 100%;text-align: center"><a href="">Go to private page</a></div>
        </div>
    </div>

</div>