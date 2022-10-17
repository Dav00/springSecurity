package org.davo.security.service;

import org.davo.security.identity.Role;
import org.davo.security.identity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser (String username, String roleName);
    User getUser(String username);
    List<User> getAllUsers();
}
