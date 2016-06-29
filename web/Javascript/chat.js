$(document).ready(function () {
    var socket;
    var idList;
    var nickname;
    var receiver = $('#reciver-username').val();

    function initializeChat() {
        var id = receiver;
        idList.push(id);
        chatboxManager.addBox(id,
            {
                dest: $('#username').val(), // not used in demo
                title: "box",
                first_name: "" + id,
                last_name: ""
                //you can add your own options too
            });

    }

    function startClient() {
        console.log("opening socket");
        socket = new WebSocket("ws://" + document.domain + ":8080/Chat");
        socket.onopen = function () {
            nickname = $('#username').val();
            socket.send(nickname);
        };

        socket.onmessage = function (a) {
            var message = a.data;
            if (message != "") {
                var inp = $('#input').val();
                inp = inp.concat('\n' + message.substring(0, message.indexOf("#")) + " says: " + message.substring(message.indexOf("#") + 1));
                var msg = message.substring(message.indexOf("#") + 1);
                $('#input').val(inp);
                receiver = message.substring(0, message.indexOf("#"));
                initializeChat();
                var alert = new Audio("alert.mp3");
                alert.play();
                $("#" + idList[idList.indexOf(receiver)]).chatbox("option", "boxManager").addMsg(receiver, msg);
            }

        };

        socket.onclose = function () {
            //event handler when the socket has been properly closed
        };

        socket.onerror = function () {
            document.write("Error during transfer.");
        }

    }

    //for sending data to the server
    function sendMessage() {
        if ($("#txtMessage").val()) {
            console.log("sent to socket.");
            socket.send(receiver + "$" + nickname + "#" + $("#txtMessage").val());
            $("#txtMessage").val("");
        }
    }

    $(document).ready(function () {
        startClient();
        idList = [];
        var broadcastMessageCallback = function (from, msg) {
            chatboxManager.addBox(idList[idList.indexOf(from)]);
            $("#txtMessage").val(msg);
            receiver = from;
            sendMessage();
            $("#" + idList[idList.indexOf(from)]).chatbox("option", "boxManager").addMsg(nickname, msg);
        };


        // chatboxManager is excerpt from the original project
        // the code is not very clean, I just want to reuse it to manage multiple chatboxes
        chatboxManager.init({messageSent: broadcastMessageCallback});

        $("#link_add").click(function (event, ui) {
            var id = receiver;
            idList.push(id);
            chatboxManager.addBox(id,
                {
                    dest: $('#username').val(), // not used in demo
                    title: "box",
                    first_name: receiver,
                    last_name: ""
                    //you can add your own options too
                });

            event.preventDefault();
        });

    });
 

});
