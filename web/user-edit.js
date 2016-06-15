(function () {
    $(document).ready(function () {
        $('#img-edit-input').change(function () {
            alert("submit gamoidzaxa");
            $('#img-edit').submit();
        })
        $("#img-edit").submit(function (event) {
            //disable the default form submission
            alert("submit gamoidzaxa");
            event.preventDefault();
            var formData = new FormData($(this)[0]);

            $.ajax({
                url: "Edit",
                type: 'POST',
                data: formData,
                success: function (data) {
                    if(data=="failed"){
                      
                    }
                    else{
                        $('#login-form').replaceWith(data);
                    }
                },
                cache: false,
                contentType: false,
                processData: false,

            });

            return false;

        });
    });
})();

