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

package ru.ifmo.cs.pb.lab6.soft.source;



import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * The {@code Deserializer} final class reads an object
 * from the {@code byte} array.
 *
 *
 *
 * @author Bobur Zakirov
 * @see java.io.ByteArrayInputStream
 * @see java.io.IOException
 * @see java.io.ObjectInputStream
 * @since  1.0
 */

public final class Deserializer {

    /**
     * The {@code deserialize} static method deserializes to the object
     *
     * @param bytes         {@code byte} array
     * @return              an Object
     * @throws              IOException if something wrong with input/output
     * @throws              ClassNotFoundException if class does not exists
     */

    public static Object deserialize(byte[] bytes)
            throws IOException, ClassNotFoundException {

        ByteArrayInputStream input = new ByteArrayInputStream(bytes);
        ObjectInputStream stream = new ObjectInputStream(input);

        Object object = stream.readObject();
        input.close();
        stream.close();

        return object;
    }
}
