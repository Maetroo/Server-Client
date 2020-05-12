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

package ru.ifmo.cs.pb.lab6.soft.source.command;


import ru.ifmo.cs.pb.lab6.soft.source.Collection;


public class Info extends AbstractCommand {


    public Info() {

        this.name = "info";
        this.description = "display information about  the collection" +
                           "\n                                       " +
                           "(type,  initialization  date,  number  of" +
                           "\n                                       " +
                           "elements, etc.) in  the  standard  output" +
                           "\n                                       " +
                           "stream";
    }


    @Override
    public String execute(Collection collection) {

        StringBuilder builder = new StringBuilder();

        builder.append(String.format("%-36s - %s\n", "type of collection",
                collection.getCollection().getClass().getName()));
        builder.append(String.format("%-36s - %s\n", "number of elements",
                collection.getCollection().size()));
        builder.append(String.format("%-36s - %s", "initialization date",
                collection.getInitializationDate()));

        return builder.toString();
    }
}
