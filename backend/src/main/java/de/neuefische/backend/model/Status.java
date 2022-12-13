package de.neuefische.backend.model;

public enum Status {
    READ ("read"),
    READING ("reading"),
    TO_READ("to read");

    private final String name;

    private Status(String name) 
       {this.name = name; }

    }

