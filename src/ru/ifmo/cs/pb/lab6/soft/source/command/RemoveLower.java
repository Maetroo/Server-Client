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
import ru.ifmo.cs.pb.lab6.soft.source.objects.Laboratory;


public class RemoveLower extends AbstractCommand {


    public RemoveLower() {

        this.name = "remove_lower";
        this.description = "remove from  the collection  all elements" +
                           "\n                                       " +
                           "smaller   than   the   specified";
        this.needInput = true;
    }


    @Override
    public String execute(Collection collection) {

        Laboratory laboratory = (Laboratory) this.object;

        int size;
        if (laboratory == null) return "Command failed!";
        if ((size = collection.getCollection().size()) == 0)
            return "Collection is empty!";

        for (int i = collection.getCollection().size() - 1; i >= 0; i --)
            if (laboratory.compareTo(collection.getCollection().get(i)) >= 0)
                collection.getCollection().remove(i);

        if (size == collection.getCollection().size())
            return "Could not find item less than the added!";

        collection.updateInitializationDate();

        return "Successfully removed all items less than the added!";
    }
}
