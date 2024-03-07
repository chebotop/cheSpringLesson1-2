package ru.tandem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BaseModuleTest {
    private BaseModule baseModule;
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
    void testSetAndGetGreet() {
        baseModule.setGreet("Good day");
        assertEquals("Good day", baseModule.getGreet());
    }

    @Test
    void testPrintWelcomeMessage() {
        baseModule.setGreet("Good day");
        baseModule.printWelcomeMessage();
        assertEquals("Good day" + System.lineSeparator(), outContent.toString());
        assertEquals("Good day", baseModule.getGreet());
    }


}
