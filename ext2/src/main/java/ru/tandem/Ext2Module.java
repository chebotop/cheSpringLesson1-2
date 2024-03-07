package ru.tandem;


public class Ext2Module extends BaseModule {
    @Override
    public void printWelcomeMessage() {
        String greet = getDescription().toUpperCase();

        System.out.println(greet + greet);
    }
}
