package ir.ebcom.websocket.ws.service;
import ir.ebcom.websocket.ws.model.UserSession;
import java.util.Map;
public interface UserService {
    void addSession(UserSession userSession);
    void removeSession(String userId);
    UserSession getSession(String userId);
    Map<String, UserSession> getActiveUsers();
}
