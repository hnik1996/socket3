//package ir.ebcom.websocket.ws.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.redis.core.RedisHash;
//import org.springframework.data.redis.core.TimeToLive;
//
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@RedisHash("InputData")
//public class InputData {
//    @Id
//    private String id;
//    private String action;
//    private Map<String, Object> data;
//    private long ts;
//    private String requestId;
//    @TimeToLive(unit = TimeUnit.SECONDS)
//    private Long expiration;
//}
