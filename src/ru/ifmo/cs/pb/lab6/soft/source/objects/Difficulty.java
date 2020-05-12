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

package ru.ifmo.cs.pb.lab6.soft.source.objects;



import ru.ifmo.cs.pb.lab6.soft.source.exception
        .DifficultyParseException;

import java.io.Serializable;
import java.util.TreeMap;
import java.util.Map;

/**
 * The {@code Difficulty} enum is the base class of this
 * laboratory work. To current class was added new field
 * and new method.
 *
 *
 * 
 * @author Bobur Zakirov
 * @see java.io.Serializable
 * @see java.util.TreeMap
 * @see java.util.Map
 * @since  1.2
 */

public enum Difficulty implements Serializable {



    VERY_EASY, NORMAL, HARD, IMPOSSIBLE, TERRIBLE;

    /**
     * Current {@code Map<Difficulty, Integer>} field helps to
     * compare enum types
     */

    public static Map<Difficulty, Integer> getDiffMap
            = new TreeMap<Difficulty, Integer>() {
        {
            put(VERY_EASY, 1);
            put(NORMAL, 2);
            put(HARD, 3);
            put(IMPOSSIBLE, 4);
            put(TERRIBLE, 5);
        }
    };


    /**
     * The {@code parseDifficulty()} enum parses string value to
     * enum value of current class
     *
     * @param difficulty    String value of types of current enum
     * @return              Type of current enum class
     */

    public static Difficulty parseDifficulty(String difficulty) {

        switch (difficulty) {

            case "VERY_EASY":
                return VERY_EASY;
            case "NORMAL":
                return NORMAL;
            case "HARD":
                return HARD;
            case "IMPOSSIBLE":
                return IMPOSSIBLE;
            case "TERRIBLE":
                return TERRIBLE;
            default:
                throw new DifficultyParseException(difficulty);
        }
    }
}
