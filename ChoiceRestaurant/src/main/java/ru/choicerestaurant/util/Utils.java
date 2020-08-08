package ru.choicerestaurant.util;

import ru.choicerestaurant.model.BaseEntity;
import ru.choicerestaurant.model.Restaurant;
import ru.choicerestaurant.util.exception.UserAuthException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static javafx.scene.input.KeyCode.T;

public class Utils {
    public static final SimpleDateFormat formatterDate = new SimpleDateFormat("ddMMyyyy");
    public static List sortList(List<? extends BaseEntity> list) {
        return list.stream()
                .sorted((entity1, entity2) -> entity1.getId().compareTo(entity2.getId()))
                .collect(Collectors.toList()
                );
    }

    public static Map sortMapRestaurant() { //todo generic extends BaseEntity
        return new TreeMap<Restaurant, Integer>(new Comparator<Restaurant>() {
            @Override
            public int compare(Restaurant restaurant1, Restaurant restaurant2) {
                return Integer.compare(restaurant1.getId(), restaurant2.getId());
            }
        });
    }

    public static LocalDate stringDateToLocalDate(String dateSt) {
        return LocalDate.parse(dateSt);
    }

    public static void checkAuthUser(int idUser) {
        if (SecurityUtil.authUserId() != idUser){
            throw new UserAuthException("Logged user " + SecurityUtil.authUserId() + " != user from object " + idUser);
        }
    }
}
