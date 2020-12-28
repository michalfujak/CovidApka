package com.michalfujak.covid19.covidapka;

public class CountryModel
{
    // Variables
    public String updated;
    public String flag;
    public String country;
    public String cases;
    public String totalCases;
    public String deaths;
    public String totalDeaths;
    public String recovered;
    public String todayRecovered;
    public String active;
    public String critical;

    /**
     * construct:    CountryModel(null)
     * return:       null;
     */
    public CountryModel()
    {
    }

    /**
     * construct:    CountryModel(param 11x)
     * param:        updated
     * param:        flag
     * param:        country
     * param:        cases
     * param:        totalCases
     * param:        deaths
     * param:        totalDeaths
     * param:        recovered
     * param:        todayRecovered
     * param:        active
     * param:        critical
     */
    public CountryModel(String updated, String flag, String country, String cases, String totalCases, String deaths, String totalDeaths, String recovered, String todayRecovered, String active, String critical) {
        this.updated = updated;
        this.flag = flag;
        this.country = country;
        this.cases = cases;
        this.totalCases = totalCases;
        this.deaths = deaths;
        this.totalDeaths = totalDeaths;
        this.recovered = recovered;
        this.todayRecovered = todayRecovered;
        this.active = active;
        this.critical = critical;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
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

    public String getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(String totalCases) {
        this.totalCases = totalCases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(String totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getTodayRecovered() {
        return todayRecovered;
    }

    public void setTodayRecovered(String todayRecovered) {
        this.todayRecovered = todayRecovered;
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
}
