package org.example.nguyenngoctran_4454.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.nguyenngoctran_4454.entities.Role;
import org.example.nguyenngoctran_4454.entities.User;
import org.example.nguyenngoctran_4454.repositories.UserRepository;
import org.example.nguyenngoctran_4454.services.RoleService;
import org.example.nguyenngoctran_4454.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("")
    public String showUserList(Model model) {
        model.addAttribute("users", userService.findAll());

        return "users/list";
    }
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.findAll()); // Lấy danh sách vai trò từ cơ sở dữ liệu
        return "users/add";
    }
    @PostMapping("/add")
    public String addUser(@Valid User user, BindingResult bindingResult, @RequestParam("roleID") Integer roleID) {
        if (bindingResult.hasErrors()) {
            return "users/add";
        }

        // Set the role object to the user
        userService.addUser(user);
        return "redirect:/user";
    }
    @GetMapping("/update/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        User user = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id:"
                        + id));
        model.addAttribute("user", user);
        return "users/update";
    }
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") int id, @Valid User user, BindingResult bindingResult,  Model model) {
        if (bindingResult.hasErrors()) {
            user.setIduser(id);
            return "redirect:/user";
        }
        userService.updateUser(user);
        model.addAttribute("user", userService.findAll());
        return "redirect:/user";
    }
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model) {
        User user = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id:"
                        + id));
        userService.deleteUser(id);
        model.addAttribute("user", userService.findAll());
        return "redirect:/user";
    }


}
