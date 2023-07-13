package com.example.imsmanagemnt.resetPassword;

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
public class PasswordController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/resetPassword")
    public String showResetPasswordPage() {
        return "reset-password";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestParam("email") String email,
        @RequestParam("oldPassword") String oldPassword,
        @RequestParam("newPassword") String newPassword,
        @RequestParam("confirmPassword") String confirmPassword,
        Model model) {

        User user = userRepository.findByEmail(email);
        if (user == null) {
            model.addAttribute("error", "Email incorrect");
            return "reset-password";
        }

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {

            model.addAttribute("error", "Incorrect old password");
            return "reset-password";
        }

        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "New password and confirm password do not match");
            return "reset-password";
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        model.addAttribute("success", "Password changed successfully");
        return "reset-password";
    }
}
