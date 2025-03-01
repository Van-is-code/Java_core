package com.studentdb.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.studentdb.entity.User;
import com.studentdb.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setAge(user.getAge());
            userRepository.save(existingUser);
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}