package de.neuefische.backend.service;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IDGenerator {

    public String generateID(){
         UUID id = UUID.randomUUID();
        return id.toString();
    }

}
