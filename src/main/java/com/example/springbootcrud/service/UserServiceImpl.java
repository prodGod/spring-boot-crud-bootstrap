package com.example.springbootcrud.service;

import com.example.springbootcrud.dao.interfaces.RoleDao;
import com.example.springbootcrud.dao.interfaces.UserDao;
import com.example.springbootcrud.model.User;
import com.example.springbootcrud.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(RoleDao roleDao, UserDao userDao, PasswordEncoder passwordEncoder) {
        this.roleDao = roleDao;
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.saveUser(user);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findById(long id) {
        return userDao.findById(id);
    }

    @Override
    public void update(long id, User user){
        userDao.update(id, user);
    }
    @Override
    public void update(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.update(user);
    }

    @Override
    public void deleteById(long id) {
        userDao.deleteById(id);
    }

    @Override
    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }
}
