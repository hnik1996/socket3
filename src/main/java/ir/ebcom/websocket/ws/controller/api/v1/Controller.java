//package ir.ebcom.websocket.ws.controller.api.v1;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/v1")
//public class Controller {
//    private final RedisService redisService;
//
//    @Autowired
//    public Controller(RedisService redisService) {
//        this.redisService = redisService;
//    }
//
//    @RequestMapping(value = "/ws/{userId}/confirm", method = RequestMethod.GET)
//    public ResponseEntity<Object> confirm(@RequestBody(required = false) Map<String, Object> data,
//                                          @PathVariable("userId") String userId) {
//        redisService.findByUserId(userId, data);
//        return ResponseEntity.noContent().build();
//    }
//}
