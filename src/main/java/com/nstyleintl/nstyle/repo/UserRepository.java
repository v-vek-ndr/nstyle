package com.nstyleintl.nstyle.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nstyleintl.nstyle.model.User;

public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository {
    
	Optional<User> findByEmail(String email);
}
