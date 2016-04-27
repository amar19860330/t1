package com.t1.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Created by User on 2016/4/27.
 */
public class Encryption {

    /**
     * md5 32位
     *
     * @param str
     * @return
     */
    public static String MD5(String str) {
        String reStr = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(str.getBytes());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : bytes) {
                int bt = b & 0xff;
                if (bt < 16) {
                    stringBuffer.append(0);
                }
                stringBuffer.append(Integer.toHexString(bt));
            }
            reStr = stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return reStr;
    }

    public static String toBase64(String content) {
        byte base64Bytes[] = Base64.getEncoder().encode(content.getBytes());
        return new String(base64Bytes);
    }
    public static String fromBase64(String content) {
        byte base64Bytes[] = Base64.getDecoder().decode( content.getBytes());
        return new String(base64Bytes);
    }



    public static void main(String[] args) {
        System.out.println(MD5("123456"));
        System.out.println(toBase64("ssada111lll你好啊xxx2"));
        System.out.println(fromBase64(toBase64("ssada111lll你好啊xxx2")));


    }
}
