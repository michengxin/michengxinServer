package org.springboot.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName MD5Util
 * @Description TODO
 * @Author mcx
 * @Date 2020/5/11 14:55
 * @Version 1.0
 */
public class MD5Util {
    private static final String CHARSET = "UTF-8";
    private static final String[] hexDigits = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public MD5Util() {
    }

    public static String generatePassword(String inputString) throws NoSuchAlgorithmException {
        return encodeByMD5(inputString);
    }

    public static boolean validatePassword(String password, String inputString) throws NoSuchAlgorithmException {
        return password.equals(encodeByMD5(inputString));
    }

    public static String encodeByMD5(String originString) {
        if (originString != null) {
            MessageDigest md = null;

            try {
                md = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException var6) {
                throw new RuntimeException(var6);
            }

            byte[] result = new byte[0];

            try {
                result = md.digest(originString.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException var5) {
                throw new RuntimeException(var5);
            }

            String resultString = byteArrayToHexString(result);
            String pass = resultString.toUpperCase();
            return pass;
        } else {
            return null;
        }
    }

    public static byte[] encodeByMD5_byte(String originString) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (originString != null) {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return md.digest(originString.getBytes("UTF-8"));
        } else {
            return null;
        }
    }

    private static String byteArrayToHexString(byte[] result) {
        StringBuffer resultSb = new StringBuffer();

        for(int i = 0; i < result.length; ++i) {
            resultSb.append(byteToHexString(result[i]));
        }

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (b < 0) {
            n = 256 + b;
        }

        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
}