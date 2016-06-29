package Objects;

import java.util.Random;

/**
 * Created by Boris on 28.06.2016.
 */
public class MyRandom {
    private static MyRandom ourInstance ;
    private Random ran;
    private String st = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
    public static MyRandom getInstance() {
        if(ourInstance==null)
            ourInstance = new MyRandom();
        return ourInstance;
    }

    public String getRandomString(){
        String answer ="";
        for(int i =0; i<8; i++){
            answer+=st.charAt(ran.nextInt(st.length()));
        }
        return answer;
    }

    private MyRandom() {
        ran = new Random();
    }
}
