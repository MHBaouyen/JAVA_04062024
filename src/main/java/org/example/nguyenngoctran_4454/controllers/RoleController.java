package org.example.nguyenngoctran_4454.controllers;

import jakarta.validation.Valid;
import org.example.nguyenngoctran_4454.entities.Role;

import org.example.nguyenngoctran_4454.services.RoleService;
import org.example.nguyenngoctran_4454.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/role")// đươờng dẫn cha
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @GetMapping("")
    public String showRoleList(Model model) {
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);// Đảm bảo tên "roles" khớp với tên trong template
        return "/roles/list";// đường dẫn của resoure/roles/name.html
    }
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("role", new Role());
        return "/roles/add";
    }

    @PostMapping("/add")
    public String addRole(@Valid Role role, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "/roles/add";
        }
        roleService.addRole(role);
        return "redirect:/role";// quay lại trang chủ cha

    }
    @GetMapping("/update/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Role role = roleService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
            model.addAttribute("role", role);
            return "roles/update";
    }
    @PostMapping("/update/{id}")
    public String updateRole(@PathVariable Integer id, @Valid Role role, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {

            return "/roles/update";
        }
        role.setIdRole(id);
        roleService.updateRole(role);
        return "redirect:/role";
    }
    @GetMapping("/delete/{id}")
    public String deleteRole(@PathVariable Integer id) {
        roleService.deleteRole(id);
        return "redirect:/role";
    }

}
