(function () {
    $(document).ready(function () {
        $('#link_add').hover(function () {
            $('#WebSockets-icon').attr("src", "WebSockets-hover.png");
        });
        $('#WebSockets-icon').hover(function () {
            $('#WebSockets-icon').attr("src", "WebSockets-hover.png");
        });
        $('#link_add').mouseout(function () {
            $('#WebSockets-icon').attr("src", "WebSockets.png");
        });
        $('#img-edit-input').change(function () {
            el = document.getElementById("img-edit-input");
            el.setAttribute("readOnly", "readOnly");
            $('#img-edit').submit();
        });

        $("#comment-form").submit(function (event) {
            //disable the default form submission
            event.preventDefault();
            var formData = new FormData($(this)[0]);

            $.ajax({
                url: "comment",
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
            var formData = new FormData($('#edit-mob')[0]);

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
        $('#pass-change-link').click(function () {
            if ($('#pass-change').css("display") == "none") {
                $('#pass-change').show("slow", function () {
                });
            }
            else {
                $('#pass-change').hide("slow", function () {
                });
                $('#pass-change')[0].reset();

            }
            $("#pass-change-suc-msg").hide("fast", function () {
            });
            $("#pass-change-err-msg").hide("fast", function () {
            });
            $("#pass-change-wrong-msg").hide("fast", function () {
            });
        });


        $("#pass-change").submit(function (event) {
            //disable the default form submission
            event.preventDefault();
            var formData = new FormData($(this)[0]);

            $.ajax({
                url: "edit",
                type: 'POST',
                data: formData,
                success: function (data) {

                    if (data == "success") {
                        $("#pass-change-err-msg").hide("fast", function () {
                        });
                        $("#pass-change-wrong-msg").hide("fast", function () {
                        });
                        $("#pass-change-suc-msg").show("fast", function () {
                        });
                        $("#pass-change").hide("slow", function () {
                        });
                        $('#pass-change')[0].reset();

                    }
                    else if (data == "wrong") {
                        $("#pass-change-wrong-msg").show("fast", function () {
                        });
                        $("#pass-change-suc-msg").hide("fast", function () {
                        });
                        $("#pass-change-err-msg").hide("fast", function () {
                        });
                        $(this).reset();
                    }
                    else {
                        $("#pass-change-err-msg").show("fast", function () {
                        });
                        $("#pass-change-suc-msg").hide("fast", function () {
                        });

                        $("#pass-change-wrong-msg").hide("fast", function () {
                        });
                    }

                },
                cache: false,
                contentType: false,
                processData: false,

            });

            return false;

        });

        $('input:radio').change(
            function () {
                var userRating = this.value;
                event.preventDefault();
                $.ajax({
                    url: 'user',
                    type: 'POST',
                    data: {
                        rate: userRating,
                        ID: $("#user-id-form").val()
                    },
                    cache: false,
                    dataType: "text",
                }).done(function (response) {
                    if (response == "success") {
                        $("#rating-form").hide();
                        $("#rate-result").show();
                    }
                });

            });
        $('a[href*="#"]:not([href="#"])').click(function () {
            if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
                var target = $(this.hash);
                target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
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

