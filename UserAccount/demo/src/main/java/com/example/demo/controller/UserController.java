package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.entity.User;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.AcountRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.Trade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AcountRepository acountRepository;

    @Autowired
    private Trade trade;

    @GetMapping("user/{id}")
    @Operation(summary = "Find User By ID")
    public ResponseEntity<?> userById(@Parameter(description = "id of user to be searched") @PathVariable(name = "id") String id){
        Optional<User> userOptional = userRepository.findById(id);
        User user = new User();
        if(userOptional.isPresent()){
             user = userOptional.get();
        }
        else {
            throw new NotFoundException("User with id = " + id + " not found");
        }
        return  ResponseEntity.ok(user);
    }

    @GetMapping("account/{id}")
    @Operation(summary = "Find Account By ID")
    public ResponseEntity<?> accountById(@Parameter(description = "id of account to be searched") @PathVariable(name = "id") String id){
        Optional<Account> accountOptional = acountRepository.findById(id);
        Account account = new Account();
        if(accountOptional.isPresent()){
            account = accountOptional.get();
        }
        else {
            throw new NotFoundException("Account with id = " + id + " not found");
        }
        String user_id = account.getUser().getId();

        return  ResponseEntity.ok(account);
    }

    @Operation(summary = "Find Account By UserId")
    @GetMapping("accountByUserId/{id}")
    public ResponseEntity<?> accountByUserId(@Parameter(description = "id_user of account to be searched") @PathVariable(name = "id") String id){
        Optional<User> userOptional = userRepository.findById(id);
        User user = new User();
        if(userOptional.isPresent()){
            user = userOptional.get();
        }
        else {
            throw new NotFoundException("Account with id = " + id + " not found");
        }
        List<Account> accountList = acountRepository.findAllByUser(user);
        return  ResponseEntity.ok(accountList);
    }

    @Operation(summary = "Transfer Money")
    @PostMapping ("tranfer")
    public ResponseEntity<?> tranfer(@Parameter(description = "id_account of account send") @RequestParam String accSend , @Parameter(description = "id_account of account recv") @RequestParam String accRecv,@Parameter(description = "Tiền gửi")  @RequestParam long amount){
        trade.transfer(accSend,accRecv,amount);
        return  ResponseEntity.ok("Chuyển tiền thành công");
    }

    @PostMapping ("deposit")
    public ResponseEntity<?> deposit (@Parameter(description = "id_account ") @RequestParam String acccountId,@Parameter(description = "Tiền nạp")  @RequestParam long amount){
        trade.deposit(acccountId,amount);
        return  ResponseEntity.ok("Nạp tiền thành công");
    }

    @PostMapping ("withDraw")
    public ResponseEntity<?> withDraw (@Parameter(description = "id_account ") @RequestParam String acccountId,@Parameter(description = "Tiền nạp")  @RequestParam long amount){
        trade.withDraw(acccountId,amount);
        return  ResponseEntity.ok("Rút tiền thành công");
    }

}
