package ru.institute.pojo;

public class CountryHappiness {
    private String country;

    public double getHappinessScore() {
        return happinessScore;
    }

    public String getCountry() {
        return country;
    }

    private double happinessScore;

    public CountryHappiness(String country, double happinessScore) {
        this.country = country;
        this.happinessScore = happinessScore;
    }
}
