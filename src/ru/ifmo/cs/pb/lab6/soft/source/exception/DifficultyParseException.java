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

package ru.ifmo.cs.pb.lab6.soft.source.exception;



/**
 * @author Bobur Zakirov
 * @since  1.2
 */

public class DifficultyParseException extends RuntimeException {

    public DifficultyParseException() { }

    public DifficultyParseException(String input) {
        super("For input string: " + "\"" + input + "\"");
    }
}
