(function () {
    $(document).ready(function () {

        $("#item-add-form").submit(function (event) {
            //disable the default form submission
            event.preventDefault();
            var formData = new FormData($(this)[0]);

            $.ajax({
                url: "item",
                type: 'POST',
                data: formData,
                success: function (data) {

                    if (data == "success") {
                        $("#add-item-err-msg").css("display", 'Block');
                        $("#add-item-err-msg").css("color", 'green');
                        $('#item-add-form')[0].reset();
                    }
                    else {
                        $("#add-item-err-msg").text('Error Has Occurred');
                        $("#add-item-err-msg").css('color', 'red');
                    }

                },
                cache: false,
                contentType: false,
                processData: false

            });

            return false;

        });

        $("#reg-sub-b").click(function (e) {
            //disable the default form submission
            e.preventDefault();
            allright = true;
            if (!ispasswordgood) {
                allright = false;
                $('#reg-buy-pass').css("background-color", "#D08080");
            }
            if (!passwordmatch) {
                allright = false;
                $('#reg-buy-pass-con').css("background-color", "#D08080");
            }
            if ($('#username-reg-b').val() <= 2) {
                allright = false;
                $('#username-reg-b').css("background-color", "#D08080");
            }
            if ($('#buyer-email').val() <= 3) {
                allright = false;
                $('#buyer-email').css("background-color", "#D08080");
            }

            if ($('#buyer-name').val() <= 3) {
                allright = false;
                $('#buyer-name').css("background-color", "#D08080");
            }

            if (allright) {
                buyerSend();
            }
        });

        $("#reg-sub-s").click(function (e) {
            e.preventDefault();
            //disable the default form submission
            allright = true;
            if (!ispasswordgood) {
                allright = false;
                $('#reg-sel-pass').css("background-color", "#D08080");
            }
            if (!passwordmatch) {
                allright = false;
                $('#reg-sel-pass-con').css("background-color", "#D08080");
            }
            if ($('#username-reg-s').val() <= 2) {
                allright = false;
                $('#username-reg-s').css("background-color", "#D08080");
            }
            if ($('#seller-email').val() <= 5) {
                allright = false;
                $('#seller-email').css("background-color", "#D08080");
            }
            if ($('#seller-name').val() <= 3) {
                allright = false;
                $('#seller-name').css("background-color", "#D08080");
            }
          
            if (allright) {
                sellerSend();
            }
        });
        function buyerSend() {
            event.preventDefault();
            var formData = new FormData($("#buyer-registration")[0]);

            $.ajax({
                url: "NewAccountServlet",
                type: 'POST',
                data: formData,
                success: function (data) {
                    if (data == "usedusername") {
                        buyerUserNameValidation(data);
                    }
                    else if (data == "usedemail") {
                        $('#email-reg-msg').css("display", "block");
                        $('#buyer-email-msg').css("background-color", "D08080");
                    }
                    else {
                        $('#login-form').replaceWith(data);
                        $('#myModal').css("display", "none");
                    }
                },
                cache: false,
                contentType: false,
                processData: false

            });

            return false;

        }

        function sellerSend() {
            event.preventDefault();
            var formData = new FormData($("#seller-registration")[0]);
            $.ajax({
                url: "NewAccountServlet",
                type: 'POST',
                data: formData,
                success: function (data) {
                    if (data == "usedusername") {
                        buyerUserNameValidation(data);
                    }
                    else if (data == "usedemail") {
                        $('#email-reg-msg').css("display", "block");
                        $('#buyer-email-msg').css("background-color", "D08080");
                    }
                    else {
                        $('#login-form').replaceWith(data);
                        $('#myModal').css("display", "none");
                    }
                },
                cache: false,
                contentType: false,
                processData: false

            });

            return false;

        }

        $("#username-reg-b").change(function () {
            event.preventDefault();
            var arg = $(this).val();
            $.ajax({
                url: 'UsernameCheckServlet',
                type: 'POST',
                data: {username: arg},
                cache: false,
                dataType: "text"
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
                dataType: "text"
            }).done(function (response) {
                sellerUserNameValidation(response);
            });

            return false;
        });
        usernameIsGood = false;
        function sellerUserNameValidation(response) {
            if (response == "free") {
                usernameIsGood = true;
                $("#username-reg-s").css('background-color', '#9FEF7D');
                $("#seller-reg-message").css('display', 'none');
            }
            if (response == "used") {
                usernameIsGood = false;
                $("#seller-reg-message").css('display', 'block');
                $("#username-reg-s").css('border-color', '#D08080');
                $("#username-reg-s").css('background-color', '#D08080');
            }
        }

        function buyerUserNameValidation(response) {
            if (response == "free") {
                usernameIsGood = true;
                $("#username-reg-b").css('background-color', '#9FEF7D');
                $("#buyer-reg-message").css('display', 'none');
            }
            if (response == "used") {
                usernameIsGood = false;
                $("#buyer-reg-message").css('display', 'block');
                $("#username-reg-b").css('border-color', '#D08080');
                $("#username-reg-b").css('background-color', '#D08080');
            }
        }

    });
})();
