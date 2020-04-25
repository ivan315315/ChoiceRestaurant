package ru.choicerestaurant.web;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.choicerestaurant.model.User;
import ru.choicerestaurant.web.role.RoleController;
import ru.choicerestaurant.web.user.UserAdminController;

import java.time.LocalDateTime;

public class ConsoleTest {
    public static final String TIPE_DB = "MEMORY"; //{MEMORY,JDBC,JPA,DATAJPA}
/*    public static final Class REPO_CLASS;
    static {
        if (TIPE_DB.equals("MEMORY")) {
            REPO_CLASS = UserRepImplInMem.class;
        } else if (TIPE_DB.equals("JDBC")) {
            REPO_CLASS = UserRepImplJdbc.class;
        } else if (TIPE_DB.equals("JPA")) {
            REPO_CLASS = UserRepImplJpa.class;
        } else {
            REPO_CLASS = UserRepImplDatajpa.class;
        }
    }*/
    public static void main(String[] args) {
        ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        //System.out.println(Arrays.asList(appCtx.getBeanDefinitionNames()));
        UserAdminController userAdminController = appCtx.getBean(UserAdminController.class);
        RoleController roleController = appCtx.getBean(RoleController.class);

        if (TIPE_DB.equals("MEMORY")){
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


        /*--
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
        --*/

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
