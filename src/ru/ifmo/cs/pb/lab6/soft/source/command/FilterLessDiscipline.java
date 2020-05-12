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
import ru.ifmo.cs.pb.lab6.soft.source.objects.Discipline;


public class FilterLessDiscipline extends AbstractCommand {


    public FilterLessDiscipline() {

        this.name = "filter_less_than_discipline";
        this.description = "display   elements,   whose  'discipline'" +
                           "\n                                       " +
                           "field  value  is  less  than   the  given";
        this.needInput = true;
    }


    @Override
    public String execute(Collection collection) {

        StringBuilder builder = new StringBuilder("");
        Discipline discipline = (Discipline) this.object;

        if (discipline == null) return "Command failed!";
        if (collection.getCollection().size() == 0) return "Collection is empty!";

        collection.getCollection().forEach(laboratory
                -> builder.append(discipline.compareTo(laboratory
                .getDiscipline()) > 0 ? "\n" + laboratory.toString() : "" ));

        if (builder.toString().equals("")) return "Could not find an item, " +
                "wich \\discipline::value less than the entered!";

        return builder.toString();
    }
}
