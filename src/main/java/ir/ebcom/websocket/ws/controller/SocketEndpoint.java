package ir.ebcom.websocket.ws.controller;
import ir.ebcom.websocket.ws.model.UserSession;
import ir.ebcom.websocket.ws.service.impl.StaticService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@Slf4j
@ServerEndpoint("/ws")
public class SocketEndpoint {

    @OnOpen
    public void onOpen(Session session) {
    }

    @OnMessage
    public void onMessage(String message, Session session) {
 /*       try {
            JSONObject jsonObject;
            try {
                jsonObject = new JSONObject(message);
            } catch (Exception e) {
                onClose(session);
                return;
            }*/

//            if (jsonObject.has("action")) {
//                String action = jsonObject.get("action").toString();
//                switch (action) {
//                    case "token":
//                        //TODO must call esb api if success save on redis with time to live 120
//
//                        break;
//                    case "login":
//                        break;
//                    default:
//                        break;
//
//                }
//            }

            UserSession userSession = new UserSession(session.getId(), session);
            StaticService.addSession(userSession);
/*            UserSession userId = StaticService.getSession("userId");
            session.getBasicRemote().sendText("Hi " + jsonObject.get("user") + " how may we help you?");*/
/*        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @OnClose
    public void onClose(Session session) {
    }

    @OnError
    public void onError(Throwable t) {
        log.error(t.getMessage());
    }
}
