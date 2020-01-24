package util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {
    private final String filename = "/TypeDAO.properties";

public String getProp(String prop) throws IOException {
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename)){
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties.getProperty(prop);
        }
    }
}