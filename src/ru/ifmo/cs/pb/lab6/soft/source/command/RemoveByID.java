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


public class RemoveByID extends AbstractCommand {


    public RemoveByID() {

        this.name = "remove_by_id";
        this.description = "remove an element from  the collection by" +
                           "\n                                       " +
                           "its 'id'";
        this.needArgument = true;
    }


    @Override
    public String execute(Collection collection) {

        Long ID;

        if (args.length > 2) return "Current command needs only one argument!";

        try { ID = Long.parseLong(args[args.length-1]); }
            catch (NumberFormatException e) { return "Invalid format of argument!"; }

        int size = collection.getCollection().size();

        if (size == 0) return "Collection is empty!";

        for (int i = 0; i < collection.getCollection().size(); i ++)
            if (ID.equals(collection.getCollection().get(i).getId())) {
                collection.getCollection().remove(i); break;
            }

        if (size == collection.getCollection().size()) {
            return "Could not find an item with /id:" + ID + " in the collection!";
        } else {
            collection.updateInitializationDate();
            return "Successfully removed the item with /id:" + ID + " from the collection!";
        }
    }
}
