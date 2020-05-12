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
import ru.ifmo.cs.pb.lab6.soft.source.Commander;


public class Help extends AbstractCommand {


    public Help() {

        this.name = "help";
        this.description = "display  help   for  available   commands";
    }


    @Override
    public String execute(Collection collection) {

        StringBuilder builder = new StringBuilder("");

        Commander.getAvailableCommands()
                 .forEach(command -> builder.append(String
                 .format("%-36s - %s.\n", command.name, command.description)));

        builder.append(String.format("%-36s - %s.", "exit (quit)",
                "terminated    the    client   application"));

        return builder.toString();
    }
}
