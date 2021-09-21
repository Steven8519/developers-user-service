package com.dev.social.core.developersuserservice.service;

import com.dev.social.core.developersuserservice.domain.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User saveUser(User user);
    User updateUser(User user);
    void deleteUser(UUID uuid);
    User findByUsername(String username);
    List<User> findAllUsers();
    Long numberOfUsers();
}
