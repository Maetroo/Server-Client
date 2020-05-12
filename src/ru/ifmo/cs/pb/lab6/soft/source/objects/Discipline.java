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

import java.io.Serializable;
import java.util.Scanner;

/**
 * The {@code Discipline} class is the base class of this
 * laboratory work. Current class is a field to the main
 * {@code Laboratory} class.
 *
 *
 *
 * @author Bobur Zakirov
 * @see java.io.Serializable
 * @see java.lang.Comparable
 * @see java.util.Scanner
 * @since  1.2
 */

public class Discipline
        implements Serializable, Comparable<Discipline> {

    /**
     * Value of the field cannot be null
     */

    private String name;

    /**
     * Value of the field must be Integer or null
     */

    private Integer selfStudyHours;

    /**
     * Value of the field must be Long or null
     */

    private Long labsCount;


    public String getName() {
        return name; }

    public void setName(String name) {
        this.name = name; }

    public Integer getSelfStudyHours() {
        return selfStudyHours; }

    public void setSelfStudyHours(Integer selfStudyHours) {
        this.selfStudyHours = selfStudyHours; }

    public Long getLabsCount() {
        return labsCount; }

    public void setLabsCount(Long labsCount) {
        this.labsCount = labsCount; }

    /**
     * The {@code getInstance()} method asks value of this class
     * and builds it. Asks with console or reads it from Script.
     *
     * @return          Object of this class
     */

    public static Discipline getInstance() {

        boolean success = true;
        boolean empty = Script.empty;

        String string;

        Scanner script = null;
        Scanner scanner = new Scanner(System.in);

        if (!empty) script = Script.scripts.get(Script.scripts.size() - 1);

        Discipline discipline = new Discipline();

        while (success) {

            if (empty) {
                System.out.print("Please enter \\discipline_name: ");
                string = scanner.nextLine().trim();
            } else

                if (script.hasNextLine()) {
                    string = script.nextLine().trim();
                } else

                    string = null;

            if (string == null) success = false;

            if (string != null && string.equals("")) {
                if(empty) System.out.println("INCORRECT!");
                continue;
            }

            discipline.setName(string);
            break;
        }

        while (success) {

            if (empty) {
                System.out.print("Please enter " +
                        "\\discipline_self_study_hours (Integer or null): ");
                string = scanner.nextLine().trim();
            } else
                if (script.hasNextLine()) {
                    string = script.nextLine().trim();
                } else

                    string = null;

            if (string == null) success = false;

            try {
                if (string != null && !string.equals(""))
                    discipline.setSelfStudyHours(Integer.parseInt(string));
                break;
            } catch (NumberFormatException e) {
                if (empty) System.out.println("INCORRECT!");
            }
        }

        while (success) {

            if (empty) {
                System.out.print("Please enter " +
                        "\\discipline_labs_count (Long or null): ");
                string = scanner.nextLine().trim();
            } else

                if (script.hasNextLine()) {
                    string = script.nextLine().trim();
                } else

                    string = null;

            if (string == null) success = false;

            try {
                if (string != null && !string.equals(""))
                    discipline.setLabsCount(Long.parseLong(string));
                break;
            } catch (NumberFormatException e) {
                if (empty) System.out.println("INCORRECT!");
            }
        }

        if (!success) discipline = null;

        return discipline;
    }

    /**
     * The {@code compareTo()} method compares objects of current class
     *
     * @param discipline        an object of {@code Discipline} class
     * @return                  positive number if first object greater than second
     *                          negative number if first object less than second
     *                          zero if they are equal
     */

    @Override
    public int compareTo(Discipline discipline) {
        return this.name.length() - discipline.name.length(); }
}
