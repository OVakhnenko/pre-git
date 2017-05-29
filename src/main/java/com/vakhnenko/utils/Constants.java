package com.vakhnenko.utils;

public class Constants {
    public static final int FIRST_POSITION = 0;
    public static final int SECOND_POSITION = 1;

    public static final String ANALIZE_COMMAND = "ANALIZE";

    public static final String ANNOTATION_SIGN = "//@";
    public static final int ANNOTATION_SIZE = ANNOTATION_SIGN.length();
    public static final String ANNOTATION_SEPARATOR = " ";

    public static final String CLASS_SIGN = "CLASS";
    public static final int CLASS_SIZE = CLASS_SIGN.length();
    public static final String CLASS_SEPARATOR = "{";

    public static final String METHOD_SIGN = "()";
    public static final int METHOD_SIZE = METHOD_SIGN.length();
    public static final String METHOD_SEPARATOR = "{";

    private Constants() {
    }
}
