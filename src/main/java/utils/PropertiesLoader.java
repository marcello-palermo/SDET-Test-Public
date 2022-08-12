package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PropertiesLoader {

    private Properties properties;

    public PropertiesLoader(String path) {
        try (InputStream inputStream = new FileInputStream(path)) {
            if (properties == null) {
                properties = new Properties();
                properties.load(inputStream);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Properties properties(){
        return properties;
    }
}