package com.kkh.kronikare;

import java.util.ArrayList;
import java.util.List;

public class Service {

    private static Service service;
    private String relation;
    private String city;
    private String type;
    private String startDate;
    private String durationInMonths;
    private String caretakerGender;
    private List<String> languages;

    private Service() {
        languages=new ArrayList<>();
    }

    public static synchronized Service getService()
    {
        if(service==null)
        {
            service=new Service();
        }
        return service;
    }

    public String getRelation() {
        return relation;
    }

    public String getCity() {
        return city;
    }

    public String getType() {
        return type;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getDurationInMonths() {
        return durationInMonths;
    }

    public String getCaretakerGender() {
        return caretakerGender;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setDurationInMonths(String durationInMonths) {
        this.durationInMonths = durationInMonths;
    }

    public void setCaretakerGender(String caretakerGender) {
        this.caretakerGender = caretakerGender;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }
}
