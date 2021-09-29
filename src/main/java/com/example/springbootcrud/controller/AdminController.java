package com.example.springbootcrud.controller;

import com.example.springbootcrud.model.Role;
import com.example.springbootcrud.model.User;
import com.example.springbootcrud.service.interfaces.RoleService;
import com.example.springbootcrud.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
public class AdminController{

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/admin")
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "/admin/user-list";
    }

    @GetMapping("/admin/user-create")
    public String createUserForm(User user) {
        return "/admin/user-create";
    }

    @PostMapping("/admin/user-create")
    public String createUser(User user) {
        Set<Role> setRoles = new HashSet<>();
        setRoles.add(roleService.getByName("ROLE_USER"));
        User temp = new User(
                user.getLogin(), user.getPassword(),
                user.getName(), user.getSurname(),
                user.getAge(), user.getEmail(),
                setRoles);

        userService.saveUser(temp);
        return "redirect:/admin/";
    }

    @GetMapping("/admin/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin/";
    }

    @GetMapping("/admin/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        Set<Role> setOfAllRoles = roleService.findAll();
        model.addAttribute("user", user);
        model.addAttribute("setOfAllRoles", setOfAllRoles);
        model.addAttribute("roles", new ArrayList<>());
        return "admin/user-update";
    }


    @PostMapping("/admin/user-update/")
    public String updateUser(User user, long id,@ModelAttribute("userRoles") ArrayList<Role> userRoles) {
        Set<Role> roles = new HashSet<>();
        for (Role role : user.getRoles()) {
            roles.add(roleService.getByName(role.getRole()));
        }
        user.setRoles(roles);
        userService.update(user.getId(), user);

        return "redirect:/admin/";
    }

}