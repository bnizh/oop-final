(function () {
    $(document).ready(function () {
        $("#buyer-registration").submit(function (event) {
            //disable the default form submission
            event.preventDefault();

            $.ajax({
                url: 'AccountServlet',
                type: 'POST',
                data: {
                    busername: $("input[name=busername]").val(),
                    bemail: $("input[name=bemail]").val(),
                    bpassword: $("input[name=bpassword]").val(),
                    bname: $("input[name=bname]").val(),
                    bsurname: $("input[name=bsurname]").val(),
                    bmobile: $("input[name=bmobile]").val(),
                    type: "buyer",
                },

                cache: false,
                dataType: "text",
                success: function (returndata) {
                    alert(returndata);
                }
            });
            return false;

        });

        $("#seller-registration").submit(function (event) {
            //disable the default form submission
            event.preventDefault();
            $.ajax({
                url: 'AccountServlet',
                type: 'POST',
                data: {
                    susername: $("input[name=susername]").val(),
                    semail: $("input[name=semail]").val(),
                    spassword: $("input[name=spassword]").val(),
                    company: $("input[name=company]").val(),
                    smobile: $("input[name=smobile]").val(),
                    type: "seller"
                },

                cache: false,
                dataType: "text",
                success: function (returndata) {
                    alert(returndata);
                }
            });

            return false;
        });

    });
})();


