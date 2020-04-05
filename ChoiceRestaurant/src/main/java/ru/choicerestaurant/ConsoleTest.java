package ru.choicerestaurant;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.choicerestaurant.model.Role;
import ru.choicerestaurant.model.User;
import ru.choicerestaurant.repository.RoleRep;
import ru.choicerestaurant.repository.jdbc.RoleRepImplJdbc;
import ru.choicerestaurant.repository.jpa.RoleRepImplJpa;
import ru.choicerestaurant.service.UserServ;
import ru.choicerestaurant.service.UserServImpl;
import ru.choicerestaurant.web.UserAdminController;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.util.Arrays;

public class ConsoleTest {
    public static void main(String[] args) {
        ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        System.out.println(Arrays.asList(appCtx.getBeanDefinitionNames()));
        UserServ userServ = appCtx.getBean(UserServImpl.class);
        UserAdminController userAdminController = new UserAdminController(userServ);

        //__DATAJPA test__/
        //getAll
        userAdminController.getAll().stream().forEach(System.out::println);
        //get
        System.out.println("by id: " + userAdminController.get(100004));
        //Add
        RoleRep roleRep = appCtx.getBean(RoleRepImplJpa.class);
        Role roleForAdd = roleRep.getAll().stream().filter(role -> role.getId() == 100000).findFirst().get();
        User userForAdd = new User("testAdd", "testAdd@gmail.com", "testAdd", LocalDateTime.now(), true, roleForAdd);
        System.out.println("user add: " + userAdminController.save(userForAdd));
        //Update
        User userForUpdate = new User(100000,"testUpdate", "testAdd@gmail.com", "testAdd", LocalDateTime.now(), true, roleForAdd);
        System.out.println("user update: " + userAdminController.save(userForUpdate));
        //delete
        System.out.println("Is deleted? " + userAdminController.delete(100000));
        //__DATAJPA test__//

        /*--
        //__JPA test__/
        //getAll
        userAdminController.getAll().stream().forEach(System.out::println);
        //get
        System.out.println("by id: " + userAdminController.get(100004));
        //Add
        RoleRep roleRep = appCtx.getBean(RoleRepImplJpa.class);
        Role roleForAdd = roleRep.getAll().stream().filter(role -> role.getId() == 100000).findFirst().get();
        User userForAdd = new User("testAdd", "testAdd@gmail.com", "testAdd", LocalDateTime.now(), true, roleForAdd);
        System.out.println("user add: " + userAdminController.save(userForAdd));
        //Update
        User userForUpdate = new User(100000,"testUpdate", "testAdd@gmail.com", "testAdd", LocalDateTime.now(), true, roleForAdd);
        //System.out.println("user update: " + userAdminController.save(userForUpdate));
        //delete
        System.out.println("Is deleted? " + userAdminController.delete(100000));
        //__JPA test__//
        --*/

        /*--
        //__GDBC test__/
        UserAdminController userAdminController = appCtx.getBean(UserAdminController.class);
        //getAll
        userAdminController.getAll().stream().forEach(System.out::println);
        //get
        System.out.println(userAdminController.get(100004));
        //Add
        RoleRep roleRepImplJdbc = appCtx.getBean(RoleRepImplJdbc.class);
        Role roleForAdd = roleRepImplJdbc.getAll().stream().filter(role -> role.getId() == 100000).findFirst().get();
        User userForAdd = new User("testAdd", "testAdd@gmail.com", "testAdd", LocalDateTime.now(), true, roleForAdd);
        System.out.println(userAdminController.save(userForAdd));
        //Update
        User userForUpdate = new User(100000,"testUpdate", "testAdd@gmail.com", "testAdd", LocalDateTime.now(), true, roleForAdd);
        System.out.println(userAdminController.save(userForUpdate));
        //delete
        System.out.println(userAdminController.delete(100001));
        //__GDBC test__//
        --*/



    }
}
