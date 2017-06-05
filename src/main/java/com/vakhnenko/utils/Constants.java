package com.vakhnenko.utils;

public class Constants {
    public static final int FIRST_POSITION = 0;
    public static final int SECOND_POSITION = 1;

    public static final String ANALIZE_COMMAND = "ANALIZE";
    public static final String QUERY_COMMAND = "QUERY";

    public static final String QUERY_HISTORY = "HISTORY";

    public static final String ANNOTATION_SIGN = "//@";
    public static final int ANNOTATION_SIZE = ANNOTATION_SIGN.length();
    public static final String ANNOTATION_SEPARATOR = " ";

    public static final String CLASS_SIGN = "CLASS";
    public static final int CLASS_SIZE = CLASS_SIGN.length();
    public static final String CLASS_SEPARATOR = "{";

    public static final String METHOD_SIGN = "()";
    public static final int METHOD_SIZE = METHOD_SIGN.length();
    public static final String METHOD_SEPARATOR = "{";

    public static final int CLASS_TYPE = 1;
    public static final int METHOD_TYPE = 2;

    public static final String CHANGES_TABLE_NAME = "changes";
    public static final String CREATE_CHANGES_IF_NOT_EXISTS = ""
            + "create table if not exists changes ("
            + "id int not null auto_increment,"
            + "unit varchar(255) default null,"
            + "type int default null,"
            + "author varchar(150) default null,"
            + "date date default null,"
            + "reason varchar(1000) default null,"
            + "primary key (id)"
            + ")engine = innodb";

    public static final int DB_CREATE_ERROR_EXIT_CODE = -2;

    private Constants() {
    }
}
