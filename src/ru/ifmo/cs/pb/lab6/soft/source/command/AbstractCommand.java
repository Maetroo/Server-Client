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

import java.io.Serializable;

/**
 * The {@code AbstractCommand} class the main abstract class
 * to all commands. It implements from {@code Serializable}
 * class. All command classes manage from current class.
 *
 *
 *
 *
 *
 * @author Bobur Zakirov
 * @see java.io.Serializable
 * @since  1.2
 */

public abstract class AbstractCommand
                            implements Serializable {


    /**
     * name of the command
     */

    protected String name;

    /**
     * description of the command
     */

    protected String description;

    /**
     * Especially field for classes, who needs an Input
     */

    protected Object object;

    /**
     * Especially field for classes, who needs an Argument
     */

    protected String[] args;

    /**
     * Value of field {@code true}, when class needs an Input
     */

    protected boolean needInput = false;

    /**
     * Value of field {@code true}, when class needs an Arguments
     */

    protected boolean needArgument = false;

    /**
     * The abstract method {@code execute()} executes command!
     *
     * @param collection        an object of {@code Collection} class
     * @return                  an object of {@code String} class
     */

    public abstract String execute(Collection collection);

    /**
     * The {@code needInput()} method returns value of field
     * {@code needInput}.
     *
     * @return                  boolean
     */

    public boolean needInput() { return needInput; }

    /**
     * The {@code needArgument()} method returns value of field
     * {@code needArgument}.
     *
     * @return                  boolean
     */

    public boolean needArgument() { return needArgument; }

    /**
     * Setter
     * @param args              array of arguments of command
     */

    public void setArgs(String[] args) { this.args = args; }

    /**
     * Setter
     * @param object            object of {@code Object} class
     */

    public void setObject(Object object) { this.object = object; }

    /**
     * Getter
     * @return                  value of field {@code name}
     */

    public String getName() { return name; }
}
