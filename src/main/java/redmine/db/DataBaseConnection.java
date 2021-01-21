package redmine.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import lombok.SneakyThrows;

import static redmine.Property.getIntegerProperty;
import static redmine.Property.getStringProperty;

public class DataBaseConnection {
    private String dbHost;
    private Integer dbPort;
    private String dbUser;
    private String dbPass;
    private String dbName;
    private Connection connection;

    public DataBaseConnection() {
        initVariables();
        connect();
    }

    private void initVariables() {
        dbHost = getStringProperty("dbHost");
        dbPort = getIntegerProperty("dbPort");
        dbUser = getStringProperty("dbUser");
        dbPass = getStringProperty("dbPass");
        dbName = getStringProperty("dbName");
    }

    @SneakyThrows
    @Step("Подключение к базе данных")
    private void connect() {
        Class.forName("org.postgresql.Driver");
        String url = String.format("jdbc:postgresql://%s:%d/%s?user=%s&password=%s", dbHost, dbPort, dbName, dbUser, dbPass);
        connection = DriverManager.getConnection(url);
    }

    /**
     * Выполняет SQL-запрос и возвращает результат
     *
     * @param query - sql-запрос
     * @return данные - результат запроса
     */

    @SneakyThrows
    @Step("Выполнение SQL запроса")
    public List<Map<String, Object>> executeQuery(String query) {
        Allure.addAttachment("query", query);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        int count = resultSet.getMetaData().getColumnCount();
        List<String> columnNames = new ArrayList<>();
        List<Map<String, Object>> result = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            String columnName = resultSet.getMetaData().getColumnName(i);
            columnNames.add(columnName);
        }
        while (resultSet.next()) {
            Map<String, Object> columnData = new TreeMap<>();
            for (String columnName : columnNames) {
                Object value = resultSet.getObject(columnName);
                columnData.put(columnName, value);
            }
            result.add(columnData);
        }
        Allure.addAttachment("response", result.toString());
        return result;
    }

    /**
     * Выполняет SQL запрос с подготовлением данных и возвращает результат
     *
     * @param query      - sql-запрос
     * @param parameters - параметры, подставляемые в запросы
     * @return данные - результат запрос
     */
    @SneakyThrows
    @Step("Выполнение SQL запроса")
    public List<Map<String, Object>> executePreparedQuery(String query, Object... parameters) {
        PreparedStatement statement = connection.prepareStatement(query);
        int index = 1;
        for (Object object : parameters) {
            statement.setObject(index++, object);
        }
        Allure.addAttachment("query", statement.toString());
        ResultSet resultSet = statement.executeQuery();
        int count = resultSet.getMetaData().getColumnCount();
        List<String> columnNames = new ArrayList<>();
        List<Map<String, Object>> result = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            String columnName = resultSet.getMetaData().getColumnName(i);
            columnNames.add(columnName);
        }
        while (resultSet.next()) {
            Map<String, Object> columnData = new TreeMap<>();
            for (String columnName : columnNames) {
                Object value = resultSet.getObject(columnName);
                columnData.put(columnName, value);
            }
            result.add(columnData);
        }
        Allure.addAttachment("response", result.toString());
        return result;
    }
}
