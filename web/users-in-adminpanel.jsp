<%@ page import="DataBase.DBConnection" %>
<%@ page import="DataBase.DBFactory" %>
<%@ page import="Objects.Buyer" %>
<%@ page import="Objects.Seller" %>
<%@ page import="java.util.List" %>
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
<main id="seller-table" style="display: none">
    <table class="admin-table" style="margin-top: 20px;">
        <thead>
        <tr>
            <th class="row-1 row-ID">ID</th>
            <th class="row-2 row-image">image</th>
            <th class="row-3 row-username">Username</th>
            <th class="row-3 row-name">name</th>
            <th class="row-4 row-email">Email</th>
            <th class="row-7 row-items">Items</th>
            <th class="row-5 row-type">Type</th>
            <th class="row-6 row-banned">Banned</th>
            <th class="row-8 row-ban">Ban</th>
            <th class="row-9 row-x">X</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Seller seller : sellerList) {

                String banned;
                if (seller.isBanned()) banned = "Yes";
                else banned = "No";
                int size = dbc.getItemsBySeller(seller.getID()).size();
                out.println(" <tr class='clickable-row' data-href='user?ID=" + seller.getID() + "'>\n" +
                        "            <td>" + seller.getID() + "</td>\n"
                );
                if (seller.getImage().contains("https") || seller.getImage().contains("http")) {
                    out.println(
                            "<td><img src=\"" + seller.getImage()    + "\" style=\";height: 40px\" alt=\"\"/></td>\n");
                } else {
                    out.println(
                            "<td><img src=\"ImageLoader?FileName=" + seller.getImage() + "\" style=\";height: 40px\" alt=\"\"/></td>\n");

                }
                out.println(
                        "            <td valign=\"center\">" + seller.getUserName() + "</td>\n" +
                                "            <td valign=\"center\">" + seller.getName() + "</td>\n" +
                                "            <td valign=\"center\">" + seller.getEmail() + "</td>\n" +
                                "            <td valign=\"center\">" + size + "</td>\n" +
                                "            <td valign=\"center\">Seller</td>\n" +
                                "            <td valign=\"center\">" + banned + "</td>\n");
                if (seller.isBanned()) {
                    out.println(
                            " <td class=\"clickable\" ><a href=\"admin?ban=0&type=seller&ID=" + seller.getID() + "\" style=\"color: red;text-decoration:none;border: 1px solid " +
                                    "red;font-size:15px;border-radius: 20% \">Ban Off</a></td>\n" +
                                    "                        <td> <a href=\"admin?delete=1&type=seller&ID=" + seller.getID() + "\" class=\"clickable\" style=\"color: red;text-decoration:none;font-size: 20px\">X</a></td>" +
                                    "        </tr>");
                } else {
                    out.println(
                            " <td class=\"clickable\" ><a href=\"admin?ban=1&type=seller&ID=" + seller.getID() + "\" style=\"color: red;text-decoration:none;border: 1px solid " +
                                    "red;font-size:15px;border-radius: 20% \">Ban</a></td>\n" +
                                    "                        <td> <a href=\"admin?delete=1&type=seller&ID=" + seller.getID() + "\" class=\"clickable\" style=\"color: red;text-decoration:none;font-size: 20px\">X</a></td>" +
                                    "        </tr>");
                }
            }
        %>

        </tbody>
    </table>

</main>
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
            <th class="row-8 row-ban">Ban</th>
            <th class="row-9 row-x">X</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Buyer buyer : buyerList) {
                String banned;
                if (buyer.isBanned()) banned = "Yes";
                else banned = "No";
                out.println(" <tr class='clickable-row' data-href='user?ID=" + buyer.getID() + "'>\n" +
                        "            <td>" + buyer.getID() + "</td>\n");
                if (buyer.getImage().contains("https") || buyer.getImage().contains("http")) {
                    out.println(
                            "<td><img src=\"" + buyer.getImage() + "\" style=\";height: 40px\" alt=\"\"/></td>\n");
                } else {
                    out.println(
                            " <td><img src=\"ImageLoader?FileName=" + buyer.getImage() + "\" style=\";height: 40px\" alt=\"\"/></td>\n");
                }
                out.println(
                        "            <td valign=\"center\">" + buyer.getUserName() + "</td>\n" +
                                "            <td valign=\"center\">" + buyer.getName() + "</td>\n" +
                                "            <td valign=\"center\">" + buyer.getEmail() + "</td>\n" +
                                "            <td valign=\"center\">Buyer</td>\n" +
                                "            <td valign=\"center\">" + banned + "</td>\n");
                if (buyer.isBanned()) {
                    out.println(
                            " <td class=\"clickable\" ><a href=\"admin?ban=0&type=buyer&ID=" + buyer.getID() + "\" style=\"color: red;text-decoration:none;border: 1px solid " +
                                    "red;font-size:15px;border-radius: 20% \">Ban Off</a></td>\n" +
                                    "                        <td> <a href=\"admin?delete=1&type=buyer&ID=" + buyer.getID() + "\" class=\"clickable\" style=\"color: red;text-decoration:none;font-size: 20px\">X</a></td>" +
                                    "        </tr>");
                } else {
                    out.println(
                            " <td class=\"clickable\" ><a href=\"admin?ban=1&type=buyer&ID=" + buyer.getID() + "\" style=\"color: red;text-decoration:none;border: 1px solid " +
                                    "red;font-size:15px;border-radius: 20% \">Ban</a></td>\n" +
                                    "                        <td> <a href=\"admin?delete=1&type=buyer&ID=" + buyer.getID() + "\" class=\"clickable\" style=\"color: red;text-decoration:none;font-size: 20px\">X</a></td>" +
                                    "        </tr>");
                }
            }
        %>

        </tbody>
    </table>

</main>