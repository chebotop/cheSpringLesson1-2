package ru.tandem;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Launcher {
    private static final Logger logger = Logger.getLogger(Launcher.class.getName());

    public static void main(String[] args) {
        // Проверяем, что аргумент с путем к файлу app.properties был предоставлен
        if (args.length != 1) {
            logger.log(Level.SEVERE, "Usage: Launcher <path-to-app.properties>");
            return;
        }

        String propertiesPath = args[0];
        System.out.println("Loading properties from: " + propertiesPath);

        try (FileInputStream input = new FileInputStream(propertiesPath)) {
            Properties properties = new Properties();
            properties.load(input);

            String modulesProperty = properties.getProperty("modules");
            if (modulesProperty == null || modulesProperty.trim().isEmpty()) {
                logger.log(Level.SEVERE, "No modules defined in app.properties");
                return;
            }

            // Разделяем список модулей и загружаем каждый файл конфигурации
            String[] moduleFiles = modulesProperty.split(",");
            // Создаем ApplicationContext, используя XML файлы конфигурации модулей
            try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(moduleFiles)) {
                // ApplicationContext создан и бины инициализированы
                // Дополнительные действия с бинами здесь не требуются согласно вашим указаниям
            }

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to load app.properties file.", e);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred while initializing the application context.", e);
        }
    }
}
