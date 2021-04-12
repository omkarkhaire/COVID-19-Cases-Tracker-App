package com.example.covid_19tracker;


//https://disease.sh/v3/covid-19/countries
public class countrymodel {
    String flag,country,cases,todaycases,totaldeaths,todaydeaths,recovered,active,critical,testspermillion;

     countrymodel()
     {

     }

    public countrymodel(String flag, String country, String cases, String todaycases, String totaldeaths, String todaydeaths, String recovered, String active, String critical, String testspermillion) {
        this.flag = flag;
        this.country = country;
        this.cases = cases;
        this.todaycases = todaycases;
        this.totaldeaths = totaldeaths;
        this.todaydeaths = todaydeaths;
        this.recovered = recovered;
        this.active = active;
        this.critical = critical;
        this.testspermillion = testspermillion;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getTodaycases() {
        return todaycases;
    }

    public void setTodaycases(String todaycases) {
        this.todaycases = todaycases;
    }

    public String getTotaldeaths() {
        return totaldeaths;
    }

    public void setTotaldeaths(String totaldeaths) {
        this.totaldeaths = totaldeaths;
    }

    public String getTodaydeaths() {
        return todaydeaths;
    }

    public void setTodaydeaths(String todaydeaths) {
        this.todaydeaths = todaydeaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }

    public String getTestspermillion() {
        return testspermillion;
    }

    public void setTestspermillion(String testspermillion) {
        this.testspermillion = testspermillion;
    }
}
