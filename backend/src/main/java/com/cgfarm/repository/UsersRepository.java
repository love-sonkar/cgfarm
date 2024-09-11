package com.cgfarm.repository;

import com.cgfarm.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository()
public interface UsersRepository extends JpaRepository<Users, Integer> {

    Users findByEmail(String email);
    Users findByName(String name);

    Users findByContact(String contact);
}
