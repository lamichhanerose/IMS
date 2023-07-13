package com.example.imsmanagemnt.forgetPassword;

import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.imsmanagemnt.user.User;
import com.example.imsmanagemnt.user.UserRepository;

@Controller
public class ForgetPasswordController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/passwordReset")
    public String showForgetPasswordPage() {
        return "password";
    }

    @PostMapping("/passwordReset")
    public String processForgetPassword(@RequestParam("email") String email, Model model) {
        User user = userRepository.findByEmail(email);

        if (user != null) {
            String resetCode = generateResetCode();
            user.setResetCode(resetCode);
            userRepository.save(user);
            model.addAttribute("message", "We have sent a reset password link to your database. Please check.");
        } else {
            model.addAttribute("error", "Email address not found or invalid.");
        }

        return "password";
    }
    private String generateResetCode() {
        Random random = new Random();
        int code = random.nextInt(9000) + 1000;
        return String.valueOf(code);
    }
    @GetMapping("/changePassword")
    public String showResetPasswordPage() {
        return "reset";
    }

    @PostMapping("/changePassword")
    public String resetPassword(
        @RequestParam("email") String email,
        @RequestParam("password") String password,
        @RequestParam("confirmPassword") String confirmPassword,
        Model model) {
        User user = userRepository.findByEmail(email);

        if (user != null && user.getEmail().equals(email)) {
            if (password.equals(confirmPassword)) {
                String encryptedPassword = passwordEncoder.encode(password);
                user.setPassword(encryptedPassword);
                user.setResetCode(null);
                userRepository.save(user);
                model.addAttribute("success", "Password has been successfully changed.");
            } else {
                model.addAttribute("error1", "Passwords do not match.");
            }
        } else {
            model.addAttribute("error", "Invalid verification code.");
        }

        return "reset";
    }

}
