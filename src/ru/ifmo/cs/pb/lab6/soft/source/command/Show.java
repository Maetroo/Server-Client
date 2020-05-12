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


public class Show extends AbstractCommand {


    public Show() {

        this.name = "show";
        this.description = "output  to  the  standard  output  stream" +
                           "\n                                       " +
                           "all the elements of  the collection  in a" +
                           "\n                                       " +
                           "string representation";
    }


    @Override
    public String execute(Collection collection) {

        StringBuilder builder = new StringBuilder("");

        if (collection.getCollection().size() == 0) return "Collection is empty!";

        collection.getCollection().forEach(laboratory
                -> builder.append("\n").append(laboratory.toString()));

        return builder.toString();
    }
}
