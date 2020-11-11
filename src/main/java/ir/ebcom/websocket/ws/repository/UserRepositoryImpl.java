package ir.ebcom.websocket.ws.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.ebcom.websocket.ws.model.UserSession;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserRepositoryImpl {

    private final RedisTemplate<String, Object> template;
    // TODO @Hosein: I dont know whats below line
    private HashOperations<String, String, UserSession> hashOperations;
    private static final String KEY = "UserSession";

    public UserRepositoryImpl(RedisTemplate<String, Object> template) {
        this.template = template;
        hashOperations = template.opsForHash();
    }

    public void save(UserSession userSession) {
        template.opsForList().leftPush(KEY+userSession.getUserId(),userSession);
    }

    public UserSession find(String id) {
        // TODO @Hosein here do casting. this is wrong
        return (UserSession) template.opsForList().leftPop(KEY+id);
    }

    public Map<String, UserSession> findAll() {
        return hashOperations.entries(KEY);
    }

    public void update(UserSession userSession) {
        hashOperations.put(KEY, userSession.getUserId(), userSession);
    }

    public void delete(String id) {
        hashOperations.delete(KEY, id);
    }
}
