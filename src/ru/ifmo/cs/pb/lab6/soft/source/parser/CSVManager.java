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

package ru.ifmo.cs.pb.lab6.soft.source.parser;



import ru.ifmo.cs.pb.lab6.soft.source.objects.*;

import java.time.LocalDate;
import java.util.LinkedList;

/**
 * The {@code CSVManager} class only has one public method,
 * which converts object of {@code CSVFile} class to collection
 * of {@code Laboratory}'s.
 *
 *
 *
 * @author Bobur Zakirov
 * @see java.time.LocalDate
 * @see java.util.LinkedList
 * @since  1.2
 */

public class CSVManager {

    /**
     * Object of .csv file
     */

    private final CSVFile file;

    /**
     * Constructor
     * @param file              object of .csv file
     */

    public CSVManager(CSVFile file) { this.file = file; }

    /**
     * The {@code getCollection()} method parses all data in
     * .csv file to Java Object.
     *
     * @return                  collection of Laboratories
     */

    public LinkedList<Laboratory> getCollection() {

        LinkedList<Laboratory> laboratories = new LinkedList<>();

        if (file.header == null || file.body == null) return laboratories;

        file.body.forEach(body -> laboratories
                .add(this.getLaboratory(file.header, body)));

        return laboratories;
    }

    /**
     * The {@code getLaboratory()} method parses to Java Object.
     *
     * @param header            header of .csv file
     * @param body              body of .csv file
     * @return                  object of {@code Laboratory} class
     */

    private Laboratory getLaboratory(String[] header, String[] body) {

        Laboratory laboratory = new Laboratory();
        Coordinates coordinates = new Coordinates();
        Discipline discipline = new Discipline();

        for (int i = 0, j = 0; i < header.length && j < body.length; i ++, j ++) {

            if (body[j].equals("")) continue;

            switch (header[i]) {

                case "id":
                    laboratory.setId(Long.parseLong(body[j]));
                    break;
                case "name":
                    laboratory.setName(String.valueOf(body[j]));
                    break;
                case "coordinates_x":
                    coordinates.setX(Long.parseLong(body[j]));
                    break;
                case "coordinates_y":
                    coordinates.setY(Double.parseDouble(body[j]));
                    break;
                case "creationDate":
                    laboratory.setCreationDate(LocalDate.parse(body[j]));
                    break;
                case "minimalPoint":
                    laboratory.setMinimalPoint(Float.parseFloat(body[j]));
                    break;
                case "personalQualitiesMinimum":
                    laboratory.setPersonalQualitiesMinimum(Double.parseDouble(body[j]));
                    break;
                case "tunedInWorks":
                    laboratory.setTunedInWorks(Integer.parseInt(body[j]));
                    break;
                case "difficulty":
                    laboratory.setDifficulty(Difficulty.parseDifficulty(body[j]));
                    break;
                case "discipline_name":
                    discipline.setName(String.valueOf(body[j]));
                    break;
                case "discipline_selfStudyHours":
                    discipline.setSelfStudyHours(Integer.parseInt(body[j]));
                    break;
                case "discipline_labsCount":
                    discipline.setLabsCount(Long.parseLong(body[j]));
                    break;
            }
        }

        laboratory.setCoordinates(coordinates);
        laboratory.setDiscipline(discipline);

        return laboratory;
    }

    /**
     * Getter
     * @return                  value of field {@code file}
     */

    public CSVFile getFile() {
        return file;
    }
}
