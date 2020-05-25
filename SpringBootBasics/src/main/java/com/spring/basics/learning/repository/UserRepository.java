package com.spring.basics.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.basics.learning.jpa.User;

// 1st Arg of repository is entity class and 2nd one is primary column data type.
public interface UserRepository extends JpaRepository<User, Long>{

}
