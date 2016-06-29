<%@ page import="DataBase.DBConnection" %>
<%@ page import="Objects.ObjectFactory" %>
<%@ page import="DataBase.DBFactory" %>
<%@ page import="java.util.List" %>
<%@ page import="Objects.User" %>
<%@ page import="Objects.Buyer" %>
<%@ page import="Objects.Seller" %>
<%
    DBConnection dbc = DBFactory.getDBConnection();
    List<Buyer> buyerList = dbc.getAllBuyer();
    List<Seller> sellerList = dbc.getAllSeller();
%>
<h2 style="text-align: center"> Users </h2>
<label style="margin-left: 40%;margin-right: 5%;font-size: 20px" class=""><input id="buyer-radio-admin" type="radio"
                                                                                 name="user-type"
                                                                                 checked="checked">buyer</label>
<label style="font-size: 20px" class=""><input id="seller-radio-admin" type="radio" name="user-type">Seller</label>
<main id="buyer-table">
    <table class="admin-table" style="margin-top: 20px">
        <thead>
        <tr>
            <th class="row-1 row-ID">ID</th>
            <th class="row-2 row-image">image</th>
            <th class="row-3 row-username">Username</th>
            <th class="row-3 row-name">name</th>
            <th class="row-4 row-email">Email</th>
            <th class="row-5 row-type">Type</th>
            <th class="row-6 row-banned">Banned</th>
            <th class="row-7 row-items">Items</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Seller seller : sellerList) {
                int size = dbc.getItemsBySeller(seller.getID()).size();
                out.println(" <tr class='clickable-row' data-href='user?ID="+seller.getID()+"'>\n" +
                        "            <td>" + seller.getID() + "</td>\n" +
                        "            <td><img src=\"ImageLoader?FileName=" + seller.getImage() + "\" style=\";height: 40px\" alt=\"\"/></td>\n" +
                        "            <td valign=\"center\">" + seller.getUserName() + "</td>\n" +
                        "            <td valign=\"center\">" + seller.getName() + "</td>\n" +
                        "            <td valign=\"center\">" + seller.getEmail() + "</td>\n" +
                        "            <td valign=\"center\">Seller</td>\n" +
                        "            <td valign=\"center\">No</td>\n" +
                        "            <td valign=\"center\">" + size + "</td>\n" +
                        "        </tr>");
            }
        %>

        </tbody>
    </table>

</main>
<main id="seller-table" style="display: none">
    <table class="admin-table" style="margin-top: 20px">
        <thead>
        <tr>
            <th class="row-1 row-ID">ID</th>
            <th class="row-2 row-image">image</th>
            <th class="row-3 row-username">Username</th>
            <th class="row-3 row-name">name</th>
            <th class="row-4 row-email">Email</th>
            <th class="row-5 row-type">Type</th>
            <th class="row-6 row-banned">Banned</th>
            <th class="row-7 row-items">Items</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Buyer buyer : buyerList) {
                int size = dbc.getItemsBySeller(buyer.getID()).size();
                out.println(" <tr class='clickable-row' data-href='user?ID="+buyer.getID()+"'>\n" +
                        "            <td>" + buyer.getID() + "</td>\n" +
                        "            <td><img src=\"ImageLoader?FileName=" + buyer.getImage() + "\" style=\";height: 40px\" alt=\"\"/></td>\n" +
                        "            <td valign=\"center\">" + buyer.getUserName() + "</td>\n" +
                        "            <td valign=\"center\">" + buyer.getName() + "</td>\n" +
                        "            <td valign=\"center\">" + buyer.getEmail() + "</td>\n" +
                        "            <td valign=\"center\">Buyer</td>\n" +
                        "            <td valign=\"center\">No</td>\n" +
                        "            <td valign=\"center\">" + size + "</td>\n" +
                        "        </tr>");
            }
        %>

        </tbody>
    </table>

</main>