package ru.tandem;

public class BaseModule {
    private String greet;


    public void setGreet(String greet) {
        this.greet = greet;
    }
    public String getGreet() {
        return greet;
    }

    public void printWelcomeMessage() {
        System.out.println(greet);
    }
}
