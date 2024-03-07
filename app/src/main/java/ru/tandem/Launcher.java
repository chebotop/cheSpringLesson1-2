package ru.tandem;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

        try (FileInputStream input = new FileInputStream(propertiesPath)) {
            Properties properties = new Properties();
            properties.load(input);

            String modulesProperty = properties.getProperty("modules");

            if (modulesProperty == null || modulesProperty.trim().isEmpty()) {
                logger.log(Level.SEVERE, "No modules defined in app.properties");
                return;
            }

            // Разделяем список модулей и формируем список путей к файлам конфигурации
            String[] moduleNames = modulesProperty.split(",");
            List<String> configLocations = new ArrayList<>();
            for (String moduleName : moduleNames) {
                moduleName = moduleName.trim(); // Удаляем лишние пробелы
                if (!moduleName.isEmpty()) {
                    configLocations.add(moduleName + "/" + moduleName + ".spring.xml");
                }
            }

            // Создаем ApplicationContext, используя XML файлы конфигурации модулей
            ApplicationContext context = new ClassPathXmlApplicationContext(
                    configLocations.toArray(new String[0])
            );

            // ЛОГГЕР ДЛЯ ПРОВЕРКИ ВЫВОДА
            if (context.containsBean("baseModule")) {
                logger.log(Level.INFO, "Bean 'baseModule' found in the context.");
            } else {
                logger.log(Level.SEVERE, "Bean 'baseModule' not found in the context.");
            }
            if (context.containsBean("ext1Module")) {
                logger.log(Level.INFO, "Bean 'ext1Module' found in the context.");
            } else {
                logger.log(Level.SEVERE, "Bean 'ext1Module' not found in the context.");
            }
            if (context.containsBean("ext2Module")) {
                logger.log(Level.INFO, "Bean 'ext2Module' found in the context.");
            } else {
                logger.log(Level.SEVERE, "Bean 'ext2Module' not found in the context.");
            }
            BaseModule baseModule = context.getBean("baseModule", BaseModule.class);
            Ext1Module ext1Module = context.getBean("ext1Module", Ext1Module.class);
            Ext2Module ext2Module = context.getBean("ext2Module", Ext2Module.class);
            baseModule.printWelcomeMessage();
            ext1Module.printWelcomeMessage();
            ext2Module.printWelcomeMessage();
            String description = baseModule.getDescription();
            logger.log(Level.INFO, "Description: {0}", description);
            // ApplicationContext создан и бины инициализированы
            // Дополнительные действия с бинами здесь не требуются согласно вашим указаниям

        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred while loading the application properties.", e);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred while initializing the application context.", e);
        }
    }
}
