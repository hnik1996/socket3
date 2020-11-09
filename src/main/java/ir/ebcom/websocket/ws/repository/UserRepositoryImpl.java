package ir.ebcom.websocket.ws.repository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.ebcom.websocket.ws.model.UserSession;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import javax.websocket.Session;
import java.util.Map;
@Repository
public class UserRepositoryImpl {
    Jedis jedis = new Jedis("localhost",6379,false);


    final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
    HashOperations<String, String, UserSession> hashOperations =template.opsForHash();
    private static final String KEY = "UserSession";

//    public UserRepositoryImpl() {
//        jedisConnectionFactory.setHostName("localhost");
//        jedisConnectionFactory.setPort(6379);
//        System.out.println("HAAAAAAAAAAAAAAAAAAA"+jedisConnectionFactory.getClientName());
//        template.setConnectionFactory(jedisConnectionFactory);
//        template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
//    }
    public void save(UserSession userSession) {
        jedis.select(0);
        jedis.sadd(userSession.getUserId(), userSession.getSession().toString());
//        hashOperations.put(KEY, userSession.getUserId(), userSession);
    }

    public UserSession find(String id) {
        jedis.select(0);
        String s = jedis.get(id);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            UserSession session = objectMapper.readValue(s, UserSession.class);
            return session;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
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
