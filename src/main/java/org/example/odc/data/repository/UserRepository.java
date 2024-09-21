package org.example.odc.data.repository;

import org.example.odc.data.entity.Role;
import org.example.odc.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
