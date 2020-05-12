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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PrintMinimalPoint extends AbstractCommand {


    public PrintMinimalPoint() {
        this.name = "print_field_descending_minimal_point";
        this.description = "print  the  'minimalPoint'  field  values" +
                           "\n                                       " +
                           "of  all  elements   in  descending  order";
    }


    @Override
    public String execute(Collection collection) {

        List<Float> points = new ArrayList<Float>();
        StringBuilder builder = new StringBuilder("");

        collection.getCollection().forEach(laboratory
                -> points.add(laboratory.getMinimalPoint()));

        Collections.sort(points);
        Collections.reverse(points);

        points.forEach(item -> builder.append("\n").append(item.toString()));

        if (collection.getCollection().size() == 0) return "Collection is empty!";

        return builder.toString();
    }
}
