(function () {
    $(document).ready(function () {
        $('#login-input-container').submit(function (event) {
            event.preventDefault();
            $.ajax({
                url: 'login',
                type: 'POST',
                data: {
                    username: $('#login-username').val(),
                    password: $('#login-password').val()
                },
                cache: false,
                dataType: "text",
            }).done(function (response) {
                if (response == "failed") {
                    $('#login-error-msg').css("display", "block");
                }
                else{
                    $('#login-form').replaceWith(response);
                }
            });

            return false;
        })
    });
})();


// This is called with the results from from FB.getLoginStatus().
function statusChangeCallback(response) {
    console.log('statusChangeCallback');
    console.log(response);
    // The response object is returned with a status field that lets the
    // app know the current login status of the person.
    // Full docs on the response object can be found in the documentation
    // for FB.getLoginStatus().
    if (response.status === 'connected') {
        // Logged into your app and Facebook.
        testAPI();
    } else if (response.status === 'not_authorized') {
        // The person is logged into Facebook, but not your app.
    } else {
        // The person is not logged into Facebook, so we're not sure if
        // they are logged into this app or not.
    }
}

// This function is called when someone finishes with the Login
// Button.  See the onlogin handler attached to it in the sample
// code below.
function checkLoginState() {
    FB.getLoginStatus(function(response) {
        statusChangeCallback(response);
    });
}

window.fbAsyncInit = function() {
    FB.init({
        appId      : '1548334072140918',
        cookie     : false,  // enable cookies to allow the server to access
                            // the session
        xfbml      : true,  // parse social plugins on this page
        version    : 'v2.6' // use graph api version 2.6
    });

    // Now that we've initialized the JavaScript SDK, we call
    // FB.getLoginStatus().  This function gets the state of the
    // person visiting this page and can return one of three states to
    // the callback you provide.  They can be:
    //
    // 1. Logged into your app ('connected')
    // 2. Logged into Facebook, but not your app ('not_authorized')
    // 3. Not logged into Facebook and can't tell if they are logged into
    //    your app or not.
    //
    // These three cases are handled in the callback function.

    FB.getLoginStatus(function(response) {
        statusChangeCallback(response);
    });

};

// Load the SDK asynchronously
(function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

// Here we run a very simple test of the Graph API after login is
// successful.  See statusChangeCallback() for when this call is made.
function testAPI() {
    console.log('Welcome!  Fetching your information.... ');
    FB.api('/me', {fields: 'name,email,id'}, function(response) {
       if(response.name!=null&&response.email!=null&&response.id!=null) {
            $('#status').val("connected");
            $.ajax({
                url: 'FbServlet',
                type: 'POST',
                data: {
                    id: response.id,
                    name: response.name,
                    mail: response.email,
                    image: "http://graph.facebook.com/" + response.id + "/picture?type=large"
                },
                cache: false,
                dataType: "text"
            }).done(function (response) {
                $('#login-form').replaceWith(response);
            });
        }
    });
}


function fblogout(response){
    if($('#status').val()=="connected"){
       console.log("bla1");
        FB.logout(response);
        $('#status').val('');
    }
        $.ajax({
            url: 'logout',
            type: 'GET'
        }).done(function (response) {
            window.location = "http://localhost:8080/index.jsp";
        });
}

