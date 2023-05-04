package com.sabre.interview.model;

import java.util.List;
import java.util.Objects;

public class University {
    private List<String> domains;
    private String country;
    private String alpha_two_code;
    private List<String> web_pages;
    private String name;

    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> domains) {
        this.domains = domains;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAlpha_two_code() {
        return alpha_two_code;
    }

    public void setAlpha_two_code(String alpha_two_code) {
        this.alpha_two_code = alpha_two_code;
    }

    public List<String> getWeb_pages() {
        return web_pages;
    }

    public void setWeb_pages(List<String> web_pages) {
        this.web_pages = web_pages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        University that = (University) o;
        return Objects.equals(domains, that.domains) && Objects.equals(country, that.country) && Objects.equals(alpha_two_code, that.alpha_two_code) && Objects.equals(web_pages, that.web_pages) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(domains, country, alpha_two_code, web_pages, name);
    }
}
