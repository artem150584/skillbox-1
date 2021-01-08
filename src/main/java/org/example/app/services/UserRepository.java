package org.example.app.services;


import org.apache.log4j.Logger;
import org.example.web.dto.LoginForm;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository implements ProjectRepositoryUser {

    private final Logger logger = Logger.getLogger(UserRepository.class);
    private final Map<String, String> repo = new HashMap<>();

    @Override
    public HashMap<String, String> reteiveAll() {
        return new HashMap<>(repo);
    }

    @Override
    public void store(LoginForm user) {
        logger.info("add new user: " + user.toString());
        repo.put(user.getUsername(), user.getPassword());
    }
}
