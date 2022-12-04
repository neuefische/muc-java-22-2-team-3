package de.neuefische.backend.model;

public enum Status {
    READ ("r"),
    READING ("rd"),
    TO_READ("tr");

    private final String name;

    private Status(String name) 
       {this.name = name; }

    }

