package runner;

import java.io.IOException;
import java.util.Properties;

public class Trial {

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.load(Trial.class.getClassLoader().getResourceAsStream("extent.properties"));

        System.out.println(properties.get("extent.reporter.klov.start"));
    }
}
