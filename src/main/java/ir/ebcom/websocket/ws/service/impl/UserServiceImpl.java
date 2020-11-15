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

    public void addUserSession(UserSession userSession) {
        userRepository.saveUserSession(userSession);
    }

    public void deleteUserSession(String userId) {
        userRepository.deleteUserSession(userId);
    }

    public UserSession getUserSession(String userId) {
        return userRepository.findUserSession(userId);
    }

    @Override
    public Map<String, UserSession> getUserSessions() {
        return userRepository.findAllUserSessions();
    }

}
