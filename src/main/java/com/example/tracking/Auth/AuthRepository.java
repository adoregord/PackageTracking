package com.example.tracking.Auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Auth, Long>{
    
    public Optional<Auth> findByUsername(String username);

    public boolean existsByUsername(String username);

}
