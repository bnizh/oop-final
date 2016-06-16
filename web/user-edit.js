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
                    el.setAttribute("readOnly", "");
                },
                cache: false,
                contentType: false,
                processData: false,

            });

            return false;

        });
        $('#edit-name img').click(function () {
            alert($('#edit-name input'));
            $('#edit-name input').css("border", "1px solid #ff5e01")
            $('#edit-name input').attr("readOnly", false);
            $('#edit-name input').focus();
        });

        $('#edit-name input').change(function () {
            $('#edit-name input').css("border", "none")
            $('#edit-name input').attr("readOnly", "readOnly");
            alert($('#edit-name input').val());
            data=$('#edit-name input').val();
            event.preventDefault();
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
                processData: false,

            });

            return false;
        })
    });
})();

