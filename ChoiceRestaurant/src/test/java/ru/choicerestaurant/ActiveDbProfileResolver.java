package ru.choicerestaurant;

import org.springframework.test.context.ActiveProfilesResolver;
import ru.choicerestaurant.web.Profiles;

//http://stackoverflow.com/questions/23871255/spring-profiles-simple-example-of-activeprofilesresolver
public class ActiveDbProfileResolver implements ActiveProfilesResolver {

    @Override
    public String[] resolve(Class<?> aClass) {
        return new String[]{Profiles.getActiveDbProfile()};
    }
}