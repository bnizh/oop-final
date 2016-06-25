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
<link rel="stylesheet" href="css/ui-lightness/jquery-ui-1.8.2.custom.css" />
<script type="text/javascript" src="Javascript/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="Javascript/jquery-ui-1.8.2.custom.min.js"></script>
<link type="text/css" href="css/jquery.ui.chatbox.css" rel="stylesheet" />
<script type="text/javascript" src="Javascript/jquery.ui.chatbox.js"></script>
<script type="text/javascript" src="Javascript/chatboxManager.js"></script>
<textarea id="username"
          style="display: none"><%=((User) (request.getSession().getAttribute("user"))).getUserName()%></textarea>
<textarea id="name"
style="display: none"><%=((User) (request.getSession().getAttribute("user"))).getName()%></textarea>
<div style="width: 100%;text-align: center">
    <TEXTAREA id="input" style="display: none"></TEXTAREA>
    <input type="text" id="txtMessage"  style="display: none" class="form-control"
           placeholder="Type your message here."/>
    <button id="link_add" href="#">Chat</button>
</div>
<script type="text/javascript">
    var socket;
    var idList;
    var nickname;
    var receiver = "bnizh";

    function initializeChat(){
        var id = receiver;
            idList.push(id);
            console.log("in 2")
            chatboxManager.addBox(id,
                    {
                        dest: $('#username').val(), // not used in demo
                        title: "box",
                        first_name: ""+id,
                        last_name: ""
                        //you can add your own options too
                    });

    }
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
                var msg = message.substring(message.indexOf("#") + 1);
                $('#input').val(inp);
                receiver = message.substring(0, message.indexOf("#"));
                initializeChat();
                $("#" + idList[idList.indexOf(receiver)]).chatbox("option", "boxManager").addMsg(receiver, msg);
            }

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
        idList = new Array();
        var broadcastMessageCallback = function(from, msg) {
                chatboxManager.addBox(idList[idList.indexOf(from)]);
                $("#txtMessage").val(msg);
                receiver = from;
                sendMessage();
                $("#" + idList[idList.indexOf(from)]).chatbox("option", "boxManager").addMsg(nickname, msg);
        }


        // chatboxManager is excerpt from the original project
        // the code is not very clean, I just want to reuse it to manage multiple chatboxes
        chatboxManager.init({messageSent : broadcastMessageCallback});

        $("#link_add").click(function(event, ui) {
            var id = receiver;

                idList.push(id);console.log("in 1")
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
</script>