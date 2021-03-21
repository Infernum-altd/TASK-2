package com.altynnikov.TASK_2.services;

import com.altynnikov.TASK_2.UserSession;

public class UserService {
    private static UserSession userSession;

    public UserService() {
        userSession = new UserSession();
    }

    public static UserSession getUserSession() {
        return userSession;
    }

    public static void setUserSession(UserSession userSession) {
        UserService.userSession = userSession;
    }
}
