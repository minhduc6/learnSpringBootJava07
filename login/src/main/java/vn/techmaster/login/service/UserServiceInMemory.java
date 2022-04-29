package vn.techmaster.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.techmaster.login.exception.UserException;
import vn.techmaster.login.hash.Hashing;
import vn.techmaster.login.model.State;
import vn.techmaster.login.model.User;
import vn.techmaster.login.repository.UserRepo;

import java.util.Optional;

@Service
public class UserServiceInMemory implements UserService{

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private Hashing hash;

    @Override
    public User login(String email, String password) {
        // Kiem tra user co ton tai k
       Optional<User> o_user = userRepo.findByEmail(email);
       if(!o_user.isPresent()){
           throw  new UserException("User is not found");
       }

       // Muon dang nhap user phai active
       User user = o_user.get();
       if(user.getState() != State.ACTIVE){
           throw  new UserException("User is not actived");
       }
       // Kiem tra password
       if(hash.validatePassword(password,o_user.get().getHash_password())){
            return  o_user.get();
       } else {
           throw new UserException("Password is incorrect");
       }
    }

    @Override
    public boolean logout(String email) {
        return false;
    }

    @Override
    public User addUser(String fullname, String emaill, String password) {
        return userRepo.addUser(fullname,emaill,hash.hashPassword(password),State.PENDING);
    }

    @Override
    public User addUserThenAutoActive(String fullname, String emaill, String password) {
        return userRepo.addUser(fullname,emaill,hash.hashPassword(password),State.ACTIVE);
    }

    @Override
    public Boolean activateUser(String activation_code) {
        return null;
    }

    @Override
    public Boolean updatePassword(String email, String password) {
        return null;
    }

    @Override
    public Boolean updateEmail(String email, String newEmail) {
        return null;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public User findById(String id) {
        return null;
    }
}
