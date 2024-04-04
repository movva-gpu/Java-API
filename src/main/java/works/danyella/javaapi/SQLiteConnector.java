package works.danyella.javaapi;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteConnector {

    public static final String SQLITE_FOLDER = System.getProperty("user.home") + File.separator +
        "." + (JavaApi.PROPERTIES.containsKey("app.folder_name") ?
        JavaApi.PROPERTIES.getString("app.folder_name") : "java-api")
        .toLowerCase() + File.separator + "sqlite";

    public static final String SQLITE_URL = "jdbc:sqlite:" + SQLITE_FOLDER +
        File.separator + "java-api.sqlite";

    public static void createDatabaseFolder() {
        File sqliteFolder = new File(SQLITE_FOLDER);

        if (!sqliteFolder.exists()) {
            JavaApi.LOGGER.warn("SQLite folder does not exist, creating it...");
            if (!sqliteFolder.mkdirs()) {
                JavaApi.LOGGER.error("Failed to create SQLite folder");
                System.exit(1);
            } else {
                JavaApi.LOGGER.info("SQLite folder created successfully");
            }
        }
    }

    public static void createUsersTable() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            statement.setQueryTimeout(JavaApi.PROPERTIES.containsKey("sqlite.timeout") ?
                Integer.parseInt(JavaApi.PROPERTIES.getString("sqlite.timeout")) : 30);

            statement.executeUpdate("create table if not exists users (" +
                "id integer primary key autoincrement, token text, username text not null, password text not null," +
                "email text not null, biography text, pronouns text, tasks_permissions text, avatar text," +
                "banner text, last_login date, created_at timestamp not null default current_timestamp" +
            ")");

        } catch (SQLException e) {
            JavaApi.LOGGER.error("Failed to create users table", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        createDatabaseFolder();
        return DriverManager.getConnection(SQLITE_URL);
    }
}
