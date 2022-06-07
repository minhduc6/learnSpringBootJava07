package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "USERS")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;

    private String name;

    private String address;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = false)
    @JoinColumn(name = "user_id")
    private List<Account> accounts = new ArrayList<>();

    public void addAccount(Account account) {
        account.setUser(this);
        accounts.add(account);
    }

    public void removeAccount(Account account) {
        account.setUser(null);
        accounts.remove(account);
    }
}
