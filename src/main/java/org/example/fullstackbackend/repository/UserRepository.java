package org.example.fullstackbackend.repository;

import org.example.fullstackbackend.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
