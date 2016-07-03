<%@ page import="DataBase.DBFactory" %>
<%@ page import="java.util.List" %>
<%@ page import="Objects.*" %><%--
  Created by IntelliJ IDEA.
  User: Boris
  Date: 29.06.2016
  Time: 2:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% User user = (User) request.getSession().getAttribute("user");%>
<html>
<head>
    <title>Your Transactions</title>
</head>
<body>
<span align="center" style="color: red;  font-size: 18pt; font-family: Sylfaen;"><p>Awaiting Offers</p></span>
<div>
    <script src="https://code.jquery.com/jquery-3.0.0-beta1.min.js"></script>
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
                    type: 'POST',
                    data: {
                        status: "Accepted",
                        transactionID: "<%=ls.get(i).getId()%>"
                    },
                    cache: false,
                    });
                $(<%="un"+i%>).remove();
                $("#resolved").load(location.href+"#resolved");
            });
            $(<%="rej"+ls.get(i).getId()%>).click(function(){
                $.ajax({
                    url: 'ItemBuy',
                    type: 'POST',
                    data: {
                        status: "Rejected",
                        transactionID: "<%= ls.get(i).getId()%>"
                    },
                    cache: false,
                   });
                $(<%="un"+i%>).remove();
                $("#resolved").hide().fadeIn('fast');
            });

        </script>
    </div>
        </p>
<%
            }
        %>
    </div >
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
</body>
</html>
