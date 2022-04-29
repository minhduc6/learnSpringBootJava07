package vn.techmaster.login.service;

import vn.techmaster.login.model.User;

import java.util.Optional;

public interface UserService {
    public User login(String email,String password);
    public boolean logout(String email);

    public User addUser(String fullname , String emaill, String password);
    public User addUserThenAutoActive(String fullname , String emaill, String password);
    public Boolean activateUser(String activation_code);

    public Boolean updatePassword(String email,String password);
    public Boolean updateEmail(String email,String newEmail);

    public Optional<User> findByEmail(String email);
    public User findById(String id);
}
