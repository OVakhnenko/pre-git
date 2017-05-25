package com.vakhnenko.utils;

public class Strings {
    public static String swq(String str) { // Strings With Quotes
        return "'" + str + "' ";
    }

    public static String makeStringLength(String str, int length) {
        StringBuffer buffer = new StringBuffer(str);
        buffer.setLength(length);
        return ("" + buffer);
    }

    public static String shrink(String command) {
        String result = command.toUpperCase().trim();

        while (result.contains("  ")) {
            result = result.replace("  ", " ");
        }
        return result;
    }

    public static String getStringFromManyWords(String[] commands, int cindex) {
        String result = "";

        if (cindex == -1) {
            return result;
        }
        if ((cindex + 1 < commands.length) && (!commands[cindex + 1].contains("-"))) {
            result = commands[cindex + 1] + " " + getStringFromManyWords(commands, cindex + 1);
        }
        return result.trim();
    }
}
