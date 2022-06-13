package vn.techmaster.bank;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import vn.techmaster.bank.exception.NotFoundException;
import vn.techmaster.bank.model.Account;
import vn.techmaster.bank.model.Bank;
import vn.techmaster.bank.model.User;
import vn.techmaster.bank.repository.AccountRepo;
import vn.techmaster.bank.repository.BankRepo;
import vn.techmaster.bank.repository.UserRepo;

@Component
public class AppRunner implements CommandLineRunner{
  @Autowired private UserRepo userRepo;
  @Autowired private AccountRepo accountRepo;
  @Autowired private BankRepo bankRepo;

  @Override
  public void run(String... args) throws Exception {
    generateSomeAccount();
  }

  private void generateSomeAccount() {
      Bank bankVCB = bankRepo.findById("vcb").orElseThrow(() ->  new NotFoundException("No data!"));
      Bank bankACB = bankRepo.findById("abc").orElseThrow(() ->  new NotFoundException("No data!"));
      User userBob = userRepo.findById("4").orElseThrow(() ->  new NotFoundException("No data!"));
      User userAlice = userRepo.findById("1").orElseThrow(() ->  new NotFoundException("No data!"));

      Account bob_vcb_1 = new Account("001", bankVCB,userBob,100L,true);
      Account alice_acb_1 = new Account("002", bankACB,userAlice,100L,true);

      accountRepo.save(bob_vcb_1);
      accountRepo.save(alice_acb_1);

  }

}
