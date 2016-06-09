package Objects;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashCreator {
    private static HashCreator instance = null;

    public HashCreator() {}

    public static HashCreator getInstance() {
        instance = new HashCreator();
        return instance;
    }

    public static String getHash(String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA");
        md.update(text.getBytes());
        byte[] byteData = md.digest();
        return hexToString(byteData);
    }
    private static String hexToString(byte[] bytes) {
        StringBuilder buff = new StringBuilder();
        for (byte aByte : bytes) {
            int val = aByte;
            val = val & 0xff;  // remove higher bits, sign
            if (val < 16) buff.append('0'); // leading 0
            buff.append(Integer.toString(val, 16));
        }
        return buff.toString();
    }
}
