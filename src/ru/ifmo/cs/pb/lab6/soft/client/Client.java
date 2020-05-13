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

package ru.ifmo.cs.pb.lab6.soft.client;



import ru.ifmo.cs.pb.lab6.soft.source.Commander;
import ru.ifmo.cs.pb.lab6.soft.source.command.*;
import ru.ifmo.cs.pb.lab6.soft.source.objects.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * The {@code Client} final class connects to with server
 * and can sends commands to server.
 *
 * @author Bobur Zakirov
 * @since  1.0
 */

public final class Client {

    
    private static Integer PORT = 4004;
    private static String HOST = "localhost";

    private static Socket socket;

    private static InputStream in_stream;
    private static OutputStream out_stream;

    //Constructor
    public Client(String[] args) { this.connect(args); }

    /**
     * The {connect()} method tries to connect to any server with it
     * host and port. If host and port don't showed, it will use 
     * \host:localhost and \port:4004.
     * 
     * @param args              an array of Strings
     */
    
    private void connect(String[] args) {

        if (args.length != 0) {
            if (args.length > 1) {
                System.out.println("Invalid format of argument!");
                System.exit(0);
            } else {
                String[] arr = args[0].trim().split(":");
                if (arr.length == 2) {
                    try {
                        HOST = arr[0];
                        PORT = Integer.parseInt(arr[1]);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid format of argument!");
                        System.exit(0);
                    }
                } else {
                    System.out.println("Invalid format of argument!");
                    System.exit(0);
                }
            }
        }

        try {
            socket = new Socket(HOST, PORT);

            socket.setSoTimeout(5000);

            in_stream = socket.getInputStream();
            out_stream = socket.getOutputStream();
        } catch (IOException e) {
            System.out.println("Server is offline...");
            System.exit(0);
        }
    }

    /**
     * The {@code main()} void method begins client!
     * 
      * @param args             an array of Strings
     */    

    public static void main(String[] args) {
        
        try {
            new Client(args).start();
        } catch (InterruptedException | IOException e) {
            System.out.println("ERROR OCCURRED!");
        }
    }

    /**
     * The {@code start()} method starts clients work
     * 
     * @throws                  IOException if something wrong with input/output
     * @throws                  InterruptedException if class does not exists
     */

    private void start() 
            throws IOException, InterruptedException {

        boolean online = true; //client mode

        String input = new String("");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please type 'help' or '?' to get information.");

        while (online) {

            System.out.print("\nNET:\\se\\ifmo\\ru\\projects\\lab6 > ");

            if (Script.scripts.size() != 0) {

                if (!Script.scripts.get(Script.scripts.size()-1).hasNextLine()) {
                    Script.scripts.remove(Script.scripts
                            .get(Script.scripts.size() - 1));

                    System.out.println("Script successfully DONE!");
                    continue;
                }

                if (Script.scripts.get(Script.scripts.size()-1).hasNextLine()) {
                    System.out.println(input = Script.scripts
                            .get(Script.scripts.size() - 1).nextLine().trim());
                }
                
            } else {
                input = scanner.nextLine().trim();
                input = input.equals("?") ? "help" : input;
                Script.empty = true;
            }

            if (input.equals("")) continue;

            if (input.toLowerCase().equals("exit") ||
                    input.toLowerCase().equals("quit")) {
                online = false; continue;
            }

            String[] args = input.split("\\s+");

            if (args[0].toLowerCase().equals(new Script().getName())) {
                Script script = new Script();
                script.setArgs(args);
                System.out.println(script.execute());
                continue;
            }

            AbstractCommand command;

            if (!Commander.commands.containsKey(args[0].toLowerCase())) {
                System.out.println("Current command does not exist!");
                continue;
            } else

                if (Commander.commands.get(args[0].toLowerCase()).needArgument() && args.length == 1) {
                    System.out.println("Current command needs an argument!");
                    continue;
                } else

                    if (!Commander.commands.get(args[0].toLowerCase()).needArgument() && args.length > 1) {
                        System.out.println("Current command does not need an argument!");
                        continue;
                    } else

                        if (Commander.commands.get(args[0].toLowerCase()).needArgument()) {
                            command = Commander.commands.get(args[0].toLowerCase());
                            command.setArgs(args);

                            if (args[0].toLowerCase().equals("update_by_id"))
                                command.setObject(Laboratory.getInstance());

                        } else

                            {
                                if (!Commander.commands.get(args[0].toLowerCase()).needInput()) {
                                    command = Commander.commands.get(args[0].toLowerCase());
                                } else

                                    {
                                        command = Commander.commands.get(args[0].toLowerCase());
                                        command.setArgs(args);

                                        if (input.toLowerCase().equals("filter_less_than_discipline"))
                                            command.setObject(Discipline.getInstance());

                                            else command.setObject(Laboratory.getInstance());
                                    }

                            }

            this.send(command);
            this.receive();
        }
    }

    /**
     * The {@code connect()} method tries to rebuild failed contact with server
     * 
     * @throws                  InterruptedException if something wrong with threads
     */
    
    private void connect() throws InterruptedException {

        System.out.print("Connecting");

        for (int i = 0; i < 3; i ++) {
            Thread.sleep(500); System.out.print(".");
            Thread.sleep(500); System.out.print(".");
            Thread.sleep(500); System.out.print(".");
            Thread.sleep(500); System.out.print("\b\b\b");
        }

        System.out.print("\b\b\b\b\b\b\b\b\b\b");

        try {
            socket = new Socket(HOST, PORT);

            in_stream = socket.getInputStream();
            out_stream = socket.getOutputStream();

        } catch (IOException e) {

            System.out.println("Connection lost!");
            System.exit(0);
        }

    }

    /**
     * The {@code send()} method sends array of bytes to connected server
     *
     * @param command               an object of {@code AbstractCommand} class
     * @throws                      IOException if something wrong with input/output
     * @throws                      InterruptedException if something wrong with threads
     */
    
    private void send(AbstractCommand command)
            throws IOException, InterruptedException {

        try {
            out_stream.write(Serializer.serialize(command));
        } catch (IOException ignored) {
            this.connect();
            out_stream.write(Serializer.serialize(command));
        }
    }

    /**
     * The {@code receive()} method receives result of work of the server
     */

    private void receive() {

        int checker;

        try {
            while ((checker = in_stream.read()) != (int) '\b')
                System.out.print((char) checker);
        } catch (IOException e) {
            System.out.println("\nConnection lost!");
        }
    }
}