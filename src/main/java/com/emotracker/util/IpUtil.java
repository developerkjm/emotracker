package com.emotracker.util;


import jakarta.servlet.http.HttpServletRequest;

public class IpUtil {

    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isBlank()) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String maskIp(String ip) {
        // 예: 192.168.0.25 → 192.168.*.*
        String masked_ip = "unknown ip";
        if (ip == null) masked_ip = "unknown";
        String[] parts = ip.split("\\.");
        if (parts.length == 4) {
            masked_ip = parts[0] + "." + parts[1] + ".*.*";
            // return parts[0] + "." + parts[1] + ".*.*";
        }
        return masked_ip;
    }
}

