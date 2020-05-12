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



import ru.ifmo.cs.pb.lab6.soft.source.command.*;

import java.util.*;

/**
 * The final {@code Commander} class help to analysis commands,
 * which was read from Console and get it by name.
 *
 *
 *
 * @author Bobur Zakirov
 * @see java.util
 * @since 1.2
 */

public final class Commander {

    /**
     * Map of all available commands
     */

    public static Map<String, AbstractCommand> commands
            = new TreeMap<String, AbstractCommand>() {

        {
            put(new Add());
            put(new AddIfMin());
            put(new Clear());
            put(new FilterGreaterDifficulty());
            put(new FilterLessDiscipline());
            put(new Help());
            put(new Info());
            put(new PrintMinimalPoint());
            put(new RemoveByID());
            put(new RemoveFirst());
            put(new RemoveLower());
            put(new Script());
            put(new Show());
            put(new UpdateID());
        }

        /**
         * The {@code put(AbstractCommand command)} method puts
         * a command to map with it's name
         *
         * @param command
         */
        
        private void put(AbstractCommand command) {
            put(command.getName(), command);
        }
    };

    /**
     * Getter
     * @return              array list of all available commands
     */

    public static ArrayList<AbstractCommand> getAvailableCommands() {
        return new ArrayList<AbstractCommand>(commands.values());
    }
}