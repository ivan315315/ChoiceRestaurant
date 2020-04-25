package ru.choicerestaurant;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import ru.choicerestaurant.model.User;
import ru.choicerestaurant.web.Profiles;
import ru.choicerestaurant.web.role.RoleController;
import ru.choicerestaurant.web.user.UserAdminController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        try (GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()) {
            appCtx.getEnvironment().setActiveProfiles(Profiles.getActiveDbProfile(), Profiles.REPOSITORY_IMPLEMENTATION);
            appCtx.load("spring/spring-app.xml", "spring/spring-db.xml", "spring/spring-mvc.xml");
            appCtx.refresh();
            //System.out.println(Arrays.asList(appCtx.getBeanDefinitionNames()));
            UserAdminController userAdminController = appCtx.getBean(UserAdminController.class);
            RoleController roleController = appCtx.getBean(RoleController.class);

            //getAll
            userAdminController.getAll().stream().forEach(System.out::println);
            roleController.getAll().stream().forEach(System.out::println);
            //get
            System.out.println("by id: " + userAdminController.get(100004));
            System.out.println("by id: " + roleController.get(100000));
            //Add
            User userForAdd = new User("testAdd", "testAdd@gmail.com", "testAdd", LocalDateTime.now(),
                    true, roleController.get(100000));
            userForAdd = userAdminController.create(userForAdd);
            userAdminController.getAll().stream().forEach(System.out::println);
            //Update
            User userForUpdate = userForAdd;
            userForUpdate.setName("testUpdate");
            userForUpdate.setRole(roleController.get(100001));
            userAdminController.update(userForUpdate, userForUpdate.getId());
            userAdminController.getAll().stream().forEach(System.out::println);
            //delete
            System.out.println("Is deleted? " + userAdminController.delete(userForUpdate.getId()));
            userAdminController.getAll().stream().forEach(System.out::println);
        }
    }
}
