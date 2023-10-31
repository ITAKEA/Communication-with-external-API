package ita.gptdemo.dto;

public class NameInfoResponse {
    private String name;
    private String gender;
    private double genderProbability;
    private int age;
    private int ageCount;
    private String country;
    private double countryProbability;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getGenderProbability() {
        return genderProbability;
    }

    public void setGenderProbability(double genderProbability) {
        this.genderProbability = genderProbability;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAgeCount() {
        return ageCount;
    }

    public void setAgeCount(int ageCount) {
        this.ageCount = ageCount;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getCountryProbability() {
        return countryProbability;
    }

    public void setCountryProbability(double countryProbability) {
        this.countryProbability = countryProbability;
    }
}

