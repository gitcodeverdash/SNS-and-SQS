package com.yogesh.UserServices.repositories;


import com.yogesh.UserServices.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositories extends JpaRepository<User, String> {
}
