package Objects;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Boris on 27.06.2016.
 */
public class unactivedMap  extends ConcurrentHashMap<String, String> {
        private static unactivedMap ourInstance ;

        public static unactivedMap getInstance() {
            if(ourInstance==null)
                ourInstance= new unactivedMap();
            return ourInstance;
        }

        private unactivedMap() {
            super();
        }

    }
