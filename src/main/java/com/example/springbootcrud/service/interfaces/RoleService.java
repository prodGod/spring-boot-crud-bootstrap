package com.example.springbootcrud.service.interfaces;



import com.example.springbootcrud.model.Role;

import java.util.Set;

public interface RoleService {
    boolean add(Role role);
    Role getById(Long id);
    Role getByName(String name);
    boolean update(Role role);
    Set<Role> findAll();
}