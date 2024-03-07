package ru.tandem;


public class Ext2Module extends BaseModule {
    @Override
    public void printWelcomeMessage() {
        String greet = getGreet();
        if (greet != null) {
            greet = greet.toUpperCase() + greet.toUpperCase();
            System.out.println(greet);
        }

    }
}
