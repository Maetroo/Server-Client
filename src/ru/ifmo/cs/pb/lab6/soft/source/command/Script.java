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

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Script extends AbstractCommand {


    public static List<Scanner> scripts = new ArrayList<Scanner>();
    public static boolean empty = true;


    public Script() {

        this.name = "execute_script";
        this.description = "read  and  execute the  script  from  the" +
                           "\n                                       " +
                           "specified   file, the   script   contains" +
                           "\n                                       " +
                           "commands   in  the  same  form  in  which" +
                           "\n                                       " +
                           "they   are   entered    by    the    user" +
                           "\n                                       " +
                           "interactively";
    }


    public String execute() {

        if (this.args.length > 2) return "Current command needs only one argument!";

        try {
            scripts.add(new Scanner(Paths.get(args[args.length-1])));
            empty = false;
            }
            catch (IOException e)
                {
                    return "File does not exist!";
                }
                catch (Exception e)
                    {
                        return "List is crowded!";
                    }

        return "Script readed from the file!";
    }

    @Override
    public String execute(Collection collection) { return "null"; }
}