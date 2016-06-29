package WebSockets;

/**
 * Created by Boris on 28.06.2016.
 */

import Objects.ObjectFactory;

import javax.websocket.server.ServerEndpoint;
import javax.websocket.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@ServerEndpoint("/Transaction")
public class TransactionWebSocket {
    private Map< Session,String> sessionHashMap = ObjectFactory.getMap();
    private Session curSess;
    private static Set<Session> conns = java.util.Collections.synchronizedSet(new HashSet<Session>());
    @OnOpen
    public void onOpen(Session session) throws IOException {
        this.curSess = session;
        conns.add(curSess);
        session.getBasicRemote()
                .sendText("");
    }

    @OnMessage
    public void onMessage(String message) {
        if(!sessionHashMap.containsKey(curSess)){
            sessionHashMap.put(curSess,message);
        }else{
            try {
                String userName = message;
                for(Session ses : conns) {
                    if (sessionHashMap.containsKey(ses)&&sessionHashMap.get(ses).equals(userName) ) {
                        ses.getBasicRemote().sendText("New Offer");
                    }
                }
            } catch (IOException e) {
                System.out.println("Error while sending message message");
            }
        }

    }

    @OnClose
    public void onClose(Session session,
                        CloseReason reason) {
        sessionHashMap.remove(session);
        System.out.println("Session : " + session+ " "+reason );
    }

    @OnError
    public void onError(Session session,
                        Throwable throwable) {
        System.out.println(session+" "+ throwable);
    }
}

