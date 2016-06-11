(function () {
    $(document).ready(function () {
        $('#login-input-container').submit(function (event) {
            event.preventDefault();
            var formData = new FormData($(this)[0]);
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

