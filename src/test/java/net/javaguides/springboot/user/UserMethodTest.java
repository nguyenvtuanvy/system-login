package net.javaguides.springboot.user;

import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMethodTest {
    @Autowired
    private UserService userService;

    @Test
    void saveUser(){
        UserDto userDto = new UserDto();
        userDto.setFirstName("van");
        userDto.setLastName("An");
        userDto.setEmail("an@gmail.com");
        userDto.setPassword("12345");
        userService.saveUser(userDto);
    }


    @Test
    void findbyemail(){
        String email = "minh@gmail.com";
        User user = userService.findUserByEmail(email);
        System.out.println(user.getId());
        System.out.println(user.getName());
        System.out.println(user.getEmail());
        System.out.println(user.getRoles().get(0).getName());
        System.out.println(user.getRoles().get(1).getName());
    }
}
