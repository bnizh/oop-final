package WebSockets;

/**
 * Created by Boris on 28.06.2016.
 */

import DataBase.DBFactory;
import Objects.Item;
import Objects.ObjectFactory;
import Objects.Transaction;

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
                String userName = message.substring(0, message.indexOf("$"));
                for(Session ses : conns) {
                    String itemID = message.substring(message.indexOf("$")+1, message.indexOf("#"));
                    String buyerID = message.substring(message.indexOf("@")+1);
                    String amount = message.substring(message.indexOf("#")+1, message.indexOf("@"));
                    if(amount.charAt(0)!='0') {
                        Item it = DBFactory.getDBConnection().getItemById(Integer.parseInt(itemID));
                        Transaction tr = ObjectFactory.getTransaction(it.getOwnerID(), Integer.parseInt(buyerID), it.getID(), Integer.parseInt(amount));
                        if (sessionHashMap.containsKey(ses) && sessionHashMap.get(ses).equals(userName)) {
                            ses.getBasicRemote().sendText("New Offer");
                        }
                        for (Session ses1 : conns) {
                            if (sessionHashMap.containsKey(ses1) && sessionHashMap.get(ses1).equals(buyerID)) {
                                ses1.getBasicRemote().sendText("Your code is " + tr.getId() + " save this!!!");
                            }
                        }
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
    }

    @OnError
    public void onError(Session session,
                        Throwable throwable) {
        System.out.println(session+" "+ throwable);
    }
}

