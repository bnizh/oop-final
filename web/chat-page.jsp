<%--suppress JSUnresolvedFunction --%>
<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: Boris
  Date: 18.06.2016
  Time: 1:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="Objects.User" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<textarea id="username"
          style="display: none"><%=((User) (request.getSession().getAttribute("user"))).getUserName()%></textarea>
<div style="width: 100%;text-align: center">
    <TEXTAREA id="input" style="width:100%; font-size: 18px; height: 80%"></TEXTAREA>
    <input type="text" id="txtMessage" style="width:100%;  height: 9%;" class="form-control"
           placeholder="Type your message here."/>
    <button id="btnSend" class="btn btn-primary" style="width:100%; height: 9%">Send</button>
</div>
<script type="text/javascript">
    var socket;
    var nickname;
    var receiver = "bnizh";
    function startClient() {
        console.log("opening socket");
        socket = new WebSocket("ws://" + document.domain + ":8080/Chat");
        socket.onopen = function () {
            nickname = $('#username').val();
            console.log(nickname);
            socket.send(nickname);
        };

        socket.onmessage = function (a) {
            var message = a.data;
            if (message != "") {
                var inp = $('#input').val();
                inp = inp.concat('\n' + message.substring(0, message.indexOf("#")) + " says: " + message.substring(message.indexOf("#") + 1));
                $('#input').val(inp);
            }
            $('#txtMessage').keyup(function (e) {
                if (e.keyCode == 13) {
                    sendMessage();
                }
            });
            $("#btnSend").click(function () {
                sendMessage();
            });
        };

        socket.onclose = function () {
            //event handler when the socket has been properly closed
        }

        socket.onerror = function () {
            document.write("Error during transfer.");
        }

    }
    //for sending data to the server
    function sendMessage() {
        if ($("#txtMessage").val()) {
            console.log("sent to socket.");
            socket.send(receiver+"$"+nickname+"#"+$("#txtMessage").val());
            $("#txtMessage").val("");
        }
    }

    $(document).ready(function () {
        startClient();
    });
</script>