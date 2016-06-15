(function () {
    $(document).ready(function () {
        $('#img-edit-input').change(function () {
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
                processData: false,

            });

            return false;

        });
        
        $(".edit-name img").click(function () {
           $(".edit-name:input").attr("readonly",true);

        });
    });
})();

