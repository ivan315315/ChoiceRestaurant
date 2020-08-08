package ru.choicerestaurant;

import org.springframework.context.support.GenericXmlApplicationContext;
import ru.choicerestaurant.model.User;
import ru.choicerestaurant.repository.TestData;
import ru.choicerestaurant.web.Profiles;
import ru.choicerestaurant.web.role.RoleController;
import ru.choicerestaurant.web.user.UserAdminController;

import java.time.LocalDateTime;
import java.util.Arrays;

public class MainTest {
    public static void main(String[] args) {
        try (GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()) {
            appCtx.getEnvironment().setActiveProfiles(Profiles.getActiveDbProfile(), Profiles.REPOSITORY_IMPLEMENTATION);
            appCtx.load("spring/spring-app.xml", "spring/spring-db.xml", "spring/spring-mvc.xml");
            appCtx.refresh();
            TestUtil.mockAuthorize(TestData.getForAuth());
            System.out.println(Arrays.asList(appCtx.getBeanDefinitionNames()));
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
            //userForAdd = userAdminController.create(userForAdd); todo ficks
            userAdminController.getAll().stream().forEach(System.out::println);
            //Update
            User userForUpdate = userForAdd;
            userForUpdate.setName("testUpdate");
            userForUpdate.setRole(roleController.get(100001));
            //userAdminController.update(userForUpdate, userForUpdate.getId()); todo ficks
            userAdminController.getAll().stream().forEach(System.out::println);
            //delete
            System.out.println("Is deleted? " + userAdminController.delete(userForUpdate.getId()));
            userAdminController.getAll().stream().forEach(System.out::println);
        }
    }
}
/*
* org.springframework.beans.factory.BeanDefinitionStoreException:
* Unexpected exception parsing XML document from class path resource [spring/spring-mvc.xml];
* nested exception is org.springframework.context.annotation.ConflictingBeanDefinitionException:
* Annotation-specified bean
* name 'roleController' for bean class [ru.choicerestaurant.web.role.RoleController]
* conflicts with existing, non-compatible bean definition of same
* name and class [ru.choicerestaurant.web.RoleController]
* */