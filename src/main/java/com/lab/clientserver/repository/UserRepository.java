package com.lab.clientserver.repository;

import com.lab.clientserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Spring Data JPA автоматично надає нам методи для CRUD операцій
    // і тип її ідентифікатора - Long.
}