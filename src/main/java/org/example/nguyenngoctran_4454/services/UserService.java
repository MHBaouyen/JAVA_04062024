package org.example.nguyenngoctran_4454.services;


import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.example.nguyenngoctran_4454.entities.User;
import org.example.nguyenngoctran_4454.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    public List<User> findAll() {
        return userRepository.findAll();
    }
    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }
    public User addUser(User user) {
        return userRepository.save(user);
    }
    public User updateUser(@NotNull User user) {
        User updatedUser = userRepository.findById(user.getIduser()).orElseThrow(() -> new IllegalStateException("Product with ID " +
                user.getIduser() + " does not exist."));
        updatedUser.setUsername(user.getUsername());
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setRole(user.getRole());
        updatedUser.setBirthDay(user.getBirthDay());
        updatedUser.setDelete(user.isDelete());
        return userRepository.save(updatedUser);
    }
    public void deleteUser(int id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalStateException("User with id " + id + " does not exist.");
        }
        userRepository.deleteById(id);
    }


}
