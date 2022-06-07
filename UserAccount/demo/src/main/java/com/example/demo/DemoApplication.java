package com.example.demo;

import com.example.demo.entity.Account;
import com.example.demo.entity.User;
import com.example.demo.repository.AcountRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AcountRepository acountRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Account account = new Account("1","Bob123","123",100L);
        Account account5 = new Account("5","Bob456","123",500L);
        Account account1 = new Account("2","Alice123","123",200L);
        Account account2 = new Account("3","Tom123","123",300L);
        Account account3 = new Account("4","Sara123","123",500L);





        List<Account> accountsBob = new ArrayList<>();
        accountsBob.add(account);
        accountsBob.add(account5);
        acountRepository.saveAll(accountsBob);
        User user1 = new User("1","Bob","London",accountsBob);
        userRepository.save(user1);

        List<Account> accountsAlice = new ArrayList<>();
        accountsAlice.add(account1);
        acountRepository.saveAll(accountsAlice);
        User user2 = new User("2","Alice","London",accountsAlice);
        userRepository.save(user2);

        List<Account> accountsTom = new ArrayList<>();
        accountsTom.add(account2);
        acountRepository.saveAll(accountsTom);
        User user3 = new User("3","Tom","London",accountsTom);
        userRepository.save(user3);

        List<Account> accountsSara = new ArrayList<>();
        accountsSara.add(account3);
        acountRepository.saveAll(accountsSara);
        User user4 = new User("4","Sara","London",accountsSara);
        userRepository.save(user4);
    }
}
