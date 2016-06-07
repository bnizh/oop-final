(function () {

    <!--  opening modal-->
    $(document).ready(function () {
        // Get the modal
        var modal = document.getElementById('myModal');

// Get the button that opens the modal
        var btn = document.getElementById("new-acc");

// Get the <span> element that closes the modal
        var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal
        btn.onclick = function () {
            modal.style.display = "block";
        };

// When the user clicks on <span> (x), close the modal
        span.onclick = function () {
            modal.style.display = "none";
        };

// When the user clicks anywhere outside of the modal, close it
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
        <!--  opening modal-->

        <!--  change type-->

        $('input:radio[name=user-type]').change(function () {
            var radioValue = $("input[name='user-type']:checked").val();
            if ($('#seller-bt').is(':checked')) {
                $('.seller').css("visibility", "visible").css("display", "block");
                $('.buyer').css("visibility", "hidden").css("display", "none");
            }
            else {
                $('.seller').css("visibility", "hidden").css("display", "none");
                $('.buyer').css("visibility", "visible").css("display", "block");
            }
        });
        <!--  change type-->

    });
})();

