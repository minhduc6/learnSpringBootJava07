package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account {
    @Id
    private String id;

    public Account(String id, String username, String password, long balance) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    private String  username;

    private String password;

    private long balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;


}
