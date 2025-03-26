package com.example.demo.infrastructure.repositorys;

import com.example.demo.infrastructure.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
