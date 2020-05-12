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


public class AddIfMin extends AbstractCommand {


    public AddIfMin() {

        this.name = "add_if_min";
        this.description = "add a new  element to the  collection, if" +
                           "\n                                       " +
                           "it's value is less than from the value of" +
                           "\n                                       " +
                           "smallest  element  in  this  collection";
        this.needInput = true;
    }
    

    @Override
    public String execute(Collection collection) {

        Laboratory laboratory = (Laboratory) this.object;

        if (laboratory == null) return "Command failed!";

        int counter = 0;
        for (Laboratory laba : collection.getCollection())
            if (laba.compareTo(laboratory) <= 0) counter ++;

        if (counter != 0) return "Current item is not the smallest!";

        laboratory.setId(collection.findUniqueID());
        collection.getCollection().add(laboratory);
        collection.updateInitializationDate();

        return "Successfully added new item to the collection!";
    }
}
