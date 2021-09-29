package com.example.springbootcrud.service.interfaces;



import com.example.springbootcrud.model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);
    List<User> findAll();
    User findById(long id);
    void update(long id, User user);
    void update(User user);
    void deleteById(long id);
    User findByLogin(String login);

}
