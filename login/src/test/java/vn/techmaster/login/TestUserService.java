package vn.techmaster.login;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.techmaster.login.exception.UserException;
import vn.techmaster.login.model.User;
import vn.techmaster.login.service.UserService;
import  static  org.assertj.core.api.Assertions.*;

@SpringBootTest
public class TestUserService {
    @Autowired
    private UserService userService;

    @Test
    public void login_when_account_is_pending(){
        userService.addUser("Duc","duc@gmail.com","123");
        Assertions.assertThatThrownBy(() ->{
            userService.login("duc@gmail.com","123");
        }).isInstanceOf(UserException.class).hasMessageContaining("User is not active");
    }

    @Test
    public void login_when_account_is_not_found(){
        Assertions.assertThatThrownBy(() ->{
            userService.login("duc0611@gmail.com","123");
        }).isInstanceOf(UserException.class).hasMessageContaining("User is not found");
    }


    @Test
    public void login_when_password_is_incorrect(){
        userService.addUserThenAutoActive("Duc","duc@gmail.com","123");
        Assertions.assertThatThrownBy(() ->{
            userService.login("duc@gmail.com","1234");
        }).isInstanceOf(UserException.class).hasMessageContaining("Password is incorrect");
    }

    @Test
    public void login_success(){
        userService.addUserThenAutoActive("Duc","duc@gmail.com","123");
        User user = userService.login("duc@gmail.com","123");
        assertThat(user).isNotNull();

    }
}
