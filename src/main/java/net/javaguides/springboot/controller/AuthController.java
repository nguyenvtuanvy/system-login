package net.javaguides.springboot.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class AuthController {

    private UserService userService;
    // Request home Page
    @GetMapping("/home")
    public String home(){
        return "index";
    }

    // Request login form
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    // Request register form
    @GetMapping("/register")
    public String showRegisterForm(Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "register";
    }

    // Request submit form registration
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,BindingResult result,Model model){
        User exitingUser = userService.findUserByEmail(userDto.getEmail());

        if (exitingUser != null && exitingUser.getEmail() != null && !exitingUser.getEmail().isEmpty()){
            result.rejectValue("email", null, "There is already an account registered with the same email");
        }

        if (result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    // Request getAllUsers
    @GetMapping("/admin")
    public String users(Model model){
        List<UserDto> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}
