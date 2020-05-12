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
import ru.ifmo.cs.pb.lab6.soft.source.exception.DifficultyParseException;
import ru.ifmo.cs.pb.lab6.soft.source.objects.Difficulty;
import ru.ifmo.cs.pb.lab6.soft.source.objects.Laboratory;


public class FilterGreaterDifficulty extends AbstractCommand {


    public FilterGreaterDifficulty() {

        this.name = "filter_greater_than_difficulty";
        this.description = "display   elements,   whose  'difficulty'" +
                           "\n                                       " +
                           "field  value  isgreater  than  the  given";
        this.needArgument = true;
    }


    @Override
    public String execute(Collection collection) {

        Difficulty difficulty;

        if (args.length > 2) return "Current command needs only one argument!";
        if (collection.getCollection().size() == 0) return "Collection is empty!";

        try { difficulty = Difficulty.parseDifficulty(args[args.length-1]); }
            catch (DifficultyParseException e) { return "Invalid format of argument!"; }

        StringBuilder builder = new StringBuilder("");

        for (Laboratory laboratory : collection.getCollection()) {
            if (laboratory.getDifficulty() != null) builder.append(Difficulty
                    .getDiffMap.get(difficulty) < Difficulty.getDiffMap.get(laboratory
                    .getDifficulty()) ? "\n" + laboratory.toString() : "");
        }

        if (builder.toString().equals("")) return "Could not find an item, " +
                "wich difficulty greater than /difficulty:" + difficulty + "!";

        return builder.toString();
    }
}
