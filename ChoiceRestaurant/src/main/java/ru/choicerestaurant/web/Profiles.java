package ru.choicerestaurant.web;

public class Profiles {
    public static final String JPA = "jpa";

    public static final String REPOSITORY_IMPLEMENTATION = JPA;

    public static final String HSQL_DB = "hsqldb";

    //  Get DB profile depending of DB driver in classpath
    public static String getActiveDbProfile() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            return Profiles.HSQL_DB;
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Could not find DB driver");
        }
    }
}
