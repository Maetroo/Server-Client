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



import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * The {@code CSVFile} class builds object model of .csv file
 *
 *
 *
 * @author Bobur Zakirov
 * @see java.io.IOException
 * @see java.nio.file.Paths
 * @see java.util.ArrayList
 * @see java.util.List
 * @see java.util.Scanner
 * @since  1.2
 */

public class CSVFile {

    /**
     * Path to a .csv file
     */

    private final String path;

    /**
     * Type of the .csv file delimiter
     */

    private final CSVDelimiter delimiter;

    /**
     * {@code String} array of fields' name
     */

    protected String[] header;

    /**
     * List of the {@code String} arrays of values' name
     */

    protected List<String[]> body;

    /**
     * Constructor
     *
     * @param path          path to a .csv file
     * @throws              IOException if something wrong with input/output
     */

    public CSVFile(String path) throws IOException {
        
        this.path = path;
        this.delimiter = CSVDelimiter.COMMA;
        this.setFields();
    }

    /**
     * Constructor
     *
     * @param path          path to a .csv file
     * @param delimiter     type of the .csv file delimiter
     * @throws              IOException if something wrong with input/output
     */

    public CSVFile(String path, CSVDelimiter delimiter) throws IOException {

        this.path = path;
        this.delimiter = delimiter;
        this.setFields();
    }

    /**
     * The {@code setFields()} method sets a value of fields
     *
     * @throws              IOException if something wrong with input/output
     */

    private void setFields() throws IOException {

        List<String> file = this.read();

        String[] header = null;
        List<String[]> body = new ArrayList<>();

        if (file.size() != 0) {
            header = this.cut(file.get(0));
            file.remove(0);
        }

        for (String line : file) {
            body.add(this.cut(line));
        }

        this.header = header;
        this.body = body;
    }

    /**
     * The {@code cut()} method cuts a line by delimiter
     *
     * @param string        a String line
     * @return              array String words
     */

    private String[] cut(String string) {

        String[] columns = string.split(CSVDelimiter.getDelimiter(delimiter));

        for (int i = 0; i < columns.length; i ++)
            columns[i] = columns[i].trim();

        return columns;
    }

    /**
     * The {@code read()} method reads file as a list
     *
     * @return              {@code List} of {@code String}s
     * @throws              IOException if something wrong with input/output
     */

    private List<String> read() throws IOException {

        List<String> list = new ArrayList<>();
        Scanner stream = new Scanner(Paths.get(path));

        while (stream.hasNextLine()) {
            String line;
            if (!(line = stream.nextLine().trim()).equals("")) {
                list.add(line);
            }
        }

        stream.close();

        return list;
    }

    /**
     * Getter
     * @return              path  to .csv file
     */

    public String getPath() { return path; }

    /**
     * Getter
     * @return              type of delimiter of .csv file
     */

    public CSVDelimiter getDelimiter() { return delimiter; }
}
