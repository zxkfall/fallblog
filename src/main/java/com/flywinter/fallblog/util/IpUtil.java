package com.flywinter.fallblog.util;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by IntelliJ IDEA
 * User:Zhang Xingkun
 * Date:2021/8/22 19:11
 * Description:
 */
public class IpUtil {
    /**
     * 获取ip地址
     * @author gaodongyang
     * @date 2020/8/11 14:06
     * @param request 请求的request
     * @return String ip地址
     **/
    public static String getIp(HttpServletRequest request){
        String ipAddress = request.getHeader("x-forwarded-for");
        String unknown = "unknown";
        if (ipAddress == null || ipAddress.length() == 0 || unknown.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || unknown.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || unknown.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            String benji = "127.0.0.1";
            String bj = "0:0:0:0:0:0:0:1";
            if (benji.equals(ipAddress) || bj.equals(ipAddress)) {
                ///根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                if(inet != null){
                    ipAddress = inet.getHostAddress();
                }
            }
        }
        ///对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        int i = 15;
        String s = ",";
        if (ipAddress != null && ipAddress.length() > i) {
            if (ipAddress.indexOf(s) > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }
}
