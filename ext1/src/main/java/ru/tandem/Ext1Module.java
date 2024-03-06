package ru.tandem;


public class Ext1Module extends BaseModule {

    @Override
    public void printWelcomeMessage() {
        setDescription(getDescription().toUpperCase());
        System.out.println(getDescription());
    }
}
