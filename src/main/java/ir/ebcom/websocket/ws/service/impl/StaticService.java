package ir.ebcom.websocket.ws.service.impl;

import ir.ebcom.websocket.ws.model.UserSession;
import ir.ebcom.websocket.ws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class StaticService {
    private static UserService userService;

    @Autowired
    public StaticService(UserService userService) {
        this.userService = userService;
    }

    public static void addSession(UserSession userSession) {
        userService.addUserSession(userSession);
    }

    public static UserSession getSession(String userId) {
        return userService.getUserSession(userId);
    }

    public static Map<String, UserSession> getUserSessions(String userId) {
        return userService.getUserSessions();
    }

    public static void deleteUserSession(String userId) {
        userService.deleteUserSession(userId);
    }


}

