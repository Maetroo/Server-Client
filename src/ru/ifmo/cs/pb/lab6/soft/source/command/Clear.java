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


public class Clear extends AbstractCommand {


    public Clear() {

        this.name = "clear";
        this.description = "clear  collection";
    }


    @Override
    public String execute(Collection collection) {

        collection.getCollection().clear();
        collection.updateInitializationDate();

        return "Collection successfully cleared!";
    }
}
