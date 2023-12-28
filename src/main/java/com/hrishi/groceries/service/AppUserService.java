package com.hrishi.groceries.service;

import com.hrishi.groceries.model.AppUser;
import com.hrishi.groceries.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AppUserService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;
    public AppUser save(AppUser appUser) {
        appUser.setPassword(encoder.encode(appUser.getPassword()));
        return repository.save(appUser);
    }
    public AppUser getUserById(Integer id) {
        return repository.findById(id).orElseThrow();
    }
    public void deleteUser(Integer id) {
        repository.deleteById(id);
    }
    public List<AppUser> getAllUsers() {
        return repository.findAll();
    }
}
