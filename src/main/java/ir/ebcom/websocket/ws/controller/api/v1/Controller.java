package ir.ebcom.websocket.ws.controller.api.v1;


import ir.ebcom.websocket.ws.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class Controller {

    private final RedisService service;

    public Controller(RedisService service) {
        this.service = service;
    }

    @RequestMapping(value = "/ws/{userId}/confirm", method = RequestMethod.GET)
    public ResponseEntity<Object> confirm(@RequestBody(required = false) Map<String, Object> data,
                                          @PathVariable("userId") String userId) {
        service.findByUserId(userId, data);
        return ResponseEntity.noContent().build();
    }
}
