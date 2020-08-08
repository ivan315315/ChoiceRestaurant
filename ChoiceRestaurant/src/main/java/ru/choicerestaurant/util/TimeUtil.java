package ru.choicerestaurant.util;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TimeUtil {
    public static LocalDateTime timestampToLdt(Timestamp timestamp){
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp.getTime()), ZoneId.systemDefault());
    }
    public static LocalDateTime getToday(){
        return LocalDateTime.now();
    }
}
