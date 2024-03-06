package ru.tandem;

public class BaseModule {
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void printWelcomeMessage() {
        System.out.println("Good day");
    }
}
