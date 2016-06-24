package chat;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by Boris on 24.06.2016.
 */
@ServerEndpoint("/Chat")
public class ChatSocket {
    private Map< String,Session> sessionHashMap = new ConcurrentHashMap< String,Session>();
    private Session curSess;
    @OnOpen
    public void onOpen(Session session) throws IOException {
        this.curSess = session;
        session.getBasicRemote()
                .sendText("");
    }

    @OnMessage
    public void onMessage(String message) {
       if(!sessionHashMap.containsValue(curSess)){
           sessionHashMap.put(message, curSess);
       }else{
           String userName = message.substring(0,message.indexOf("$"));
           Session ses = sessionHashMap.get(userName);
           try {
               ses.getBasicRemote().sendText(message.substring(message.indexOf("$")+1));
           } catch (IOException e) {
               System.out.println("Error while sending message message");
           }
       }

    }

    @OnClose
    public void onClose(Session session,
                        CloseReason reason) {

    }

    @OnError
    public void onError(Session session,
                        Throwable throwable) {
        //Handle error during transport here
    }
}
