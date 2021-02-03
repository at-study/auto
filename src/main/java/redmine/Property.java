package redmine;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

/**
 * Класс для работы с .properties.
 */

public class Property {
    private static Properties properties = new Properties();

    static {
        try {
            properties.load(new FileInputStream("src/test/resources/" +
                    Optional.ofNullable(System.getProperty("config"))
                            .orElse("local.properties")
            ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getStringProperty(String key) {
        return properties.getProperty(key);
    }

    public static Integer getIntegerProperty(String key) {
        return Integer.parseInt(getStringProperty(key));
    }

    public static Boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(getStringProperty(key));
    }


}
