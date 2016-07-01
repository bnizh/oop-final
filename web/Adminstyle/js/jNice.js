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
                    if (data == "success") {
                        $('#new-admin-form')[0].reset();
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


        });
        $('.delete-item').click(function (e) {
            console.log($(this).parent().siblings('.item-id').html());
            e.preventDefault();
            $.ajax({
                url: "item-delete",
                type: 'POST',
                data: {
                    ID: $(this).parent().siblings('.item-id').html()},
                cache: false,
                dataType: "text",
                success: function (data) {
                    var newDoc = document.open("text/html", "replace");
                    newDoc.write(data);
                    newDoc.close();
                }
            });

            return false;
        });
        $('.delete-category').click(function (e) {
            console.log($(this).parent().siblings('.category-id').html());
            e.preventDefault();
            $.ajax({
                url: "cat",
                type: 'POST',
                data: {
                    ID: $(this).parent().siblings('.category-id').html()},
                cache: false,
                dataType: "text",
                success: function (data) {
                    var newDoc = document.open("text/html", "replace");
                    newDoc.write(data);
                    newDoc.close();
                }


            });

            return false;
        });
        var focusedElement;
        $('.category-edit').click(function (e) {
            console.log('asdasdasd');
            var el = $(this).parent().siblings('.td-name').children();
            el.css("border", "3px solid #ff5e01");
            el.attr("readOnly", false);
            el.focus();
            el.focusout(function () {
                $(el).css("border", "none");
                $(el).attr("readOnly", "readOnly");
            });
            el.change(function (e) {
                el.css("border", "none");
                el.attr("readOnly", "readOnly");
                console.log(el.val() + " category");
                console.log(el.parent().siblings('.category-id').html() + " id");
                e.preventDefault();
                $.ajax({
                    url: "cat",
                    type: 'POST',
                    data: {
                        category: el.val(),
                        ID: el.parent().siblings('.category-id').html()
                    }, cache: false,
                    dataType: "text",
                    success: function (data) {
                        var newDoc = document.open("text/html", "replace");
                        newDoc.write(data);
                        newDoc.close();
                    }
                });

                return false;
            });
        });
    });
})();

