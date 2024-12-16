package ru.institute.service;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.category.DefaultCategoryDataset;
import ru.institute.pojo.CountryHappiness;

import java.awt.*;
import java.util.List;

public class HappinessChartService extends ApplicationFrame {

    public HappinessChartService(String title, List<CountryHappiness> data) {
        super(title);
        JFreeChart chart = createChart(data);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);
    }

    private JFreeChart createChart(List<CountryHappiness> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (CountryHappiness countryHappiness : data) {
            dataset.addValue(countryHappiness.getHappinessScore(), "Happiness Score", countryHappiness.getCountry());
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "Happiness Score by ru.institute.pojo.Country", // Chart Title
                "ru.institute.pojo.Country", // X-Axis Label
                "Happiness Score", // Y-Axis Label
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false
        );
        CategoryPlot plot = barChart.getCategoryPlot();
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90); // Повернули подписи на 45 градусов


        barChart.setBackgroundPaint(Color.white);
        plot.setBackgroundPaint(Color.lightGray);
        return barChart;
    }

}