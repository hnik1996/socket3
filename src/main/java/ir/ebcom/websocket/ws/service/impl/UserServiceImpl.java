package ir.ebcom.websocket.ws.service.impl;

import ir.ebcom.websocket.ws.model.UserSession;
import ir.ebcom.websocket.ws.repository.UserRepositoryImpl;
import ir.ebcom.websocket.ws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepositoryImpl userRepository;

    @Autowired
    public UserServiceImpl(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    public void addSession(UserSession userSession) {
        userRepository.save(userSession);
        System.out.println("*****************" + userSession.getSession().getId() + "*******************");
    }

    public void removeSession(String userId) {
        userRepository.delete(userId);
    }

    public UserSession getSession(String userId) {
        return userRepository.find(userId);
    }

    @Override
    public Map<String, UserSession> getActiveUsers() {
        return null;
    }

}
