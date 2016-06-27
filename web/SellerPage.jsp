<%@ page import="Objects.User" %>
<%@ page import="Objects.Item" %>
<%@ page import="java.util.List" %>
<%@ page import="DataBase.DBConnection" %>
<%@ page import="DataBase.DBFactory" %>
<div>
    <div class="user-container">
        <div class="type-header">
            <span>Seller Page</span>
        </div>
        <div class="left-side-user">
            <div style="float:right;">
                <form id="img-edit">
                    <div class="image-upload">
                        <label id="input-label" for="img-edit-input">
                            <img id="pic-edit-icon" class="edit-icon" src="edit.png"/>
                        </label>
                        <input id="img-edit-input" name="simage" accept="image/gif, image/jpeg, image/png" type="file"/>
                    </div>
                </form>
            </div>

            <%User us = (User) request.getSession().getAttribute("user"); %>
            <img src="ImageLoader?FileName=<%=us.getImage()%>" style="width: 200px; height: 200px">
            <div style="color: #990099">
                <span><%=us.getRating()%>  </span>
                <span><%=us.getVoters()%></span>
            </div>
        </div>
        <div class="center-side-user">

            <div>
                <span style="font-size: 30px"><%=us.getUserName()%></span>
            </div>
            <form id="edit-name" class="edit-forms">
                <input type="text" name='sname' class="user-fields" value="<%=us.getName()%>" readonly>
                <img src="edit.png" class="edit-icon edit-icon-uname">
            </form>
            <form class="edit-forms">
                <label> Email:</label>
                <input type="text" name="semail" class="user-fields" value="<%=us.getEmail()%>" readonly>
            </form>
            <form class="edit-forms" id="edit-mob">
                <label> Mobil:</label>
                <input type="text" name="smob" class="user-fields" value=" <%=us.getMobileNumber()%>" readonly
                       title="Mobile">
                <img src="edit.png" class="edit-icon ">
            </form>
            <form>
                <input type="text" style="float:left;" id="pass-change-link" class="user-fields" value="Change Password"
                       readonly
                       title="change Password">
            </form>

        </div>
        <div class="right-side-user">
            <div>
                <a href="add-product.jsp">Add New Product</a>
            </div>
            <div>
                <a href="#my-prod">My Products</a>
            </div>
            <div>
                <a href="#stats">My statistics</a>
            </div>
            <div>
                <a href="#com">Comments</a>
            </div>
        </div>
    </div>

    <div class="type-header" style="width:50%; margin-left:25%;font-size: 20px">
        <div style="width: 100%">
            <span id="pass-change-err-msg"
                  style="color:red; display: none ">Error has occurred during password change</span>
            <span id="pass-change-suc-msg" style="color:green; display: none ">Password has Changed Successfully</span>
            <span id="pass-change-wrong-msg" style="color:red; display: none ">Current password is wrong</span></div>
    </div>
    <form class="edit-forms" id="pass-change"
          style="display: none; width: 30%;    margin-left: 30%;   margin-bottom: 80px;">
        <label> Current Password:</label>
        <input type="password" name="curpassword" class="registration-input"
               title="current password">
        <label> New Password:</label>
        <input type="password" name="newpassword" class="registration-input"
               title=" NewPassword">
        <button id="pass-chnage-submit" style="width: 35%;margin-top: 10px " type="submit"
                class="button registration-submit-buy">Update
        </button>
    </form>
</div>
<div class="div-separator"></div>
<div>
    <div class="user-container">
        <div class="type-header">
            <span id="stats">Statistics</span>
        </div>
        <div>
            <div class="wrapper">

                <div class="table">

                    <div class="row-stat header">
                        <div class="cell">
                            Product Name
                        </div>
                        <div class="cell">
                            Product Price
                        </div>
                        <div class="cell">
                            Overall Sell
                        </div>
                        <div class="cell">
                            Sold Today
                        </div>
                        <div class="cell">
                            Per Day
                        </div>
                    </div>

                    <div class="row-stat">
                        <div class="cell">
                            Pizza
                        </div>
                        <div class="cell">
                            7₾
                        </div>
                        <div class="cell">
                            200
                        </div>
                        <div class="cell">
                            8
                        </div>
                        <div class="cell">
                            11.6
                        </div>
                    </div>

                    <div class="row-stat">
                        <div class="cell">
                            Khchapuri
                        </div>
                        <div class="cell">
                            2₾
                        </div>
                        <div class="cell">
                            300
                        </div>
                        <div class="cell">
                            5
                        </div>
                        <div class="cell">
                            13,4
                        </div>
                    </div>
                    <div class="row-stat">
                        <div class="cell">
                            Khchapuri
                        </div>
                        <div class="cell">
                            2₾
                        </div>
                        <div class="cell">
                            300
                        </div>
                        <div class="cell">
                            5
                        </div>
                        <div class="cell">
                            13,4
                        </div>
                    </div>
                    <div class="row-stat">
                        <div class="cell">
                            Khchapuri
                        </div>
                        <div class="cell">
                            2₾
                        </div>
                        <div class="cell">
                            300
                        </div>
                        <div class="cell">
                            5
                        </div>
                        <div class="cell">
                            13,4
                        </div>
                    </div>
                    <div class="row-stat">
                        <div class="cell">
                            Khchapuri
                        </div>
                        <div class="cell">
                            2₾
                        </div>
                        <div class="cell">
                            300
                        </div>
                        <div class="cell">
                            5
                        </div>
                        <div class="cell">
                            13,4
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="div-separator"></div>

<div>
    <div id="main">
        <div class="type-header">
            <span id="my-prod">My Products</span>
        </div>
        <div id="product-list-user">
            <% DBConnection dbc = DBFactory.getDBConnection();
                List<Item> items = dbc.getItemsBySeller(us.getID());
                for (Item item : items) {
                    out.println("<div class=\"product-user\">" +
                            "                <div>" + item.getName() + "</div>\n" +
                            "                <img src=\"ImageLoader?FileName=" + item.getImage() + " \">\n" +
                            "             <form action=\"item\" method=\"get\">" +
                            "<input name=\"ID\" type=\"hidden\" value=\"" + item.getID() + "\">\n" +
                            "<button  type=\"submit\" class=\"button\"> დეტალურად</button>\n" +
                            "</form>" +
                            "            </div>");

                }
            %>
            <div class="product-user">
                <div>Pizza</div>
                <img src="food.jpg">
                <button class="button"> დეტალურად</button>
            </div>

        </div>
    </div>
</div>
<div class="div-separator"></div>
<div>
    <div class="user-container">
        <div class="type-header">
            <span id="com">Comments</span>
        </div>
    </div>
</div>
