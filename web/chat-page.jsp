<%--suppress JSUnresolvedFunction --%>
<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: Boris
  Date: 18.06.2016
  Time: 1:23
  To change this template use File | Settings |     File Templates.
--%>
<%@ page import="Objects.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://code.jquery.com/jquery-3.0.0-beta1.min.js"></script>
<link rel="stylesheet" href="css/ui-lightness/jquery-ui-1.8.2.custom.css"/>
<script type="text/javascript" src="Javascript/jquery-ui-1.8.2.custom.min.js"></script>
<link type="text/css" href="css/jquery.ui.chatbox.css" rel="stylesheet"/>
<script type="text/javascript" src="Javascript/jquery.ui.chatbox.js"></script>
<script type="text/javascript" src="Javascript/chatboxManager.js"></script>
<input id="userID" type="hidden" value="<%=((User) (request.getSession().getAttribute("user"))).getID()%>">
<input id="userID1" type="hidden" value="<%=((User) (request.getSession().getAttribute("user"))).getID()%>">
<textarea id="username"
          style="display: none"><%=((User) (request.getSession().getAttribute("user"))).getUserName()%></textarea>
<TEXTAREA id="input" style="display: none"></TEXTAREA>
<input type="text" id="txtMessage" style="display: none;margin-left: 45%;top: 5%;" class="form-control"
       placeholder="Type your message here."/>
<script type="text/javascript" src="Javascript/chat.js"></script>
<script type="text/javascript" src="Javascript/Notification.js"></script>
