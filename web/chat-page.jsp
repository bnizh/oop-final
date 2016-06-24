
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
<%
    User user = (User) request.getSession().getAttribute("user");
%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<span ><%user.getUserName();%></span>
<TEXTAREA id="input" ROWS="5" cols="30"></TEXTAREA>
<input type="text" id="txtMessage" class="form-control" placeholder="Type your message here."/>
<button id="btnSend" class="btn btn-primary" style="width:100%;">Send</button>
<script type="text/javascript">
    var socket;
    function startClient() {
        console.log("opening socket");
         socket = new WebSocket("ws://" + document.domain + ":8080/Chat");
        socket.onopen = function () {
            var nickname = $('#username').val();
            console.log(nickname);
            socket.send(nickname);
        };

        socket.onmessage = function (a) {
            var message = a.data;
            console.log(a.data);
            var inp = $('#input').val();
            inp = inp+message + '/n';
            $('#input').val(inp);

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
                socket.send($("#txtMessage").val());
                $("#txtMessage").val("");
            }
        }

        $(document).ready(function () {
            startClient();
        });
</script>