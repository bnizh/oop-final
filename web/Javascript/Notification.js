/**
 * Created by Boris on 28.06.2016.
 */
    var socket1;
    function startClient() {
            console.log("opening socket1");
            socket1 = new WebSocket("ws://" + document.domain + ":8080/Transaction");
            socket1.onopen = function () {
                    var nickname = $('#userID1').val();
                    socket1.send(nickname);
            };

            socket1.onmessage = function (a) {
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
                
            });
            socket1.onclose = function () {
                    //event handler when the socket has been properly closed
            };

            socket1.onerror = function () {
                document.write("Error during transfer.");
            }

    }
        //for sending data to the server
        function sendMessage() {
            console.log("sent to socket1.");
            socket1.send($('#receiver-ID1').val()+"$"+$('#itemID').val()+"#"+$('#amount').val()+"@"+ $('#userID1').val());
        }+

     $(document).ready(function () {
                startClient();
           });