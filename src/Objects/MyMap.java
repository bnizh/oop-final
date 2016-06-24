package Objects;

import javax.websocket.Session;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Boris on 24.06.2016.
 */
public class MyMap extends ConcurrentHashMap <Session,String> {
    private static MyMap ourInstance ;

    public static MyMap getInstance() {
        if(ourInstance==null)
            ourInstance= new MyMap();
        return ourInstance;
    }

    private MyMap() {
        super();
    }
}
