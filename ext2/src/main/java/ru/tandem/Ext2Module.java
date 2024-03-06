package ru.tandem;


public class Ext2Module extends BaseModule {

    @Override
    public void printWelcomeMessage() {
        setDescription(getDescription().toUpperCase() + getDescription().toUpperCase());
        System.out.println();
    }
}
