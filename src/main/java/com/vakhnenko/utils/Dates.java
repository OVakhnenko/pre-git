package com.vakhnenko.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dates {

    public static Date yyyyMMdd2String(String string) {
        Date result;

        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy.MM.dd");

        try {
            result = format.parse(string);
        } catch (ParseException pe) {
            result = null;
        }

        return result;
    }
}
