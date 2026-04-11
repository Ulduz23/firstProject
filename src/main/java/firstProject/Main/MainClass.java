package firstProject.Main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import firstProject.Services.UserService;
import firstProject.config.AppConfig;
import firstProject.Models.User;

public class MainClass {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =  new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService =  context.getBean(UserService.class);
        
        for(User user :  userService.getUserList()) {
            System.out.println(user);

        }
    }
}
