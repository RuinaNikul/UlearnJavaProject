package ru.institute.pojo;

import com.opencsv.bean.CsvBindByName;

public class Country {
    @CsvBindByName(column = "ru.institute.pojo.Country")
    private String name;
    @CsvBindByName(column = "Region")
    private String region;
    @CsvBindByName(column = "Happiness Rank")
    private String happinessRank;
    @CsvBindByName(column = "Happiness Score")
    private String happinessScore;
    @CsvBindByName(column = "Standard Error")
    private String standardError;
    @CsvBindByName(column = "Economy (GDP per Capita)")
    private String economy;
    @CsvBindByName(column = "Family")
    private String family;
    @CsvBindByName(column = "Health (Life Expectancy)")
    private String health;
    @CsvBindByName(column = "Freedom")
    private String freedom;
    @CsvBindByName(column = "Trust (Government Corruption)")
    private String trust;
    @CsvBindByName(column = "Generosity")
    private String generosity;
    @CsvBindByName(column = "Dystopia Residual")
    private String dystopiaResidual;

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public String getHappinessRank() {
        return happinessRank;
    }

    public String getHappinessScore() {
        return happinessScore;
    }

    public String getStandardError() {
        return standardError;
    }

    public String getEconomy() {
        return economy;
    }

    public String getFamily() {
        return family;
    }

    public String getHealth() {
        return health;
    }

    public String getFreedom() {
        return freedom;
    }

    public String getTrust() {
        return trust;
    }

    public String getGenerosity() {
        return generosity;
    }

    public String getDystopiaResidual() {
        return dystopiaResidual;
    }

    @Override
    public String toString() {
        return "StatisticData{" +
                "country='" + name + '\'' +
                ", region='" + region + '\'' +
                ", happinessRank='" + happinessRank + '\'' +
                ", happinessScore='" + happinessScore + '\'' +
                ", standardError='" + standardError + '\'' +
                ", economy='" + economy + '\'' +
                ", family='" + family + '\'' +
                ", health='" + health + '\'' +
                ", freedom='" + freedom + '\'' +
                ", trust='" + trust + '\'' +
                ", generosity='" + generosity + '\'' +
                ", dystopiaResidual='" + dystopiaResidual + '\'' +
                '}';
    }

}
