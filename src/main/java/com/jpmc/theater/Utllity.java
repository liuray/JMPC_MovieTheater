package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class Utllity {
    public static String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

        return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }
    //Time Fornater
    public static String timeFomater(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
        String formatDateTime = localDateTime.format(formatter);
        return formatDateTime;
    } 
    // (s) postfix should be added to handle plural correctly
    public static String handlePlural(long value) {
        if (value == 1) {
            return "";
        }
        else {
            return "s";
        }
    }
    // roundUp in 2 digits 
    public static double roundUp(double num){
        return Math.round(num * 100) /100.0;
    }
}
