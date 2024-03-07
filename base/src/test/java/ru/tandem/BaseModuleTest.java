package ru.tandem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BaseModuleTest {
    private BaseModule baseModule;
    private String description;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;


    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        baseModule = new BaseModule();
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testSetAndGetDescription() {
        baseModule.setDescription("Good day");
        assertEquals("Good day", baseModule.getDescription());
    }

    @Test
    void testPrintWelcomeMessage() {
        baseModule.printWelcomeMessage();
        assertEquals("Good day" + System.lineSeparator(), outContent.toString());
    }


}
