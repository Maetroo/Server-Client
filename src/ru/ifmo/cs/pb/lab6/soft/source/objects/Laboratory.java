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

package ru.ifmo.cs.pb.lab6.soft.source.objects;



import ru.ifmo.cs.pb.lab6.soft.source.command.Script;
import ru.ifmo.cs.pb.lab6.soft.source.exception.DifficultyParseException;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * The {@code Laboratory} class is the main base class. Current
 * class is the object model of laboratory work.
 *
 *
 *
 * @author Bobur Zakirov
 * @see java.io.Serializable
 * @see java.lang.Comparable
 * @see java.time.LocalDate
 * @see java.util.Scanner
 * @since  1.2
 */

public class Laboratory implements Serializable, Comparable<Laboratory> {

    /**
     * Field must be non-null Integer number
     * Field must be generate automatically
     */

    private Long id;

    /**
     * Field cannot be null
     */

    private String name;

    /**
     * Field cannot be null
     */

    private Coordinates coordinates;

    /**
     * Field cannot be null
     * Field must be generate automatically
     */

    private java.time.LocalDate creationDate;

    /**
     * Field must be non-null Float number
     */

    private Float minimalPoint;

    /**
     * Field must be non-null Double number
     */

    private Double personalQualitiesMinimum;

    /**
     * Field must be non-null Integer number
     */

    private Integer tunedInWorks;

    /**
     * Field can be null
     */

    private Difficulty difficulty;

    /**
     * Field cannot be null
     */

    private Discipline discipline;


    public Long getId( ) {
        return id; }

    public void setId(Long id) {
        this.id = id; }

    public String getName( ) {
        return name; }

    public void setName(String name) {
        this.name = name; }

    public Coordinates getCoordinates( ) {
        return coordinates; }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates; }

    public LocalDate getCreationDate( ) {
        return creationDate; }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate; }

    public Float getMinimalPoint( ) {
        return minimalPoint; }

    public void setMinimalPoint(Float minimalPoint) {
        this.minimalPoint = minimalPoint; }

    public Double getPersonalQualitiesMinimum( ) {
        return personalQualitiesMinimum; }

    public void setPersonalQualitiesMinimum
            (Double personalQualitiesMinimum) {
        this.personalQualitiesMinimum
                = personalQualitiesMinimum; }

    public Integer getTunedInWorks( ) {
        return tunedInWorks; }

    public void setTunedInWorks(Integer tunedInWorks) {
        this.tunedInWorks = tunedInWorks; }

    public Difficulty getDifficulty( ) {
        return difficulty; }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty; }

    public Discipline getDiscipline( ) {
        return discipline; }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline; }

    /**
     * The {@code getInstance()} method asks value of this class
     * and builds it. Asks with console or reads it from Script.
     *
     * @return          Object of this class
     */


    public static Laboratory getInstance() {

        boolean success = true;
        boolean empty = Script.empty;

        String string;
        Scanner script = null;
        Scanner scanner = new Scanner(System.in);

        if (!empty) script = Script.scripts.get(Script.scripts.size() - 1);

        Laboratory laboratory = new Laboratory();

        while (success) {

            if (Script.empty) {
                System.out.print("Please enter \\name: ");
                string = scanner.nextLine().trim();
            } else

                if (script.hasNextLine()) {
                    string = script.nextLine().trim();
                } else

                    string = null;

            if (string == null) success = false;

            if (string != null && string.equals("")) {
                if(Script.empty) System.out.println("INCORRECT!");
                continue;
            }

            laboratory.setName(string);
            break;
        }

        while (success) {

            if (Script.empty) {
                System.out.print("Please enter \\minimal_point (Float > 0): ");
                string = scanner.nextLine().trim();
            } else

                if (script.hasNextLine()) {
                    string = script.nextLine().trim();
                } else

                    string = null;

            if (string == null) success = false;

            try {
                if (string != null) laboratory
                        .setMinimalPoint(Float.parseFloat(string));
                if (laboratory.getMinimalPoint() <= 0)
                    throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                if (Script.empty) System.out.println("INCORRECT!");
            }
        }

        while (success) {

            if (Script.empty) {
                System.out.print("Please  enter \\personal_qualities_min (Double > 0): ");
                string = scanner.nextLine().trim();
            } else

                if (script.hasNextLine()) {
                    string = script.nextLine().trim();
                } else

                    string = null;

            if (string == null) success = false;

            try {
                if (string != null) laboratory
                        .setPersonalQualitiesMinimum(Double.parseDouble(string));
                if (laboratory.getPersonalQualitiesMinimum() <= 0)
                    throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                if (Script.empty) System.out.println("INCORRECT!");
            }
        }

        while (success) {

            if (Script.empty) {
                System.out.print("Please enter \\tune_in_works (Integer): ");
                string = scanner.nextLine().trim();
            } else

                if (script.hasNextLine()) {
                    string = script.nextLine().trim();
                } else

                    string = null;

            if (string == null) success = false;

            try {
                if (string != null) laboratory
                        .setTunedInWorks(Integer.parseInt(string));
                break;
            } catch (NumberFormatException e) {
                if (Script.empty) System.out.println("INCORRECT!");
            }
        }

        while (success) {

            if (Script.empty) {
                System.out.print("Please enter \\difficulty " +
                                 "(VERY_EASY, NORMAL, HARD, IMPOSSIBLE, TERRIBLE): ");
                string = scanner.nextLine().trim();
            } else

                if (script.hasNextLine()) {
                    string = script.nextLine().trim();
                } else

                    string = null;

            if (string == null) success = false;

            try {
                if (string != null && !string.equals("")) laboratory
                        .setDifficulty(Difficulty.parseDifficulty(string.toUpperCase()));
                break;
            } catch (DifficultyParseException e) {
                if (Script.empty) System.out.println("INCORRECT!");
            }
        }

        laboratory.setCreationDate(LocalDate.now());
        laboratory.setCoordinates(Coordinates.getInstance());
        laboratory.setDiscipline(Discipline.getInstance());

        if (!success || laboratory.getCoordinates() == null
            || laboratory.getDiscipline() == null)
            laboratory = null;

        return laboratory;
    }

    /**
     * The @Override {@code toString()} method is the {@code Object} class's
     * method to show class fields
     *
     * @return              an object of {@code String} class
     */

    @Override
    public String toString() {
        return "Laboratory /id::"                    + id                           + "\n" +
               "           /name::"                  + name                         + "\n" +
               "           /coor_x::"                + coordinates.getX()           + "\n" +
               "           /coor_y::"                + coordinates.getY()           + "\n" +
               "           /creation_date::"         + creationDate                 + "\n" +
               "           /minimal_point::"         + minimalPoint                 + "\n" +
               "           /per_qual_min::"          + personalQualitiesMinimum     + "\n" +
               "           /tuned_in_works::"        + tunedInWorks                 + "\n" +
               "           /difficulty::"            + difficulty                   + "\n" +
               "           /disc_name::"             + discipline.getName()         + "\n" +
               "           /disc_labs_count::"       + discipline.getLabsCount()    + "\n" +
               "           /disc_self_study_hours::" + discipline.getSelfStudyHours();
    }

    /**
     * The {@code compareTo()} method compares objects of current class
     *
     * @param laboratory        an object of {@code Discipline} class
     * @return                  positive number if first object greater than second
     *                          negative number if first object less than second
     *                          zero if they are equal
     */

    @Override
    public int compareTo(Laboratory laboratory) { return this.name.compareTo(laboratory.name); }
}
