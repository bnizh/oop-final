<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<html>
<head>
    <title>Cart</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <link href="main.css" rel="stylesheet">
    <script src="script.js"></script>
    <script src="AjaxSending.js"></script>
</head>
<body>
<!-- Header -->
<div class="header">
    <div class="header1">
        <a href="#" id="logo">Food-Online</a>
    </div>
    <div>
        <form class="login-input-container" action="/AccountServlet" method="post">
            <div style="position:relative; width:100%; height:90px">
                <div style='width:100%; height:30px'></div>
                <input class="login-input" type="text" placeholder="Username or E-mail" name="username">
                <input type="hidden" value="true" name="login">
                <input class="login-input" type="password" placeholder="Password" name="password">
                <button type="submit" class="login-button login-input">Log In</button>
                <a id="new-acc" href="#"> creat new account</a>
            </div>

        </form>
    </div>
</div>
<!-- Header -->
<!-- Main -->
<div>
    <div id="main">
        <div class="left-menu">
            <a class="left-menu-item" href="#">dradad</a>
            <a class="left-menu-item" href="#">dradad</a>
            <a class="left-menu-item" href="#">dradad</a>
            <a class="left-menu-item" href="#">dradad</a>
            <a class="left-menu-item" href="#">dradad</a>
            <a class="left-menu-item" style="border: none" href="#">dradad</a>
        </div>
        <div id="product-list">
            <div class="product">
                <div>ხუთი ლულა</div>
                <img src="food.jpg">
                <button class="button"> დეტალურად</button>
            </div>
            <div class="product">
                <div>ხუთი ლულა</div>
                <img src="food.jpg">
                <button class="button"> დეტალურად</button>
            </div>
            <div class="product">
                <div>ხუთი ლულა</div>
                <img src="food.jpg">
                <button class="button"> დეტალურად</button>
            </div>
            <div class="product">
                <div>ხუთი ლულა</div>
                <img src="food.jpg">
                <button class="button"> დეტალურად</button>
            </div>
            <div class="product">
                <div>ხუთი ლულა</div>
                <img src="food.jpg">
                <button class="button"> დეტალურად</button>
            </div>
            <div class="product">
                <div>ხუთი ლულა</div>
                <img src="food.jpg">
                <button class="button"> დეტალურად</button>
            </div>
            <div class="product">
                <div>ხუთი ლულა</div>
                <img src="food.jpg">
                <button class="button"> დეტალურად</button>
            </div>
            <div class="product">
                <div>ხუთი ლულა</div>
                <img src="food.jpg">
                <button class="button"> დეტალურად</button>
            </div>
            <div class="product">
                <div>ხუთი ლულა</div>
                <img src="food.jpg">
                <button class="button"> დეტალურად</button>
            </div>
        </div>

    </div>
</div>
<!-- Main -->


<div id="footer"></div>
<!-- Footer -->
<div id="myModal" class="modal">

    <!-- Registration Modal content -->
    <div class="modal-content">
        <div class="modal-header">
            <span class="close">×</span>
            <h2>Registration</h2>
        </div>
        <div class="modal-body">
            <div class="user-type">
                <label style="margin-right: 5%" class=""><input id="buyer-bt" type="radio"
                                                                name="user-type"
                                                                checked="checked">buyer</label>
                <label class=""><input id="seller-bt" type="radio" name="user-type">Seller</label>
            </div>
            <div class="row buyer">
                <form id="buyer-registration" action="" method="" enctype="multipart/form-data">
                    <input type="hidden" value="buyer" name="type">
                    <div id="buyer-reg-message" style="display: none; text-align: center"><label style="color:red">Username
                        is already used</label></div>
                    <div>
                        <label class="reg-label">UserName:</label>
                        <input id="username-reg-b" type="text" class=" search-form-control reg-form"
                               name="username"
                               value=""
                               placeholder="UserName">
                    </div>
                    <div>
                        <label class=" reg-label">E-Mail:</label>
                        <input type="email" class=" search-form-control reg-form"
                               name="email"
                               value=""
                               placeholder="E-mail">
                    </div>
                    <div>
                        <label class="reg-label">Password:</label>
                        <input type="password" class=" search-form-control reg-form "
                               name="password"
                               value=""
                               placeholder="Password">
                    </div>
                    <div>
                        <label class=" reg-label">Name:</label>
                        <input type="text" class=" search-form-control reg-form"
                               name="name"
                               value=""
                               placeholder="Name">
                    </div>
                    <div>
                        <label class=" reg-label">Surname</label>
                        <input type="text" class="search-form-control reg-form"
                               name="surname"
                               value=""
                               placeholder="Surname">
                    </div>
                    <div>
                        <label class=" reg-label">Mobile Number:</label>
                        <input type="number" class="search-form-control reg-form"
                               name="mobile"
                               value=""
                               placeholder="Mobile Number">
                    </div>
                    <div>
                        <label class=" reg-label">Profile Picture</label>
                        <input type="file" name="file">
                    </div>
                    <div>
                        <button type="submit" class="button registration-submit-buy">Confirm</button>
                    </div>
                </form>
            </div>

            <div class="row seller" style="visibility: hidden; display:none;">
                <form id="seller-registration" action="" method="">
                    <input type="hidden" value="seller" name="type">
                    <div id="seller-reg-message" style="display: none; text-align: center"><label style="color:red">Username
                        is already used</label></div>
                    <div>
                        <label class="reg-label">UserName:</label>
                        <input id="username-reg-s" type="text" class=" search-form-control reg-form"
                               name="username"
                               value=""
                               placeholder="UserName">
                    </div>
                    <div>
                        <label class=" reg-label">E-Mail:</label>
                        <input type="email" class=" search-form-control reg-form"
                               name="email"
                               value=""
                               placeholder="E-mail">
                    </div>
                    <div>
                        <label class="reg-label">Password:</label>
                        <input type="password" class=" search-form-control reg-form "
                               name="password"
                               value=""
                               placeholder="Password">
                    </div>
                    <div>
                        <label class=" reg-label">Company Name:</label>
                        <input type="text" class=" search-form-control reg-form"
                               name="company"
                               value=""
                               placeholder="Name">
                    </div>

                    <div>
                        <label class=" reg-label">Mobile Number:</label>
                        <input type="number" class="search-form-control reg-form"
                               name="mobile"
                               value=""
                               placeholder="Mobile Number">
                    </div>
                    <div>
                        <label class=" reg-label">Profile Picture</label>
                        <input type="file" name="file">
                    </div>
                    <div>
                        <button type="submit" class="button registration-submit-buy">Confirm</button>
                    </div>
                </form>
            </div>
        </div>

    </div>

</div>

</body>
</html>
