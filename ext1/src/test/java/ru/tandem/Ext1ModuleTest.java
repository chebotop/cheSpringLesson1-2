package ru.tandem;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Ext1ModuleTest {
    private Ext1Module ext1Module;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        ext1Module = new Ext1Module();
        ext1Module.setDescription("Good day");
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testPrintWelcomeMessage() {
//        Ext1Module ext1Module = new Ext1Module();
        ext1Module.setDescription(ext1Module.getDescription().toUpperCase());
        ext1Module.printWelcomeMessage();

        assertEquals("GOOD DAY" + System.lineSeparator(), outContent.toString());
    }
}
