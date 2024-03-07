package ru.tandem;


public class Ext1Module extends BaseModule {
    @Override
    public void printWelcomeMessage() {
        // Используйте геттер для получения значения свойства 'description'
        String greet = getGreet();
        if (greet != null) {
            greet = greet.toUpperCase();
            // Используйте сеттер для установки нового значения свойства 'description'
            System.out.println(greet);
        }
    }
}
