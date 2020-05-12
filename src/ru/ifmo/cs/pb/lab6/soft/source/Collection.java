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

package ru.ifmo.cs.pb.lab6.soft.source;



import ru.ifmo.cs.pb.lab6.soft.source.exception.*;
import ru.ifmo.cs.pb.lab6.soft.source.objects.*;
import ru.ifmo.cs.pb.lab6.soft.source.parser.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * The {@code Collection} class works with collection. It keeps
 * collection, with type is {@link LinkedList} with objects of
 * {@code Laboratory} class.
 *
 *
 *
 * @author Bobur Zakirov
 * @see java.io.IOException
 * @see java.time.LocalDate
 * @see java.util.HashSet
 * @see java.util.LinkedList
 * @see java.util.Set
 * @since  1.2
 */

public final class Collection {

    /**
     * Collection
     */

    private LinkedList<Laboratory> collection = new LinkedList<>();

    /**
     * Initialization date of the Collection
     */

    private LocalDate initializationDate;

    /**
     * Object of {@link CSVManager} class
     */

    private CSVManager manager;

    /**
     * Constructor
     * @param path          path to .csv file
     */

    public Collection(final String path) {
        try {
                this.initializationDate = LocalDate.now();
                this.manager = new CSVManager(new CSVFile(path));
                this.collection = manager.getCollection();
                this.collection.forEach(this::checkFields);
                this.checkID();
            }
            catch (IOException e)
                {
                    System.out.println("File does not exist!");
                    System.exit(0);
                }
    }

    /**
     * The {@code checkFields()} method checks fields of
     * {@code Laboratory} class.
     *
     * @param laboratory    object of the {@code Laboratory} class
     */

    private void checkFields(Laboratory laboratory) {
        if (laboratory.getId() == null)
            throw new NullValueException("id");
        if (laboratory.getId() <= 0)
            throw new MinOrMaxOutException(String.valueOf(laboratory
                    .getId()));
        if (laboratory.getName() == null)
            throw new NullValueException("name");
        if (laboratory.getCoordinates().getX() > 547)
            throw new MinOrMaxOutException(String.valueOf(laboratory
                    .getCoordinates().getX()));
        if (laboratory.getCoordinates().getY() <= -583)
            throw new MinOrMaxOutException(String.valueOf(laboratory
                    .getCoordinates().getY()));
        if (laboratory.getCreationDate() == null)
            throw new NullValueException("creationDate");
        if (laboratory.getMinimalPoint() == null)
            throw new NullValueException("minimalPoint");
        if (laboratory.getMinimalPoint() <= 0)
            throw new MinOrMaxOutException(String.valueOf(laboratory
                    .getMinimalPoint()));
        if (laboratory.getPersonalQualitiesMinimum() == null)
            throw new NullValueException("personalQualitiesMinimum");
        if (laboratory.getPersonalQualitiesMinimum() <= 0)
            throw new MinOrMaxOutException(String.valueOf(laboratory
                    .getPersonalQualitiesMinimum()));
        if (laboratory.getTunedInWorks() == null)
            throw new NullValueException("tunedInWorks");
        if (laboratory.getDiscipline().getName() == null)
            throw new NullValueException("discipline_name");
    }

    /**
     * The {@code checkID()} method checks IDs elements of the collection,
     * if he'll find the equal ids throws DuplicatedIdException.
     *
     * @throws              DuplicatedIdException if here in the collection
     *                      two element with same IDs
     */

    private void checkID() throws DuplicatedIdException {

        int i = 0;

        Set<Long> IDs = new HashSet<>();

        for (Laboratory laboratory : this.collection) {

            IDs.add(laboratory.getId());

            if (++ i != IDs.size()) throw new DuplicatedIdException(String
                    .valueOf(laboratory.getId()));
        }
    }

    /**
     * The {@code findUniqueID()} method generates a new unique ID to
     * a new element.
     *
     * @return              Long number
     */

    public Long findUniqueID() {

        Long ID = Long.MAX_VALUE;
        Set<Long> ids = new HashSet<>();

        this.collection.forEach(laboratory -> ids.add(laboratory.getId() ));

        long size = ids.size();
        while (ids.size() == size) ids.add(-- ID);

        return ID;
    }

    /**
     * The {@code updateInitializationDate()} method updates initialization
     * date of the collection
     */

    public void updateInitializationDate() { this.initializationDate = LocalDate.now(); }

    /**
     * Getter
     * @return              Collection
     */

    public LinkedList<Laboratory> getCollection() { return collection; }

    /**
     * Getter
     * @return              Initialization Date
     */

    public LocalDate getInitializationDate() { return initializationDate; }

    /**
     * Getter
     * @return              value of field {@code manager}
     */
    
    public CSVManager getManager() { return manager; }
}
