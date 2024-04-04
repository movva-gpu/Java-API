package works.danyella.javaapi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ResourceBundle;

@SpringBootApplication
public class JavaApi {

    public static final Logger LOGGER = LogManager.getLogger("java-api");
    public static final ResourceBundle PROPERTIES = ResourceBundle.getBundle("conf");

    public static void main(String[] args) {

        SQLiteConnector.createUsersTable();

        SpringApplication.run(JavaApi.class, args);

    }
}