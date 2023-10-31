package ita.gptdemo.dto;

import java.util.List;

public class NationalityResponse {
    private String name;
    private List<CountryProbability> country;

    public static class CountryProbability {
        private String country_id;
        private double probability;

        public String getCountry_id() {
            return country_id;
        }

        public void setCountry_id(String country_id) {
            this.country_id = country_id;
        }

        public double getProbability() {
            return probability;
        }

        public void setProbability(double probability) {
            this.probability = probability;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CountryProbability> getCountry() {
        return country;
    }

    public void setCountry(List<CountryProbability> country) {
        this.country = country;
    }
}

