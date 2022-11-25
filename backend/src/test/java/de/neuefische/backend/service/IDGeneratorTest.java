package de.neuefische.backend.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IDGeneratorTest {

    @Test
    void test_generateID() {
        IDGenerator idGenerator = new IDGenerator();
        String id = idGenerator.generateID();
        System.out.println(id);
        assertFalse(id.isEmpty());
    }
}