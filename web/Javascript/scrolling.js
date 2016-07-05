(function () {

    $(document).ready(function () {
        var win = $(window);
        // Each time the user scrolls
        console.log($(document).height()+"doc"+win.height()+"win"+win.scrollTop());
        win.scroll(function () {
            // End of the document reached?
            if ($(document).height() - win.height() <win.scrollTop()+50) {
                $('#loading').show();
                setTimeout(function(){  }, 1000);
                $.ajax({
                    url: 'page',
                    type: 'GET',
                    async: false,
                    data: {
                        page: $('#current-page').val(),
                        cat: $('#current-category').val()
                    },
                    dataType: 'html',
                    success: function (html) {
                        $('#current-page').remove();
                        $('#product-list').append(html);
                        $('#loading').hide();
                    }
                });


            }
        });
    });
})();

