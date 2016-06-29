(function () {
    $(document).ready(function () {
        $('input:radio[name=user-type]').change(function () {

            if ($('#seller-radio-admin  ').is(':checked')) {
                $('#seller-table').css("visibility", "visible").css("display", "block");
                $('#buyer-table').css("visibility", "hidden").css("display", "none");
            }
            else {
                $('#seller-table').css("visibility", "hidden").css("display", "none");
                $('#buyer-table').css("visibility", "visible").css("display", "block");
            }
        });

        $('#new-admin-form').submit(function (e) {
            e.preventDefault(e);
            var formData = new FormData($(this)[0]);
            $.ajax({
                url: "superadmin",
                type: 'POST',
                data: formData,
                success: function (data) {
                   if(data=="success"){
                       $('#succ-msg').show();
                       $('#error-msg').hide();
                   }
                    else {
                       $('#succ-msg').hide();
                       $('#error-msg').show();
                   }
                },
                cache: false,
                contentType: false,
                processData: false

            });


        })
    });
})();

