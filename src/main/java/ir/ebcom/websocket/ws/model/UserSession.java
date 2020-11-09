package ir.ebcom.websocket.ws.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import javax.websocket.Session;


@RedisHash("UserSession")
public class UserSession {
    @Id
    private String userId;
    private Session session;

    public UserSession(String userId, Session session) {
        this.userId = userId;
        this.session = session;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
