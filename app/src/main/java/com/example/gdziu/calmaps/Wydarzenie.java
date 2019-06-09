package com.example.gdziu.calmaps;

import java.io.Serializable;

public class Wydarzenie implements Serializable {
    private int _id;
    private String summary;
    private String location;
    private String startDate;
    public Wydarzenie(){}
    public Wydarzenie(String summary, String location, String startDate) {
        super();
        this.summary = summary;
        this.location = location;
        this.startDate = startDate;
    }
    @Override
    public String toString() {
        return "Wydarzenie: [id=" + _id + ", summary=" + summary + ", location=" + location
                + " startDate"+startDate+" ]";
    }
    public String getSummary() { return summary; }
    public String getLocation() { return location; }
    public String getStartDate() { return startDate; }
    public int getId() { return _id; }
    public void setId(int id) { this._id = id; }
}
