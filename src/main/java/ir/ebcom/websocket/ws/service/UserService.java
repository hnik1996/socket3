package ir.ebcom.websocket.ws.service;
import ir.ebcom.websocket.ws.model.UserSession;
import java.util.Map;
public interface UserService {
    void addUserSession(UserSession userSession);
    void deleteUserSession(String userId);
    UserSession getUserSession(String userId);
    Map<String, UserSession> getUserSessions();
}
