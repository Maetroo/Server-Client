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

package ru.ifmo.cs.pb.lab6.soft.client;



import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * The {@code Serializer} final class writes an object
 * to {@code byte} array.
 *
 *
 *
 * @author Bobur Zakirov
 * @see java.io.ByteArrayOutputStream
 * @see java.io.IOException
 * @see java.io.ObjectOutputStream
 * @since  1.0
 */

public final class Serializer {

    /**
     * The {@code serialize()} static method serializes an object
     *
     * @param object        any Object
     * @return              {@code byte} array
     * @throws              IOException if something wrong with input/output
     */

    public static byte[] serialize(Object object) throws IOException {

        ByteArrayOutputStream output =  new ByteArrayOutputStream();
        ObjectOutputStream stream = new ObjectOutputStream(output);
        stream.writeObject(object);

        byte[] bytes = output.toByteArray();
        output.close();
        stream.close();

        return bytes;
    }
}
