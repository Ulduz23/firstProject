package firstProject.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import firstProject.Services.UserService;
import firstProject.Models.User;

@Configuration
public class AppConfig {


    @Bean
    public UserService userService(){
        UserService userService = new UserService();

         List<User> userList = new ArrayList<>();
         userList.add(new User("Ulduz"));

         userService.setUserList(userList);
         return userService;
    }

}
