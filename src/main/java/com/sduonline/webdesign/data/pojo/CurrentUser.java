package com.sduonline.webdesign.data.pojo;

public class CurrentUser {
    public static final ThreadLocal<String> USERNAME = new ThreadLocal<>();
    public static final ThreadLocal<String> USER_ID = new ThreadLocal<>();

    public static void setUSERId(String userId) {
        USER_ID.set(userId);
    }

    public static String getUSERId() {
        return USER_ID.get();
    }

}