package com.ha.haspringbootrestfulwebservicesdemoapp.repository;

import com.ha.haspringbootrestfulwebservicesdemoapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
