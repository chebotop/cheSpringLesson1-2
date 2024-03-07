package ru.tandem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Используем SpringExtension для интеграции Spring TestContext Framework с JUnit Jupiter
@ExtendWith(SpringExtension.class)
// Указываем конфигурационные файлы для загрузки контекста
@ContextConfiguration(locations = {"classpath:base.spring.xml", "classpath:ext1.spring.xml", "classpath:ext2.spring.xml"})
public class LauncherTest {

    // Автоматически внедряем ApplicationContext, предоставляемый Spring TestContext Framework
    @Autowired
    private ApplicationContext context;

    @Test
    public void contextLoads() {
        // Проверяем, что контекст не null
        assertNotNull(context, "The application context should be loaded");
    }

    @Test
    public void baseModuleBeanAvailable() {
        // Проверяем, что бин baseModule доступен в контексте
        assertTrue(context.containsBean("baseModule"), "Bean 'baseModule' should be defined in the context");
    }

    @Test
    public void baseModuleBeanFunctionality() {
        // Получаем бин baseModule и проверяем его функциональность
        BaseModule baseModule = context.getBean("baseModule", BaseModule.class);
        assertNotNull(baseModule, "The 'baseModule' bean should not be null");
        assertNotNull(baseModule.getDescription(), "The 'description' property of 'baseModule' should not be null");

        // Проверяем, что метод printWelcomeMessage() работает корректно
        // В реальном тесте здесь может быть проверка вывода в консоль или другая логика
        baseModule.printWelcomeMessage();
    }
}
