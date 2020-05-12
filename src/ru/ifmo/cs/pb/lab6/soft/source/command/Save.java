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
import ru.ifmo.cs.pb.lab6.soft.source.objects.Laboratory;
import ru.ifmo.cs.pb.lab6.soft.source.parser.CSVDelimiter;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class Save extends AbstractCommand {


    public Save() {

        this.name = "save";
        this.description = "save  collection  to  file";
    }


    @Override
    public String execute(Collection collection) {

        String header = "id,name,coordinates_x,coordinates_y,creationDate,minimalPoint," +
                        "personalQualitiesMinimum,tunedInWorks,difficulty,discipline_name," +
                        "discipline_selfStudyHours,discipline_labsCount";

        StringBuilder builder = new StringBuilder(header + "\n");
        String path = collection.getManager().getFile().getPath();
        String delimiter = CSVDelimiter.getDelimiter(collection.getManager().getFile().getDelimiter());

        for (Laboratory laboratory : collection.getCollection()) {
            builder.append(laboratory.getId()).append(delimiter);
            builder.append(laboratory.getName()).append(delimiter);
            builder.append(laboratory.getCoordinates().getX()).append(delimiter);
            builder.append(laboratory.getCoordinates().getY()).append(delimiter);
            builder.append(laboratory.getCreationDate()).append(delimiter);
            builder.append(laboratory.getMinimalPoint()).append(delimiter);
            builder.append(laboratory.getPersonalQualitiesMinimum()).append(delimiter);
            builder.append(laboratory.getTunedInWorks()).append(delimiter);
            builder.append(laboratory.getDifficulty() != null ? laboratory
                    .getDifficulty() : "").append(delimiter);
            builder.append(laboratory.getDiscipline().getName()).append(delimiter);
            builder.append(laboratory.getDiscipline().getSelfStudyHours() != null ? laboratory
                    .getDiscipline().getSelfStudyHours() : "").append(delimiter);
            builder.append(laboratory.getDiscipline().getLabsCount() != null ? laboratory
                    .getDiscipline().getLabsCount() : "").append(delimiter).append("\n");
        }

        try {
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(path));

            stream.write(builder.toString().getBytes());
            stream.close();

            } catch (IOException e)
                {
                    return "Something wrong with input/output!";
                }

        return "Successfully saved!";
    }
}
