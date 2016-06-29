(function () {
    $(document).ready(function () {
        $('#load-more-comment').click(function () {
            $('#loading').show();
            $.ajax({
                url: 'comment',
                type: 'GET',
                async: true,
                data: {
                    page: $('#comment-page').val(),
                    ID: $('#comment-owner-id').val(),
                    type:$('#comment-owner-type').val()
                },
                dataType: 'html',
                success: function (html) {
                    $('#comment-page').remove();
                    $('#comments-box').append(html);
                    $('#loading').hide();
                    if (html=="") {
                        $('#load-more-comment').remove();
                    }
                }
            });
        });
    });
})();

