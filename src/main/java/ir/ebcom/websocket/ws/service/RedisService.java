package ir.ebcom.websocket.ws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RedisService {

    private final RedisTemplate<String, Object> template;

    public RedisService(RedisTemplate<String, Object> template) {
        this.template = template;
    }

    public Object findByUserId(String userId, Map<String,Object> data){
        // template.doSomething();
        return  null;
    }

}
