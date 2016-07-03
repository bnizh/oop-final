/**
 * Created by Boris on 03.07.2016.
 */
function selectItem() {
    $.ajax({
        url: 'SearchServlet',
        type: 'GET',
        data: {
            value:$('#searchArea').val()
        },
        cache: true,
        dataType: "text",
    }).done(function (response) {
        if (response != "failed") {
           var items = document.createElement('div');
            items.setAttribute('id', 'product-list');
            items.insertAdjacentHTML('beforeend',response);
            $("#product-list").replaceWith(items);
        }
    });
}

