/**
 * Created by Boris on 28.06.2016.
 */
    var socket;
    function startClient() {
            console.log("opening socket");
            socket = new WebSocket("ws://" + document.domain + ":8080/Transaction");
            socket.onopen = function () {
                    var nickname = $('#userID').val();
                    console.log(nickname);
                    socket.send(nickname);
            };

            socket.onmessage = function (a) {
                if(a.data != "") {
                    var iDiv = document.createElement("textarea");
                    iDiv.id = 'text';
                    iDiv.value = a.data;
                    document.getElementsByTagName('body')[0].appendChild(iDiv);
                    $('#text').css("top","5%").css("margin-left","45%").css("text-align","center").css("position","absolute");
                    var alert = new Audio("alert.mp3");
                    alert.play();
                }
            };
            $( "#sendNot" ).click(function() {
                sendMessage();
                $.ajax({
                    url: 'ItemBuy',
                    type: 'POST',
                    data: {
                        item: $('#itemID').val(),
                        amount: $('#amount').val(),
                        buyer: $('#userID').val()
                    },
                    cache: false,
                    dataType: "text"
                })
            });
            socket.onclose = function () {
                    //event handler when the socket has been properly closed
            };

            socket.onerror = function () {
                document.write("Error during transfer.");
            }

    }
        //for sending data to the server
        function sendMessage() {
            console.log("sent to socket.");
            socket.send($('#receiver-ID').val());
        }

     $(document).ready(function () {
                startClient();
           });