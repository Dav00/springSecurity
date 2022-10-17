package org.davo.security.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.davo.security.identity.Role;
import org.davo.security.identity.User;
import org.davo.security.repository.RoleRepository;
import org.davo.security.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public User saveUser(User user) {
        log.info("UserServiceImpl.saveUser");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("UserServiceImpl.saveRole");
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("UserServiceImpl.addRoleToUser");
        var user = userRepository.findByUsername(username);
        var role = roleRepository.findByName(roleName);
        log.info("Adding: " + role.getName() + " role to " + user.getName());
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        log.info("UserServiceImpl.getUser");
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        log.info("UserServiceImpl.getAllUsers");
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);
        if(user == null){
            log.error("UserServiceImpl.loadUserByUsername()  User not found in DDBB. ");
            throw new UsernameNotFoundException("User not found in DDBB");
        }
        else{
            log.info("User found in DDBB. " + username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
