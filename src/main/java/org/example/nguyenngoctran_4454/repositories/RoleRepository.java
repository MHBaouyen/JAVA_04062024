package org.example.nguyenngoctran_4454.repositories;


import org.example.nguyenngoctran_4454.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
