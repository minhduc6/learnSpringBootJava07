package vn.techmaster.login.repository;

import org.springframework.stereotype.Repository;
import vn.techmaster.login.model.State;
import vn.techmaster.login.model.User;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class UserRepo {
    private ConcurrentMap<String , User> users = new ConcurrentHashMap<>();

    public User addUser(String fullname, String email, String hashed_password){
        return addUser(fullname, email, hashed_password, State.PENDING);
    }

    public User addUser(String fullname, String email, String hashed_password, State state){
        String id = UUID.randomUUID().toString();
        User user = User.builder()
                .id(id)
                .fullname(fullname)
                .email(email)
                .hash_password(hashed_password)
                .state(state)
                .build();
        users.put(id, user);
        return user;
    }
    public boolean isEmailExist(String email){
      return  users.values().stream().filter(user -> user.getEmail().equalsIgnoreCase(email)).count() > 0;
    }
    public Optional<User> findByEmail(String email){
        return  users.values().stream().filter(user -> user.getEmail().equalsIgnoreCase(email)).findFirst();
    }
    public void update(User user){
        users.put(user.getId(),user);
    }
}
