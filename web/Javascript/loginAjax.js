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
                else {
                    $('#login-form').replaceWith(response);
                }
            });

            return false;
        })
    });

})();

var st ;

function fblogout(){
    if (st=== "connected") {
        FB.logout();
        st = "disconnected";
    }
    if(api!=null) {
        signOut();
    }
    $.ajax({
        url: 'logout',
        type: 'GET'
    }).done(function (response) {
        window.location = "http://localhost:8080/index.jsp";
    });
}


window.fbAsyncInit = function() {
      FB.init({
            appId      : '1548334072140918',
            cookie     : true,  // enable cookies to allow the server to access
                                // the session
            xfbml      : true,  // parse social plugins on this page
            version    : 'v2.6' // use version 2.0
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

function fb_login(){
        FB.login(function(response) {
                if (response.authResponse) {
                    FB.api('/me', {fields: 'name,email,first_name,id'}, function(response) {
                            $.ajax({
                                url: 'FbServlet',
                                type: 'POST',
                                data: {
                                    userName: response.first_name,
                                    name: response.name,
                                    mail: response.email,
                                    image: "http://graph.facebook.com/" + response.id + "/picture?type=large"
                                },
                                cache: false,
                                dataType: "text"
                            }).done(function (response) {
                                if (response == "failed") {
                                    $('#login-error-fb').css("display", "block");
                                    FB.logout();
                                } else {
                                    $('#login-form').replaceWith(response);
                                }
                            });
                    });
                    } else {
                            console.log('User cancelled login or did not fully authorize.');
                    }
            }, {
                scope: 'public_profile,email'
        });
    }

var api = null;
function onSignIn(googleUser) {
     api = gapi;

    var profile = googleUser.getBasicProfile();
    $.ajax({
        url: 'FbServlet',
        type: 'POST',
        data: {
            userName: profile.getGivenName(),
            name: profile.getName(),
            mail: profile.getEmail(),
            image: profile.getImageUrl()
        },
        cache: false,
        dataType: "text"
    }).done(function (response) {
        if (response == "failed") {
            $('#login-error-fb').css("display", "block");
            signOut();
        } else {
            $('#login-form').replaceWith(response);
        }
    });
}

function signOut() {
    var auth2 = api.auth2.getAuthInstance();
    auth2.signOut().then(function () {
        console.log('User signed out.');
    });
}