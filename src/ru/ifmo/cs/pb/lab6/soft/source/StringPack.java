package ru.ifmo.cs.pb.lab6.soft.source;

import java.io.Serializable;

public class StringPack implements Serializable {

    private final String string;

    public StringPack(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
