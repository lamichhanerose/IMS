package com.example.imsmanagemnt.web;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.imsmanagemnt.user.UserRegistrationDto;
import com.example.imsmanagemnt.user.UserService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(UserService userService, PasswordEncoder passwordEncoder) {
        super();
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto, RedirectAttributes redirectAttributes) {

        String encryptedPassword = passwordEncoder.encode(registrationDto.getPassword());
        registrationDto.setPassword(encryptedPassword);

        try {
            userService.
                save(registrationDto);
            redirectAttributes.addAttribute("success", true);
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addAttribute("error", true);
        }

        return "redirect:/registration";
    }

}