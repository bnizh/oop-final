<%@ page import="Managers.ManagerFactory" %>
<%@ page import="Objects.ObjectFactory" %>
<%@ page import="Objects.User" %>
<%@ page import="DataBase.DBConnection" %>
<%@ page import="DataBase.DBFactory" %>
<%@ page import="Objects.Buyer" %>
<%@ page import="Objects.Seller" %><%--
  Created by IntelliJ IDEA.
  User: Boris
  Date: 26.06.2016
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>
<title>Confirmation</title>
<% String st = (String) request.getParameter("hash");
    boolean b = false;
    if (ObjectFactory.getUnactivedMap().get(st) != null) {
        String email = ObjectFactory.getUnactivedMap().get(st);
        User us = DBFactory.getDBConnection().getBuyerByEmail(email);
        if (us != null) {
            us.setConfirmed(true);
            DBFactory.getDBConnection().updateBuyerWithoutImage((Buyer) us);
            ObjectFactory.getUnactivedMap().remove(st);
            b = true;
        } else {
            us = DBFactory.getDBConnection().getSellerByEmail(email);
            if (us != null) {
                us.setConfirmed(true);
                DBFactory.getDBConnection().updateSellerWithoutImage((Seller) us);
                ObjectFactory.getUnactivedMap().remove(st);
                b = true;;
            }
        }
    }
    if (b) {
%>
<div align="center" style="color: green;  font-size: 18pt;  font-family: Sylfaen;"><span href=""><p>Thank you for confirming your mail.</p></span>
</div>
<div align="center" style="color: green;  font-size: 18pt; font-family: Sylfaen;"><span href=""><p>Now your account is activated.</p></span>
</div>
<%} else {%>
<div align="center" style="color: red;  font-size: 18pt; font-family: Sylfaen;"><span
        href=""><p>Something went wrong!</p></span></div>
<%}%>
<script type="text/javascript">
    setTimeout(function () {
        window.location.href = "http://localhost:8080/index.jsp" ;
    }, 2000);
</script>
</body>
</html>
