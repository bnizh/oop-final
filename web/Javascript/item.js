(function () {
    $(document).ready(function () {
        $('#img-edit-input').change(function () {
            el = document.getElementById("img-edit-input");
            el.setAttribute("readOnly", "readOnly");
            $('#img-edit').submit();
        });


        $("#img-edit").submit(function (event) {
            //disable the default form submission
            event.preventDefault();
            var formData = new FormData($(this)[0]);

            $.ajax({
                url: "item-edit",
                type: 'POST',
                data: formData,
                success: function (data) {
                    var newDoc = document.open("text/html", "replace");
                    newDoc.write(data);
                    newDoc.close();
                },
                cache: false,
                contentType: false,
                processData: false

            });

            return false;

        });
        $('#edit-desc img').click(function () {

            $('#edit-desc textarea').css("border", "1px solid #ff5e01");
            $('#edit-desc textarea').attr("readOnly", false);
            $('#edit-desc textarea').focus();
        });
        $('#edit-desc textarea').focusout(function () {
            $('#edit-desc textarea').css("border", "none");
        });
        $('#edit-desc textarea').change(function () {
            $('#edit-desc textarea').css("border", "none");
            $('#edit-desc textarea').attr("readOnly", "readOnly");
            data = $('#edit-desc textarea').val();
            event.preventDefault();
            var formData = new FormData($('#edit-desc')[0]);

            $.ajax({
                url: "item-edit",
                type: 'POST',
                data: formData,
                success: function (data) {
                    var newDoc = document.open("text/html", "replace");
                    newDoc.write(data);
                    newDoc.close();
                },
                cache: false,
                contentType: false,
                processData: false

            });

            return false;
        });

        $('#edit-price img').click(function () {
            $('#edit-price input').css("border", "1px solid #ff5e01");
            $('#edit-price input').attr("readOnly", false);
            $('#edit-price input').focus();
        });
        $('#edit-price input').focusout(function () {
            $('#edit-price input').css("border", "none");
        });
        $('#edit-price input').change(function () {
            $('#edit-price input').css("border", "none");
            $('#edit-price input').attr("readOnly", "readOnly");
            data = $('#edit-price input').val();
            event.preventDefault();
            var formData = new FormData($('#edit-price')[0]);

            $.ajax({
                url: "item-edit",
                type: 'POST',
                data: formData,
                success: function (data) {

                },
                cache: false,
                contentType: false,
                processData: false

            });

            return false;
        });
        /*  ratings                 --------------------------*/
        $(".rating input:radio").attr("checked", false);
        $('.rating input').click(function () {
            $(".rating span").removeClass('checked');
            $(this).parent().addClass('checked');
        });

      

    });
})
();

