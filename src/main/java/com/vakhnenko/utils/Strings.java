package com.vakhnenko.utils;

public class Strings {
    public static String swq(String str) { // Strings With Quotes
        return "'" + str + "' ";
    }

    public static String getFileExtension(String name) {
        try {
            return name.substring(name.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
    }
}
