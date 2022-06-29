package com.example.batdongsan.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import com.example.batdongsan.entity.Role;
import com.example.batdongsan.entity.User;
import com.example.batdongsan.repository.RoleRepository;
import com.example.batdongsan.repository.UserRepository;
import com.example.batdongsan.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

    public static final int USER_PER_PAGE = 4;

    @Autowired
    private UserRepository repo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> listAll()
    {
        return (List<User>) repo.findAll();
    }

    public List<Role> listRole()
    {
        return (List<Role>) roleRepo.findAll();
    }
    public User getByEmail(String email)
    {
        return repo.getUserByEmail(email);
    }

    public Page<User> listByPage(int pageNumber ,String sortField , String sortDir,String keyword)
    {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending()  : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, USER_PER_PAGE,sort);
        if(keyword != null)
        {
            return repo.findAll(keyword, pageable);
        }
        return repo.findAll(pageable);
    }

    public User saveUser(User user)
    {
        boolean  isUpdatingUser  = (user.getId() != null);
        if(isUpdatingUser)
        {
            User existingUser = repo.findById(user.getId()).get();
            if(user.getPassword().isEmpty())
            {
                user.setPassword(existingUser.getPassword());
            }else
            {
                encodePassword(user);
            }
        }
        else
        {
            encodePassword(user);
        }
        return repo.save(user);
    }
    public User updateAccount(User userInForm) {
        User userInDB = repo.findById(userInForm.getId()).get();

        if (!userInForm.getPassword().isEmpty()) {
            userInDB.setPassword(userInForm.getPassword());
            encodePassword(userInDB);
        }

        if (userInForm.getPhotos() != null) {
            userInDB.setPhotos(userInForm.getPhotos());
        }

        userInDB.setFirstName(userInForm.getFirstName());
        userInDB.setLastName(userInForm.getLastName());

        return repo.save(userInDB);
    }
    public void encodePassword(User user)
    {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }
    public boolean isEmailUnique(Integer id,String email)
    {
        User userByEmail = repo.getUserByEmail(email);
        if(userByEmail == null) return true;

        boolean isCreatingNew = (id == null);
        if(isCreatingNew)
        {
            if(userByEmail != null) return false;
        }
        else
        {
            if(userByEmail.getId() != id)
            {
                return false;
            }
        }

        return true;
    }

    public User getUserById(Integer id) throws NotFoundException
    {
        try {
            return repo.findById(id).get();
        } catch (NoSuchElementException ex) {
            // TODO: handle exception
            throw new NotFoundException("Could not find any user with ID" + id);
        }
    }


    public void delete(Integer id) throws NotFoundException
    {
        Long countById = repo.countById(id);
        if(countById == null || countById == 0)
        {
            throw new NotFoundException("Could not find any user with ID" + id);
        }

        repo.deleteById(id);
    }

    public void updateUserEnabledStatus(Integer id ,boolean enabled)
    {
        repo.updateEnabledStatus(id, enabled);
    }

}

