package vn.techmaster.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import vn.techmaster.login.service.UserService;

@Component
public class ApplicationStartRunner implements ApplicationRunner {
    @Autowired
    private UserService userService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
           userService.addUserThenAutoActive("Admin","admin@techmaster.vn","abc123");
           userService.addUser("Duc","duc@gmail.com","123456");
    }
}
