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
        $(".clickable-row").click(function() {
            window.document.location = $(this).data("href");
        });
    });
})();

