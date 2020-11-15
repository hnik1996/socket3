package ir.ebcom.websocket.ws.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.ebcom.websocket.ws.model.UserSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;

@Repository
public class UserRepositoryImpl {
    private String hostname;
    private int port;
    private String password;
    private int timeout;
    private int database;
    private boolean ssl;

    public UserRepositoryImpl(@Value("${ws.redis.hostname}") String hostname,
                              @Value("${ws.redis.port}") int port,
                              @Value("${ws.redis.password}") String password,
                              @Value("${ws.redis.timeout}") int timeout,
                              @Value("${ws.redis.database}") int database,
                              @Value("${ws.redis.ssl}") boolean ssl) {
        this.hostname = hostname;
        this.port = port;
        this.password = password;
        this.timeout = timeout;
        this.database = database;
        this.ssl = ssl;
    }

//    JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), hostname, port, 0, null);
//    Jedis jedis = jedisPool.getResource();

    final RedisTemplate<String, Object> template = new RedisTemplate<>();
    HashOperations<String, String, UserSession> hashOperations = template.opsForHash();
    private static final String KEY = "UserSession";

    public void saveUserSession(UserSession userSession) {
        Jedis jedis = new Jedis(hostname, port, ssl);
        jedis.select(0);
        JsonNode jsonNode = new ObjectMapper().valueToTree(userSession.getSession());
        jedis.sadd(KEY + userSession.getUserId(), jsonNode.asText());
    }

    public UserSession findUserSession(String id) {
        Jedis jedis = new Jedis(hostname, port, ssl);
        jedis.select(0);
        String s = jedis.get(KEY+id);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            UserSession session = objectMapper.readValue(s, UserSession.class);
            return session;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, UserSession> findAllUserSessions() {
        return hashOperations.entries(KEY);
    }

    public void updateUserSession(UserSession userSession) {
        hashOperations.put(KEY, userSession.getUserId(), userSession);
    }

    public void deleteUserSession(String id) {
        hashOperations.delete(KEY, id);
    }

}
