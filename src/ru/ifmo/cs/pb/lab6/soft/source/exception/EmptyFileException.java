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

import java.io.IOException;



/**
 * @author Bobur Zakirov
 * @see java.io.IOException
 * @since  1.2
 */

public class EmptyFileException extends IOException {

    public EmptyFileException() { }

    public EmptyFileException(String message) {
        super(message);
    }
}
