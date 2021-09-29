package com.example.springbootcrud.service;

import com.example.springbootcrud.dao.interfaces.UserDao;
import com.example.springbootcrud.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDao userDao;

    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    // «Пользователь» – это просто Object. В большинстве случаев он может быть
    //  приведен к классу UserDetails.
    // Для создания UserDetails используется интерфейс UserDetailsService, с единственным методом:
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.findByLogin(s);
        if(user == null){
            throw new UsernameNotFoundException("user not found");
        }
        return user;
    }
}