package ru.institute.service;

import ru.institute.pojo.Country;

import java.sql.*;
import java.util.List;

public class ServiceDb {
    public static Statement getStatement(String path) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + path);
        return connection.createStatement();
    }
    public static void createTables(Statement statement) throws SQLException {
        statement.execute("CREATE TABLE IF NOT EXISTS CountryInfo (\n" +
                "  country_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  country TEXT UNIQUE,\n" +
                "  region TEXT\n" +
                ");\n");
        statement.execute("CREATE TABLE IF NOT EXISTS HappinessIndicators (\n" +
                "  indicator_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  country_id INTEGER,\n" +
                "  happinessRank INTEGER,\n" +
                "  happinessScore REAL,\n" +
                "  standardError REAL,\n" +
                "  economy REAL,\n" +
                "  family REAL,\n" +
                "  health REAL,\n" +
                "  freedom REAL,\n" +
                "  trust REAL,\n" +
                "  generosity REAL,\n" +
                "  dystopiaResidual REAL,\n" +
                "  FOREIGN KEY (country_id) REFERENCES CountryInfo(country_id)\n" +
                ");");
    }

    public static void insertData(Statement statement, List<Country> countries) {
        countries.forEach(country -> {
            try {
                statement.execute("INSERT INTO CountryInfo (country, region) VALUES ('" + country.getName() + "', '" + country.getRegion() + "');\n");
                ResultSet rs = statement.executeQuery("SELECT * FROM CountryInfo WHERE country = '" + country.getName() + "';");
                statement.execute("INSERT INTO HappinessIndicators (country_id, happinessRank, happinessScore, standardError, " +
                        "economy, family, health, freedom, trust, generosity, dystopiaResidual)\n" +
                        "VALUES (" + rs.getInt("country_id") + ", " + country.getHappinessRank() + ", " + country.getHappinessScore()
                        + ", " + country.getStandardError()
                        + ", " + country.getEconomy() + ", " + country.getFamily() + ", " + country.getHealth() + ", " + country.getFreedom()
                        + ", " + country.getTrust() + ", " + country.getGenerosity() + ", " + country.getDystopiaResidual() + ");");
            } catch (SQLException e) {
                if (!e.getMessage().contains("(UNIQUE constraint failed: CountryInfo.country)")) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public static ResultSet selectMaxHappinessIndicator(Statement statement) throws SQLException {
        return statement.executeQuery("SELECT\n" +
                "  ci.country,\n" +
                "  hi.happinessScore AS maxHappinessScore\n" +
                "FROM CountryInfo AS ci\n" +
                "JOIN HappinessIndicators AS hi\n" +
                "  ON ci.country_id = hi.country_id\n" +
                "WHERE\n" +
                "  ci.region = 'Latin America and Caribbean' OR ci.region = 'Eastern Asia'\n" +
                "GROUP BY\n" +
                "  ci.country\n" +
                "ORDER BY\n" +
                "  maxHappinessScore DESC\n" +
                "LIMIT 1;");
    }

    public static ResultSet selectAvgHappinessIndicator(Statement statement) throws SQLException {
        return statement.executeQuery("SELECT T1.country, T2.happinessScore\n" +
                "FROM CountryInfo AS T1\n" +
                "INNER JOIN HappinessIndicators AS T2 ON T1.country_id = T2.country_id\n" +
                "WHERE T1.region = 'Western Europe' OR T1.region = 'North America'\n" +
                "ORDER BY ABS(T2.happinessScore - (SELECT AVG(happinessScore) FROM HappinessIndicators AS T3 INNER JOIN CountryInfo AS T4 ON T3.country_id = T4.country_id WHERE T4.region = 'Western Europe' OR T4.region = 'North America'))\n" +
                "LIMIT 1;");
    }
}
