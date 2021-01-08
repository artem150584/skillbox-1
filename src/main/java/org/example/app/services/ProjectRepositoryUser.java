package org.example.app.services;

import org.example.web.dto.LoginForm;

import java.util.HashMap;

public interface ProjectRepositoryUser {
    HashMap<String, String> reteiveAll();

    void store(LoginForm user);
}
