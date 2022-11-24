package de.neuefische.backend.service;
import java.util.UUID;

public class IDGenerator {

    public String generateID(){
         UUID id = UUID.randomUUID();
        return id.toString();
    }

}
