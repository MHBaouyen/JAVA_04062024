package org.example.nguyenngoctran_4454.services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.example.nguyenngoctran_4454.entities.Role;
import org.example.nguyenngoctran_4454.repositories.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleService {
    private final RoleRepository roleRepository;
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
    public Optional<Role> findById(int id) {
        return roleRepository.findById(id);
    }
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }
    public Role updateRole(@NonNull Role role) {
        Role existingRole = roleRepository.findById(role.getIdRole())
                .orElseThrow(() -> new IllegalStateException("Category with ID " +
                        role.getIdRole() + " does not exist."));
        existingRole.setNameRole(role.getNameRole());
        return roleRepository.save(existingRole);
    }
    public void deleteRole(int id) {
        if(!roleRepository.existsById(id)) {
            throw new IllegalStateException("Category with ID " + id + " does not exist.");

        }
        roleRepository.deleteById(id);
    }
}
