package com.ua.util;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class DateParser {

    private final SimpleDateFormat DATA_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Timestamp parseToTimestamp(String timestamp) {
        try {
            return new Timestamp(DATA_TIME_FORMAT.parse(timestamp).getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
