package ru.institute;

import com.opencsv.bean.CsvToBeanBuilder;
import org.jfree.chart.ui.UIUtils;
import ru.institute.pojo.Country;
import ru.institute.pojo.CountryHappiness;
import ru.institute.service.HappinessChartService;
import ru.institute.service.ServiceDb;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HappinessIndicatorApp {
    public static void main(String[] args) throws SQLException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Введите пожалуйста, путь для CSV-файла");
        System.out.println("Пример ввода: C:\\javaProLessons\\happiness-indicator\\Показатель счастья по странам 2015.csv");
        String csvFilePath = reader.readLine();


        List<Country> beans = new CsvToBeanBuilder(new FileReader(csvFilePath))
                .withType(Country.class)
                .build()
                .parse();

        System.out.println("Введите пожалуйста, путь до файла базы данных");
        System.out.println("Пример ввода: C:\\javaProLessons\\happiness-indicator\\happinessIndicator.db");
        String dbFilePath = reader.readLine();

        Statement statement = ServiceDb.getStatement(dbFilePath);
        ServiceDb.createTables(statement);
        ServiceDb.insertData(statement, beans);
        ResultSet rs = ServiceDb.selectMaxHappinessIndicator(statement);
        System.out.println("Страна с самым высоким показателем счастья среди \"Latin America and Caribbean\" и \"Eastern Asia\": ");
        System.out.println(rs.getString("country"));
        System.out.println("happinessScore = " + rs.getString("maxHappinessScore"));

        rs = ServiceDb.selectAvgHappinessIndicator(statement);

        System.out.println("Страна с самым средним показателем счастья среди \"Western Europe\" и \"North America\": ");
        System.out.println(rs.getString("country"));
        System.out.println("happinessScore = " + rs.getString("happinessScore"));

        List<CountryHappiness> data = new ArrayList<>();
        beans.forEach(bean ->  data.add(new CountryHappiness(bean.getName(), Double.parseDouble(bean.getHappinessScore()))));

        HappinessChartService chart = new HappinessChartService("Happiness Chart", data);
        chart.pack();
        UIUtils.centerFrameOnScreen(chart);
        chart.setVisible(true);

    }
}