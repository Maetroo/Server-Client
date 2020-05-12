/*
 * Copyright (c) 2020, Saint-Petersburg, ITMO University, P3130
 * Laboratory work #6, Server-Client Application(Single-Threaded mode)
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package ru.ifmo.cs.pb.lab6.soft.source.parser;

/**
 * The {@code CSVDelimiter} enum contains types of delimiters of .csv file
 *
 *
 *
 * @author Bobur Zakirov
 * @since  1.2
 */

public enum CSVDelimiter {

    COMMA, PIPE, COLON, SEMICOLON, SPACE;

    /**
     * The {@code getDelimiter} method gets a String value of a delimiter
     *
     * @param delimiter     type of delimiter
     * @return              String value of the delimiter
     */

    public static String getDelimiter(CSVDelimiter delimiter) {

        switch (delimiter) {
            case PIPE:
                return "|";
            case COLON:
                return ":";
            case SEMICOLON:
                return ";";
            case SPACE:
                return " ";
            default:
                return ",";
        }
    }
}
