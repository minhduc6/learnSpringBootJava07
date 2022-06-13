package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.TradeException;
import com.example.demo.repository.AcountRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Trade {
    @Autowired
    private AcountRepository acountRepository;


    public void transfer(String accountSendId,String accountReceiveid,long amount){
        Optional<Account> accountSendOptional = acountRepository.findById(accountSendId);
        Account accountSend = new Account();
        if(accountSendOptional.isPresent()){
            accountSend  = accountSendOptional.get();
        }
        else {
            throw new NotFoundException("Account with id = " + accountSendId + " not found");
        }
        Account accountReceive = new Account();
        Optional<Account> accountRecvOptional = acountRepository.findById(accountReceiveid);
        if(accountRecvOptional.isPresent()){
            accountReceive = accountRecvOptional.get();
        }
        else {
            throw new NotFoundException("Account with id = " + accountReceiveid + " not found");
        }

        long monneyAccountSend = accountSend.getBalance();
        long monneyAccountRecv = accountReceive.getBalance();

        if(monneyAccountSend < 0){
            throw new TradeException("Tiền người gửi nhỏ hơn 0");
        }
        if(monneyAccountSend < amount){
            throw new TradeException("Số dư của bạn không đủ thực hiện được giao dịch");
        }
        if(amount < 0){
            throw new TradeException("Số tiền gửi phải lớn 0");
        }
        accountSend.setBalance(monneyAccountSend - amount);
        accountReceive.setBalance(monneyAccountRecv + amount);
        acountRepository.save(accountSend);
        acountRepository.save(accountReceive);
    }

    public void deposit(String accountId , long amount){
        Optional<Account> accountSendOptional = acountRepository.findById(accountId);
        Account account = new Account();
        if(accountSendOptional.isPresent()){
            account  = accountSendOptional.get();
        }
        else {
            throw new NotFoundException("Account with id = " + accountId + " not found");
        }

        long monneyAccountCurrent = account.getBalance();

        if(amount < 0){
            throw new TradeException("Số tiền Nạp phải lớn 0");
        }
        account.setBalance(monneyAccountCurrent + amount);
        acountRepository.save(account);
    }

    public void withDraw(String accountId , long amount){
        Optional<Account> accountSendOptional = acountRepository.findById(accountId);
        Account account = new Account();
        if(accountSendOptional.isPresent()){
            account  = accountSendOptional.get();
        }
        else {
            throw new NotFoundException("Account with id = " + accountId + " not found");
        }

        long monneyAccountCurrent = account.getBalance();

        if(amount < 0){
            throw new TradeException("Số Rút Nạp phải lớn 0");
        }
        account.setBalance(monneyAccountCurrent - amount);
        acountRepository.save(account);
    }
}
