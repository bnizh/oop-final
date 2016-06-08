(function () {
    $(document).ready(function () {
        $("#buyer-registration").submit(function (event) {
            //disable the default form submission
            event.preventDefault();
            var formData = new FormData($(this)[0]);

            $.ajax({
                url: "AccountServlet",
                type: 'POST',
                data: formData,
                success: function (data) {
                    alert(data)
                },
                cache: false,
                contentType: false,
                processData: false,
                success: function (returndata) {
                    alert(returndata);
                }
            });

            return false;

        });

        $("#seller-registration").submit(function (event) {
            //disable the default form submission
            event.preventDefault();
            var formData = new FormData($(this)[0]);

            $.ajax({
                url: "AccountServlet",
                type: 'POST',
                data: formData,
                success: function (data) {
                    alert(data)
                },
                cache: false,
                contentType: false,
                processData: false,
                success: function (returndata) {
                    alert(returndata);
                }
            });

            return false;
        });

    });
})();


