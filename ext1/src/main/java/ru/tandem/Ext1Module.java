package ru.tandem;


public class Ext1Module extends BaseModule {
    @Override
    public void printWelcomeMessage() {
        // Используйте геттер для получения значения свойства 'description'
        String greet = getDescription();
        if (greet != null) {
            // Используйте сеттер для установки нового значения свойства 'description'
            System.out.println(greet.toUpperCase());
        }
    }
}
