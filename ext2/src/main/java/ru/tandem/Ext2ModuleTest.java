package ru.tandem;


public class Ext2ModuleTest extends BaseModule {
    @Override
    public void printWelcomeMessage() {
        String greet = getDescription().toUpperCase();

        System.out.println(greet + greet);
    }
}
