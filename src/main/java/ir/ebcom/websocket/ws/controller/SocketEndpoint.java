package ir.ebcom.websocket.ws.controller;

import ir.ebcom.websocket.ws.model.UserSession;
import ir.ebcom.websocket.ws.service.impl.StaticService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@Slf4j
@ServerEndpoint("/ws")
public class SocketEndpoint {
    private String ESB_API;

    public void setESB_API(@Value("${ws.esb.api}") String ESB_API) {
        this.ESB_API = ESB_API;
    }


    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            JSONObject jsonObject;
            try {
                jsonObject = new JSONObject(message);
            } catch (Exception e) {
                onClose(session);
                return;
            }

//            URL url = new URL(ESB_API);


            if (jsonObject.has("action")) {
                String action = jsonObject.get("action").toString();
                switch (action) {
                    case "token":
                        //TODO must call esb api if success save on redis with time to live 120
                        // if data was unauthorized connection must closed
                        onClose(session);
                        //if data accepted connection must close after 120 seconds
                        break;
                    case "login":
                        break;
                    case "transport":

                        break;
                    default:
                        onClose(session);
                        break;

                }
            }

            UserSession userSession = new UserSession(session.getId(), session);
            StaticService.addSession(userSession);
            UserSession userId = StaticService.getSession("userId");
            session.getBasicRemote().sendText("Hi " + jsonObject.get("user") + " how may we help you?");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session) {

    }

    @OnError
    public void onError(Throwable t) {
        log.error(t.getMessage());
    }
}
