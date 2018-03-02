package com.sharvari.engrosswomenhodd.Pojos;

/**
 * Created by sharvaridivekar on 02/03/18.
 */

public class Address {

    String type, line1, landmark,area,city, pincode,country;

    public Address() {
    }

    public Address(String type, String line1, String landmark, String area, String city, String pincode, String country) {
        this.type = type;
        this.line1 = line1;
        this.landmark = landmark;
        this.area = area;
        this.city = city;
        this.pincode = pincode;
        this.country = country;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
