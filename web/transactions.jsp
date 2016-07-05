<%@ page import="DataBase.DBFactory" %>
<%@ page import="java.util.List" %>
<%@ page import="Objects.*" %><%--
  Created by IntelliJ IDEA.
  User: Boris
  Date: 29.06.2016
  Time: 2:05
  To change this template use File | Settings | File Templates.
--%>
<link href="css/main.css" rel="stylesheet">
<link href="css/usercss.css" rel="stylesheet">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <div class="header">
        <div class="header1">
            <a href="\index.jsp" id="logo">Food-Online</a>
        </div>
        <%
            Boolean bool = (Boolean) session.getAttribute("loggedIn");
            if (!bool) {
                out.println("<script type=\"text/javascript\">  window.location.href = \"http://localhost:8080/error.html\"; </script>");
            }
            if (!bool) {
        %>
        <script src="Javascript/script.js"></script>
        <%@include file="visitor.jsp" %>

        <% } else {
        %>
        <%@include file="user-panel.jsp" %>

    </div>
</head>
<body>
<span align="center" style="color: red;  font-size: 18pt; font-family: Sylfaen;"><p>Awaiting Offers</p></span>
<div>
      <% List<Transaction> ls = DBFactory.getDBConnection().getUnresolvedTransactionBySeller(user.getID());
            for(int i=0; i< ls.size(); i++){
                Item it = DBFactory.getDBConnection().getItemById(ls.get(i).getItemID());
                User b = DBFactory.getDBConnection().getSellerByID(ls.get(i).getBuyerID());
                if(b==null)
                    b = DBFactory.getDBConnection().getBuyerByID(ls.get(i).getBuyerID());
                Seller s =DBFactory.getDBConnection().getSellerByID(ls.get(i).getSellerID());
            %><p><div id =<%="un"+i%>>
            <span>Item Name : <%=it.getName()%></span>
            <span>Item Price : <%=it.getPrice()%></span>
            <span id=<%="Amount"+ls.get(i).getId()%>>Amount : <%= ls.get(i).getAmount()%></span>
            <span>Sum :<%=it.getPrice()*ls.get(i).getAmount()%></span>
            <span>Buyer : <%=b.getName()%></span>
            <span>Seller : <%=s.getName()%></span>
            <span>Transaction ID : <%= ls.get(i).getId()%></span>
            <div id=<%="buttons"+ls.get(i).getId()%>>
            <button  id=<%="acc"+ls.get(i).getId()%> >Accept</button>
            <button  id=<%="rej"+ls.get(i).getId()%>>Reject</button>
        <script type="text/javascript">
            $(<%="acc"+ls.get(i).getId()%>).click(function(){
                $.ajax({
                    url: 'ItemBuy',
                    type: 'GET',
                    data: {
                        status: "Accepted",
                        transactionID: "<%=ls.get(i).getId()%>",
                        userID : "<%=user.getID()%>"
                    },
                    cache: false,
                    }).done(function (response) {
                    if (response != "failed") {
                        $(<%="un"+i%>).remove();
                        var trans = document.createElement('div');
                        trans.setAttribute('id', 'resolved');
                        trans.insertAdjacentHTML('beforeend',response);
                        $("#resolved").replaceWith(trans);
                    }
                });
            });
            $(<%="rej"+ls.get(i).getId()%>).click(function(){
                $.ajax({
                    url: 'ItemBuy',
                    type: 'GET',
                    data: {
                        status: "Rejected",
                        transactionID: "<%= ls.get(i).getId()%>",
                        userID : "<%=user.getID()%>"
                    },
                    cache: false,
                   }).done(function (response) {
                    if (response != "failed") {
                        $(<%="un"+i%>).remove();
                        var trans = document.createElement('div');
                        trans.setAttribute('id', 'resolved');
                        trans.insertAdjacentHTML('beforeend',response);
                        $("#resolved").replaceWith(trans);
                    }
                });

            });

        </script>
    </div>
        </p>
</div>
<%
            }
        %>

    </div>
<span align="center" style="color: red;  font-size: 18pt; font-family: Sylfaen;"><p>Accepted Offers</p></span></div>
    <div id="resolved" >
    <% List<Transaction> list = DBFactory.getDBConnection().getTransactionByBuyer(user.getID());
        if(list.size()==0)
            list= DBFactory.getDBConnection().getTransactionBySeller(user.getID());
        for(int i=0; i< list.size(); i++){
            Item it = DBFactory.getDBConnection().getItemById(list.get(i).getItemID());
            User b = DBFactory.getDBConnection().getSellerByID(list.get(i).getBuyerID());
            if(b==null)
                b = DBFactory.getDBConnection().getBuyerByID(list.get(i).getBuyerID());
            Seller s =DBFactory.getDBConnection().getSellerByID(list.get(i).getSellerID());
    %><p>
    <span>Item Name : <%=it.getName()%></span>
    <span>Item Price : <%=it.getPrice()%></span>
    <span>Amount : <%= list.get(i).getAmount()%></span>
    <span>Sum :<%=it.getPrice()*list.get(i).getAmount()%></span>
    <span>Buyer : <%=b.getName()%></span>
    <span>Seller : <%=s.getName()%></span>
    <span>Transaction ID : <%= list.get(i).getId()%></span>
</p>
    <%
        }
    %>
</div>
<% }%>
</body>
</html>
