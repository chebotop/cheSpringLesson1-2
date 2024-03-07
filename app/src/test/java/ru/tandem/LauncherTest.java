package ru.tandem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class) // Интеграция Spring с JUnit 5
@ContextConfiguration(locations = {"classpath:base/base.spring.xml",
                                   "classpath:ext1/ext1.spring.xml",
                                   "classpath:ext2/ext2.spring.xml"})
class LauncherTest {

    @Autowired
    private ApplicationContext context; // Внедрение контекста Spring

    @Test
    void contextLoads() {
        assertNotNull(context, "The application context should be loaded");
    }

    @Test
    void baseModuleBeanAvailable() {
        assertTrue(context.containsBean("baseModule"), "Bean 'baseModule' should be defined in the context");
    }

    @Test
    void baseModuleBeanFunctionality() {
        BaseModule baseModule = context.getBean("baseModule", BaseModule.class);
        assertNotNull(baseModule, "The 'baseModule' bean should not be null");
        baseModule.printWelcomeMessage();
        String description = baseModule.getDescription();
        assertNotNull(description, "The 'description' property of 'baseModule' should not be null");
    }
}
