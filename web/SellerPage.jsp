<%@ page import="Objects.User" %>
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
        </div>
        <div class="right-side-user">
            <div>
                <a href="#">Add New Product</a>
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
</div>
<div class="div-separator"></div>
<div>
    <div class="user-container">
        <div class="type-header">
            <span id="stats">Statistics</span>
        </div>
        <div>
            <div class="wrapper" >

                <div class="table">

                    <div  class="row header">
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

                    <div class="row">
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

                    <div class="row">
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
                    <div class="row">
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
                    <div class="row">
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
                    <div class="row">
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
            <div class="product-user">
                <div>Pizza</div>
                <img src="food.jpg">
                <button class="button"> დეტალურად</button>
            </div>
            <div class="product-user">
                <div>Pizza</div>
                <img src="food.jpg">
                <button class="button"> დეტალურად</button>
            </div>
            <div class="product-user">
                <div>Pizza</div>
                <img src="food.jpg">
                <button class="button"> დეტალურად</button>
            </div>
            <div class="product-user">
                <div>Pizza</div>
                <img src="food.jpg">
                <button class="button"> დეტალურად</button>
            </div>
            <div class="product-user">
                <div>Pizza</div>
                <img src="food.jpg">
                <button class="button"> დეტალურად</button>
            </div>
            <div class="product-user">
                <div>Pizza</div>
                <img src="food.jpg">
                <button class="button"> დეტალურად</button>
            </div>
            <div class="product-user">
                <div>Pizza</div>
                <img src="food.jpg">
                <button class="button"> დეტალურად</button>
            </div>
            <div class="product-user">
                <div>Pizza</div>
                <img src="food.jpg">
                <button class="button"> დეტალურად</button>
            </div>
            <div class="product-user">
                <div>Pizza</div>
                <img src="food.jpg">
                <button class="button"> დეტალურად</button>
            </div>
            <div class="product-user">
                <div>Pizza</div>
                <img src="food.jpg">
                <button class="button"> დეტალურად</button>
            </div>
            <div class="product-user">
                <div>Pizza</div>
                <img src="food.jpg">
                <button class="button"> დეტალურად</button>
            </div>
            <div class="product-user">
                <div>Pizza</div>
                <img src="food.jpg">
                <button class="button"> დეტალურად</button>
            </div>
            <div class="product-user">
                <div>Pizza</div>
                <img src="food.jpg">
                <button class="button"> დეტალურად</button>
            </div>
            <div class="product-user">
                <div>Pizza</div>
                <img src="food.jpg">
                <button class="button"> დეტალურად</button>
            </div>
            <div class="product-user">
                <div>Pizza</div>
                <img src="food.jpg">
                <button class="button"> დეტალურად</button>
            </div>
            <div class="product-user">
                <div>Pizza</div>
                <img src="food.jpg">
                <button class="button"> დეტალურად</button>
            </div>
            <div class="product-user">
                <div>Pizza</div>
                <img src="food.jpg">
                <button class="button"> დეტალურად</button>
            </div>
            <div class="product-user">
                <div>Pizza</div>
                <img src="food.jpg">
                <button class="button"> დეტალურად</button>
            </div>
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