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
                url: "edit",
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
        $('#edit-name img').click(function () {

            $('#edit-name input').css("border", "1px solid #ff5e01");
            $('#edit-name input').attr("readOnly", false);
            $('#edit-name input').focus();
        });
        $('#edit-name input').focusout(function () {
            $('#edit-name input').css("border", "none");
        });
        $('#edit-name input').change(function () {
            $('#edit-name input').css("border", "none");
            $('#edit-name input').attr("readOnly", "readOnly");
            data = $('#edit-name input').val();
            event.preventDefault();
            var formData = new FormData($('#edit-name')[0]);

            $.ajax({
                url: "edit",
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

        $('#edit-mob img').click(function () {
            $('#edit-mob input').css("border", "1px solid #ff5e01");
            $('#edit-mob input').attr("readOnly", false);
            $('#edit-mob input').focus();
        });
        $('#edit-mob input').focusout(function () {
            $('#edit-mob input').css("border", "none");
        });
        $('#edit-mob input').change(function () {
            $('#edit-mob input').css("border", "none");
            $('#edit-mob input').attr("readOnly", "readOnly");
            data = $('#edit-mob input').val();
            event.preventDefault();
            var formData = new FormData($('#edit-email')[0]);

            $.ajax({
                url: "edit",
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
        $(".rating input:radio").attr("checked", false);
        $('.rating input').click(function () {
            $(".rating span").removeClass('checked');
            $(this).parent().addClass('checked');
        });

        $('input:radio').change(
            function () {
                var userRating = this.value;
                event.preventDefault();
                $.ajax({
                    url: 'rate',
                    type: 'POST',
                    data: {
                        rate: userRating
                    },
                    cache: false,
                    dataType: "text",
                }).done(function (response) {
                    $('.rating').replaceWith(response);
                });

            });
        $('a[href*="#"]:not([href="#"])').click(function() {
            if (location.pathname.replace(/^\//,'') == this.pathname.replace(/^\//,'') && location.hostname == this.hostname) {
                var target = $(this.hash);
                target = target.length ? target : $('[name=' + this.hash.slice(1) +']');
                if (target.length) {
                    $('html, body').animate({
                        scrollTop: target.offset().top
                    }, 1000);
                    return false;
                }
            }
        });

    });
})
();

