package Objects;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Boris on 27.06.2016.
 */
public class unactivatedMap extends ConcurrentHashMap<String, String> {
        private static unactivatedMap ourInstance ;

        public static unactivatedMap getInstance() {
            if(ourInstance==null)
                ourInstance= new unactivatedMap();
            return ourInstance;
        }

        private unactivatedMap() {
            super();
        }

    }
