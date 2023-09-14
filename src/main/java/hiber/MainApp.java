package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar().getSeries());
         System.out.println("Car = "+user.getCar().getModel());
         System.out.println();
      }
      User newUser = userService.getUserByCarModelAndSeries("Tesla", 3);
      System.out.println(newUser.getId());
      System.out.println(userService.getUserByCarModelAndSeries("Tesla", 3).getCar().getModel());

      context.close();
   }
}
