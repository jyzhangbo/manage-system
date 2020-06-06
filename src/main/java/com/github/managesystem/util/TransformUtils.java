package com.github.managesystem.util;

/**
 * 转换工具.
 * @Author:zhangbo
 * @Date:2019/4/14 15:09
 */
public class TransformUtils {

    /**
     * 字节数组转16进制字符串.
     * @param b
     * @return
     */
    public static String byteToHexString(byte[] b){
        String result = "";
        for (int i=0; i < b.length; i++) {
            result += Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
        }
        return result;
    }

    //  16进制字符串转换为字节数组
    public static byte[] hexStringToBytes(String str) {

        if (str == null || str.trim().equals("")) {
            return null;
        }

        if (str.length() % 2 != 0) {
            return null;
        }

        int len = str.length() / 2;
        byte[] bytes = new byte[len];

        for (int i = 0; i < len; i++) {
            String sub = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(sub, 16);
        }

        return bytes;
    }

    //  整型数值转换为十六进制字符换
    public static String integerStrToHexString(String data) {

        String str = Integer.toHexString(Integer.valueOf(data));

        if(str.length() % 2 != 0) {
            str = "0" + str;
        }

        return str;
    }


    public static byte[] intToByteArray(int i) {

        byte[] result = new byte[4];
        result[0] = (byte) ((i >> 24) & 0xFF);
        result[1] = (byte) ((i >> 16) & 0xFF);
        result[2] = (byte) ((i >> 8) & 0xFF);
        result[3] = (byte) (i & 0xFF);

        return result;
    }

    public static int byteArrayToInt(byte[] bytes) {

        int value = 0;
        for (int i = 0; i < 4; i++) {
            int shift = (3 - i) * 8;
            value += (bytes[i] & 0xFF) << shift;
        }
        return value;
    }

    public static int byteArrayToInt(byte[] bytes, int index) {

        int value = 0;
        for (int i = 0; i < 4; i++) {
            int shift = (3 - i) * 8;
            value += (bytes[i + index] & 0xFF) << shift;
        }
        return value;
    }

    public static byte[] shortoByteArray(short i) {

        byte[] result = new byte[2];
        result[0] = (byte) ((i >> 8) & 0xFF);
        result[1] = (byte) (i & 0xFF);

        return result;
    }

    public static short byteArrayToShort(byte[] bytes) {

        short value = 0;
        for (int i = 0; i < 2; i++) {
            int shift = (1 - i) * 8;
            value += (bytes[i] & 0xFF) << shift;
        }
        return value;
    }

    public static short byteArrayToShort(byte[] bytes, int index) {

        short value = 0;
        for (int i = 0; i < 2; i++) {
            int shift = (1 - i) * 8;
            value += (bytes[index + i] & 0xFF) << shift;
        }
        return value;
    }
}
