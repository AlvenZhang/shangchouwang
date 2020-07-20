package com.atguigu.crowd.util;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CrowdUtil {



    public static String md5(String source){

        //1、首先判断source是否有效
        if (source != null && source.length() == 0){

            //判断不是有效字符，抛出异常
            throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);
        }


        try {
            //获取messageDigest对象
            String algorrathm = "md5";

            MessageDigest messageDigest = MessageDigest.getInstance(algorrathm);

            //获得明文字符串对应的字节数组
            byte[] input = source.getBytes();

            //执行加密
            byte[] output = messageDigest.digest(input);

            //创建bigInteger对象
            int signal = 1;
            BigInteger bigInteger = new BigInteger(signal, output);

            //将按16进制将bigInteger中的字符转换为字符串
            int radix = 16;
            String encoded = bigInteger.toString(radix).toUpperCase();

            return encoded;


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        return null;
    }


    /**
     * 判断当前请求是否为ajax请求
     * @param request
     * @return
     */
    public static Boolean judgeRequestType(HttpServletRequest request) {

        //获得请求头的头信息
        String acceptInformation = request.getHeader("Accept");
        String XRequestInformation = request.getHeader("X-Requested-With");


        return (
                    acceptInformation != null
                    &&
                    acceptInformation.length() > 0
                    &&
                    acceptInformation.contains("application/json")
                )
                ||
                (
                    XRequestInformation != null
                    &&
                    XRequestInformation.length() > 0
                    &&
                    acceptInformation.contains("XMLHttpRequest")
                );
    }
}
