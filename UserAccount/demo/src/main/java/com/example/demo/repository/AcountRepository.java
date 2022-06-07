package com.example.demo.repository;

import com.example.demo.entity.Account;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AcountRepository extends JpaRepository<Account,String> {
    List<Account> findAllByUser(User user);
}
