package com.vakhnenko.utils;

import static com.vakhnenko.utils.Constants.*;

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

    public static String getAnntotation(String string) {
        return string.substring(string.indexOf(ANNOTATION_SIGN) + ANNOTATION_SIZE,
                string.indexOf(ANNOTATION_SEPARATOR)).trim();
    }

    public static String getClazz(String string) {
        return string.substring(string.indexOf(CLASS_SIGN) + CLASS_SIZE + 1,
                string.indexOf(CLASS_SEPARATOR)).trim();
    }

    public static String getMethod(String string) {
        return string.substring(leftSpace(string, METHOD_SIGN),
                string.indexOf(METHOD_SEPARATOR)).trim();
    }

    public static int leftSpace(String mainString, String subString) {
        int result = mainString.indexOf(subString);

        while (mainString.charAt(result) != ' ') {
            result--;
        }

        return result;
    }

    public static String shrink(String string) {
        String result = string.toUpperCase().trim();

        while (result.contains("  ")) {
            result = result.replace("  ", " ");
        }
        return result;
    }
}
