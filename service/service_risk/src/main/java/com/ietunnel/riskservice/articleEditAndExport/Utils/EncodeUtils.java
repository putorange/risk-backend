package com.ietunnel.riskservice.articleEditAndExport.Utils;


import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

    /**
     * 浏览器编码工具
     */
    public class EncodeUtils {

        public static final String USER_AGENT = "User-Agent";
        public static final String ENCODE_UTF_8 = "UTF-8";
        public static final String ENCODE_ISO_8859_1 = "ISO8859-1";
        public static final String FIREFOX = "firefox";
        public static final String MSIE = "MSIE";
        public static final String CHROME = "CHROME";
        /**
         * 根据浏览器类型转换字符串编码（解决不同浏览器下载文件时，文件名乱码）
         * @param fileName
         * @param request
         * @return
         */
        public static String getStringByResponse(String fileName, HttpServletRequest request){
            try{
                if (request.getHeader(USER_AGENT).toLowerCase().indexOf(FIREFOX) > 0) {
                    // firefox浏览器
                    fileName = new String(fileName.getBytes(ENCODE_UTF_8), ENCODE_ISO_8859_1);
                } else if (request.getHeader(USER_AGENT).toUpperCase().indexOf(MSIE) > 0) {
                    // IE浏览器
                    fileName = URLEncoder.encode(fileName, ENCODE_UTF_8);
                }else if (request.getHeader(USER_AGENT).toUpperCase().indexOf(CHROME) > 0) {
                    // 谷歌浏览器
                    fileName = new String(fileName.getBytes(ENCODE_UTF_8), ENCODE_ISO_8859_1);
                }
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
            return fileName;
        }

    }
