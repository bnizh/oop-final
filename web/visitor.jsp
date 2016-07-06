<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-client_id" content="701248789271-ns6v7dk9metcn4k1bl5vtvfsk75daq15.apps.googleusercontent.com">

<div id="login-form" style="float: right;width: 600px;margin-top: 20px">

    <form id="login-input-container" action="" method="">
        <div style=" height:40px">
            <div style='text-align: center;'>
                <label id="login-error-msg"
                       style=" color:red;display: none">username or password are incorrect</label>
                <label id="login-error-fb"
                       style=" color:red;display: none">You can't Log In this way</label>
            </div>
            <div style="width: 100%;overflow: auto">
                <input id="login-username" class="login-input" type="text" placeholder="Username or E-mail"
                       name="username">
                <input id="login-password" class="login-input" type="password" placeholder="Password" name="password">
                <button id="login-btn" type="submit" class="login-button login-input">Log In</button>
                <div style="width: 80px;float: left;margin-right: 5px" class="g-signin2" id="googleIn" data-onsuccess="onSignIn" data-theme="dark"></div>
                <fb:login-button style="float:left;margin-right:5px" scope="public_profile,email" id="fblog" onlogin="fb_login();">
                </fb:login-button>
            </div>
            <a id="new-acc" style="float: right;margin-right: 40%" href="#"> create new account</a>
        </div>

    </form>
</div>





