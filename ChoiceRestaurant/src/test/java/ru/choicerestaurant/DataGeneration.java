package ru.choicerestaurant;

import ru.choicerestaurant.model.BaseEntity;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class DataGeneration {

    private static final String changePrefix = "chg_";
    private static final Integer lengthChangePrefix = changePrefix.length();

    public static Integer getNotExistNum(List<? extends BaseEntity> list){
        return list.stream().max(Comparator.comparing(baseTo -> baseTo.getId(), Integer::compareTo)).get().getId() + 1;
    }

    public static <T> T getRandomElement(List<T> list){
        Random rand = new Random();
        return list.get(rand.nextInt(list.size() - 1));
    }

    public static String getNewString(String line){
        if (line.length() <= lengthChangePrefix) {
            return changePrefix; //todo if size db less lengthChangePrefix; if changePrefix = line.substring(0, lengthChangePrefix)
        }
        String newLine = changePrefix + line.substring(lengthChangePrefix + 1);
        return newLine;
    }

    public static Boolean changeBoolean(Boolean bool) {
        if (bool == true) {
            return false;
        }
        return true;
    }


}
