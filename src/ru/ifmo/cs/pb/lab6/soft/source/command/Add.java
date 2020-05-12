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


public class Add extends AbstractCommand {


    public Add() {

        this.name = "add";
        this.description = "add    new    element    to    collection";
        this.needInput = true;
    }


    @Override
    public String execute(Collection collection) {

        Laboratory laboratory = (Laboratory) this.object;

        if (laboratory == null) return "Command failed!";

        laboratory.setId(collection.findUniqueID());
        collection.getCollection().add(laboratory);
        collection.updateInitializationDate();

        return "Successfully added new item to the collection!";
    }
}
