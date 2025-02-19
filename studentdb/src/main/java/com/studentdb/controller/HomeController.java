package com.studentdb.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.studentdb.entity.User;
import com.studentdb.service.UserService;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "home"; // This should be the name of your HTML file (user-list.html)
    }

    @GetMapping("/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user"; // This should be the name of your HTML file (add-user.html)
    }

    @PostMapping("/add")
    public String createUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/"; // Redirect to the user list page
    }

    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "edit-user"; // This should be the name of your HTML file (edit-user.html)
        } else {
            return "redirect:/"; // Redirect to the user list page if user not found
        }
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute User user) {
        userService.updateUser(id, user);
        return "redirect:/"; // Redirect to the user list page
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/"; // Redirect to the user list page
    }
}