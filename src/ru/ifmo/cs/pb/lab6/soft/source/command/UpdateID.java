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


public class UpdateID extends AbstractCommand {


    public UpdateID() {

        this.name = "update_by_id";
        this.description = "update the value of a collection element," +
                           "\n                                       " +
                           "whose  'id' is  equal  to  the  specified";
        this.needArgument = true;
    }


    @Override
    public String execute(Collection collection) {

        Long ID;
        Laboratory laboratory = (Laboratory) this.object;

        if (args.length > 2) return "Current command needs only one argument!";

        try { ID = Long.parseLong(args[args.length-1]); }
        catch (NumberFormatException e) { return "Invalid format of argument!"; }

        for (Laboratory laba : collection.getCollection())
            if (laba.getId().equals(ID)) {
                laboratory.setId(ID);
                collection.getCollection().remove(laba);
                collection.getCollection().add(laboratory);
                collection.updateInitializationDate();
                return "Successfully updated item with \\ID:" + ID + "!";
            }

        return "Could not find item with \\ID:" + ID + "!";
    }
}
