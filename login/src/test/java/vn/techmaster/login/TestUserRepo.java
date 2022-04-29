package vn.techmaster.login;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import vn.techmaster.login.model.State;
import vn.techmaster.login.model.User;
import vn.techmaster.login.repository.UserRepo;

import  static  org.assertj.core.api.Assertions.*;

//@SpringBootTest
public class TestUserRepo {
    @Test
    public void addUser(){
        UserRepo userRepo = new UserRepo();
        User user = userRepo.addUser("Duc" ,"duc0611@gmail.com","123", State.PENDING);
        assertThat(user).isNotNull();
        System.out.println(user.getId());
    }

    @Test
    public void isEmailExist(){
        UserRepo userRepo = new UserRepo();
        userRepo.addUser("Duc" ,"duc0611@gmail.com","123", State.PENDING);
        userRepo.addUser("Thao" ,"thao2203@gmail.com","123", State.PENDING);
        userRepo.addUser("Tru" ,"tri1007@gmail.com","123", State.PENDING);
        assertThat(userRepo.isEmailExist("duc0611@gmail.com")).isTrue();
        assertThat(userRepo.isEmailExist("Thao2203@gmail.com")).isTrue();
        assertThat(userRepo.isEmailExist("thao2203@gmail.com")).isTrue();
        assertThat(userRepo.isEmailExist("tao2203@gmail.com")).isFalse();
    }

    @Test
    public void findByEmail(){
        UserRepo userRepo = new UserRepo();
        userRepo.addUser("Duc" ,"duc0611@gmail.com","123", State.PENDING);
        userRepo.addUser("Thao" ,"thao2203@gmail.com","123", State.PENDING);
        userRepo.addUser("Tru" ,"tri1007@gmail.com","123", State.PENDING);
        assertThat(userRepo.findByEmail("duc0611@gmail.com")).isPresent();
    }
}
