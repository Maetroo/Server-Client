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
 * The {@code Coordinates} class is the base class of this
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

public class Coordinates
        implements Serializable, Comparable<Coordinates> {

    /**
     * MAX_VALUE of this field 547
     */

    private long x;

    /**
     * Value of this field must be greater than -583
     */

    private double y;


    public long getX() { return x; }

    public void setX(long x) { this.x = x; }

    public double getY() { return y; }

    public void setY(double y) { this.y = y; }

    /**
     * The {@code getInstance()} method asks value of this class
     * and builds it. Asks with console or reads it from Script.
     *
     * @return          Object of this class
     */

    public static Coordinates getInstance() {

        boolean success = true;
        boolean empty = Script.empty;

        String string;

        Scanner script = null;
        Scanner scanner = new Scanner(System.in);

        if (!empty) script = Script.scripts
                .get(Script.scripts.size() - 1);

        Coordinates coordinates = new Coordinates();

        while (success) {

            if (Script.empty) {
                System.out.print("Please enter " +
                        "\\coordinates_x (long <= 547): ");
                string = scanner.nextLine().trim();
            } else

                if (script.hasNextLine()) {
                    string = script.nextLine().trim();
                } else

                    string = null;


            if (string == null) success = false;

            try {
                if (string != null) coordinates
                        .setX(Long.parseLong(string));
                if (coordinates.getX() > 547)
                    throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                if (Script.empty) System.out.println("INCORRECT!");
            }
        }

        while (success) {

            if (Script.empty) {
                System.out.print("Please enter " +
                        "\\coordinates_y (double > -583): ");
                string = scanner.nextLine().trim();
            } else

                if (script.hasNextLine()) {
                    string = script.nextLine().trim();
                } else

                    string = null;

            if (string == null) success = false;

            try {
                if (string != null) coordinates
                        .setY(Double.parseDouble(string));
                if (coordinates.getY() <= -583)
                    throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                if (Script.empty) System.out.println("INCORRECT!");
            }
        }

        if (!success) coordinates = null;

        return coordinates;
    }

    /**
     * The {@code compareTo()} method compares objects of current class
     *
     * @param coordinates       an object of {@code Coordinates} class
     * @return                  positive number if first object greater than second
     *                          negative number if first object less than second
     *                          zero if they are equal
     */

    @Override
    public int compareTo(Coordinates coordinates) {
        return (int) x - (int) coordinates.x
             + (int) y - (int) coordinates.y;
    }
}
