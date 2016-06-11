<%@ page contentType="text/html; charset=UTF-8" %>
<div class="user-panel">
    <span class="profile-info"><%= request.getSession().getAttribute("UserName")%></span>
    <a href="#" class="profile-info">Profile</a>

</div>