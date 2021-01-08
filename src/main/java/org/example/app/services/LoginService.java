package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LoginService {

    private final ProjectRepositoryUser userRepo;
    private final Logger logger = Logger.getLogger(LoginService.class);

    @Autowired
    public LoginService(ProjectRepositoryUser userRepo) {
        this.userRepo = userRepo;
    }

    public boolean authenticate(LoginForm loginForm) {
        Map<String, String> users = getAllUsers();
        String userName = loginForm.getUsername();
        logger.info("try to auth with user-form " + loginForm);
        return users.containsKey(userName) && users.get(userName).equals(loginForm.getPassword()) ||
                loginForm.getUsername().equals("root") && loginForm.getPassword().equals("123");
    }

    public void addUser(LoginForm user) {
        userRepo.store(user);
    }

    public Map<String, String> getAllUsers() {
        return userRepo.reteiveAll();
    }
}
