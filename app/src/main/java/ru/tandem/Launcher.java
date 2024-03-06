package ru.tandem;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Launcher {
    private static final Logger logger = Logger.getLogger(Launcher.class.getName());
    private static final String PROPERTIES_PATH = "src/main/resources/app/app.properties";

    public static void main(String[] args) {
        System.out.println("Loading properties from: " + PROPERTIES_PATH);

        try (InputStream input = Launcher.class.getClassLoader().getResourceAsStream("app/app.properties")) {
            if (input == null) {
                throw new IOException("app.properties not found");
            }
            Properties properties = new Properties();
            properties.load(input);

            String modulesProperty = properties.getProperty("modules");
            if (modulesProperty == null || modulesProperty.trim().isEmpty()) {
                System.out.println("No modules defined in app.properties");
                return;
            }

            try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("base/src/main/resources/base/base.spring.xml", "ext1/src/main/resources/ext1/ext1.spring.xml", "ext2/src/main/resources/ext2/ext2.spring.xml")) {
                context.getBean("baseModule", BaseModule.class).printWelcomeMessage();
                context.getBean("ext1Module", BaseModule.class).printWelcomeMessage();
                context.getBean("ext2Module", BaseModule.class).printWelcomeMessage();
            }

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to load app.properties file.", e);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred while initializing the application context.", e);
        }
    }
}
