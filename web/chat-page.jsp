<%--suppress JSUnresolvedFunction --%>
<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: Boris
  Date: 18.06.2016
  Time: 1:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="Objects.User" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<link rel="stylesheet" href="css/ui-lightness/jquery-ui-1.8.2.custom.css"/>
<script type="text/javascript" src="Javascript/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="Javascript/jquery-ui-1.8.2.custom.min.js"></script>
<link type="text/css" href="css/jquery.ui.chatbox.css" rel="stylesheet"/>
<script type="text/javascript" src="Javascript/jquery.ui.chatbox.js"></script>
<script type="text/javascript" src="Javascript/chatboxManager.js"></script>
<script type="text/javascript" src="Javascript/chat.js"></script>
<textarea id="username"
          style="display: none"><%=((User) (request.getSession().getAttribute("user"))).getUserName()%></textarea>
<textarea id="name"
          style="display: none"><%=((User) (request.getSession().getAttribute("user"))).getName()%></textarea>
<TEXTAREA id="input" style="display: none"></TEXTAREA>
<input type="text" id="txtMessage" style="display: none" class="form-control"
       placeholder="Type your message here."/>

