(function () {
    $(document).ready(function () {
        $("#buyer-registration").submit(function (event) {
            //disable the default form submission
            if(!ispasswordgood){
                $('#reg-buy-pass').css("background-color","#D08080");
                return;
            }
            if(!passwordmatch){
                $('#reg-buy-pass-con').css("background-color","#D08080");
                return;
            }
            event.preventDefault();
            var formData = new FormData($(this)[0]);

            $.ajax({
                url: "NewAccountServlet",
                type: 'POST',
                data: formData,
                success: function (data) {
                    if(data=="usedusername"){
                        buyerUserNameValidation(data);
                    }
                    else{
                        $('#login-form').replaceWith(data);
                        $('#myModal').css("display", "none");
                    }
                },
                cache: false,
                contentType: false,
                processData: false,

            });

            return false;

        });

        $("#seller-registration").submit(function (event) {
            //disable the default form submission
            if(!ispasswordgood){
                $('#reg-sel-pass').css("background-color","#D08080");
                return;
            }
            if(!passwordmatch){
                $('#reg-sel-pass-con').css("background-color","#D08080");
                return;
            }
            event.preventDefault();
            var formData = new FormData($(this)[0]);

            $.ajax({
                url: "NewAccountServlet",
                type: 'POST',
                data: formData,
                success: function (data) {
                    if(data=="used"){
                        sellerUserNameValidation(data)
                    }
                    else{
                        $('#login-form').replaceWith(data);
                        $('#myModal').css("display", "none");

                    }
                },
                cache: false,
                contentType: false,
                processData: false,

            });

            return false;
        });
        $("#username-reg-b").change(function () {
            event.preventDefault();
            var arg = $(this).val();
            $.ajax({
                url: 'UsernameCheckServlet',
                type: 'POST',
                data: {username: arg},
                cache: false,
                dataType: "text",
            }).done(function (response) {
                if (response == "free") {
                    $("#username-reg-b").css('background-color', '9FEF7D');
                    $("#buyer-reg-message").css('display', 'none');
                }
                if (response == "used") {
                    $("#buyer-reg-message").css('display', 'block');
                    $("#username-reg-b").css('border-color', '#D08080');
                    $("#username-reg-b").css('background-color', '#D08080');
                }
            });

            return false;
        });
        $("#username-reg-s").change(function () {
            event.preventDefault();
            var arg = $(this).val();
            $.ajax({
                url: 'UsernameCheckServlet',
                type: 'POST',
                data: {username: arg},
                cache: false,
                dataType: "text",
            }).done(function (response) {
                sellerUserNameValidation(response);
            });

            return false;
        });
        function sellerUserNameValidation(response) {
            if (response == "free") {
                $("#username-reg-s").css('background-color', '#9FEF7D');
                $("#seller-reg-message").css('display', 'none');
            }
            if (response == "used") {
                $("#seller-reg-message").css('display', 'block');
                $("#username-reg-s").css('border-color', '#D08080');
                $("#username-reg-s").css('background-color', '#D08080');
            }
        }
        function buyerUserNameValidation(response) {
            if (response == "free") {
                $("#username-reg-b").css('background-color', '#9FEF7D');
                $("#buyer-reg-message").css('display', 'none');
            }
            if (response == "used") {
                $("#buyer-reg-message").css('display', 'block');
                $("#username-reg-b").css('border-color', '#D08080');
                $("#username-reg-b").css('background-color', '#D08080');
            }
        }

    });
})();
