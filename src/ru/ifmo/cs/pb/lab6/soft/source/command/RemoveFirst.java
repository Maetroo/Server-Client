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


public class RemoveFirst extends AbstractCommand {


    public RemoveFirst() {

        this.name = "remove_first";
        this.description = "remove  the   first  element   from   the" +
                           "\n                                       " +
                           "collection";
    }


    @Override
    public String execute(Collection collection) {

        if (collection.getCollection().size() == 0)
            return "Collection is already clear!";

        collection.getCollection().removeFirst();
        collection.updateInitializationDate();

        return "Successfully removed the first item of the collection!";
    }
}
