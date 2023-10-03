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


        Car car1 = new Car("reno", 5);
        Car car2 = new Car("ford", 2);
        Car car3 = new Car("mesedes", 600);
        Car car4 = new Car("toyota", 1);


        User user1 = new User("Олег", "Первый", "user1@mail.ru", car1);
        User user2 = new User("Иван", "Иванов", "user2@mail.ru", car2);
        User user3 = new User("Дмитрий", "Третий", "user3@mail.ru", car3);
        User user4 = new User("Анатолий", "Четвертый", "user4@mail.ru", car4);

        // Сохранение пользователей и машин в базе данных
        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar().getSeries());
            System.out.println("Car = " + user.getCar().getModel());
            System.out.println();
        }
        User user = userService.getUserByCarModelAndSeries("mesedes", 600);
        System.out.println(user.toString());

        context.close();
    }
}
