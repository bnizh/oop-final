<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-client_id" content="701248789271-ns6v7dk9metcn4k1bl5vtvfsk75daq15.apps.googleusercontent.com">

<div id="login-form">

    <form id="login-input-container" action="" method="">
        <div class="g-signin2"  id="googleIn" data-onsuccess="onSignIn" data-theme="dark"></div>
        <fb:login-button scope="public_profile,email" id="fblog"  onlogin="fb_login();">
        </fb:login-button>
        <div style="position:relative; width:100%; height:40px">
            <div style='width:100%; height: 0px; text-align: center;'>
                <label id="login-error-msg"
                       style=" color:red;display: none">username or password are incorrect</label>
               <label id="login-error-fb"
                style=" color:red;display: none">You can't Log In this way</label>
            </div>
            <a id="new-acc"  style="height:50px;" href="#"> create new account</a>
            <input id="login-username" class="login-input" type="text" placeholder="Username or E-mail" name="username">
            <input id="login-password" class="login-input" type="password" placeholder="Password" name="password">
            <button id="login-btn" type="submit" class="login-button login-input">Log In</button>

        </div>

    </form>
</div>





