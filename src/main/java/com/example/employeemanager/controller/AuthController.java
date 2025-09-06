package com.example.employeemanager.controller;

import com.example.employeemanager.dto.RegisterForm;
import com.example.employeemanager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("form", new RegisterForm());
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid @ModelAttribute("form") RegisterForm form,
                             BindingResult result,
                             Model model) {
        if (!form.getPassword().equals(form.getConfirmPassword())) {
            result.rejectValue("confirmPassword", "match", "Mật khẩu xác nhận không khớp");
        }
        if (userService.usernameExists(form.getUsername())) {
            result.rejectValue("username", "exists", "Username đã tồn tại");
        }
        if (result.hasErrors()) {
            return "register";
        }
        userService.registerNewUser(form.getUsername(), form.getPassword());
        model.addAttribute("message", "Đăng ký thành công, hãy đăng nhập");
        return "login";
    }
}
