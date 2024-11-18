package com.example.crud.service;

import java.util.HashMap;
import java.util.Map;  // Correct import

import org.springframework.stereotype.Service;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class LoginService {

    private static final int MAX_DEVICES = 2;
    @SuppressWarnings("FieldMayBeFinal")
    private Map<String, String> activeLogins = new HashMap<>();  // In-memory storage for active sessions

    // Handle login
    public String loginUser(String userName, HttpServletResponse request) {  // Correct parameter type
        String ipAddress = ((ServletRequest) request).getRemoteAddr();  // Now accessing IP correctly
        String userAgent = request.getHeader("User-Agent");
        String deviceId = ipAddress + "-" + userAgent;

        if (activeLogins.containsKey(userName) && activeLogins.size() >= MAX_DEVICES) {
            return "Login failed. Only two devices can be logged in at a time.";
        }

        // Add device to active logins map
        activeLogins.put(userName, deviceId);
        return "Login successful for user: " + userName + " on device ID: " + deviceId;
    }

    // Handle logout
    public String logoutUser(String userName) {
        if (activeLogins.containsKey(userName)) {
            activeLogins.remove(userName);
            return "Logged out successfully for user: " + userName;
        } else {
            return "No active session found for user: " + userName;
        }
    }
}
