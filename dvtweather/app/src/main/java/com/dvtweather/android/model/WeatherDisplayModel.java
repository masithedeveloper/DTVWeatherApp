package com.dvtweather.android.model;

import com.androidnetworking.widget.ANImageView;

/**
 * Created by masi on 25/10/2017.
 */

public class WeatherDisplayModel {
    public String max_tem = "";
    public String min_tem = "";
    public ANImageView icon;
    public String city = "";
    public String country = "";

    public String getMax_tem() {
        return max_tem;
    }

    public void setMax_tem(String max_tem) {
        this.max_tem = max_tem;
    }

    public String getMin_tem() {
        return min_tem;
    }

    public void setMin_tem(String min_tem) {
        this.min_tem = min_tem;
    }

    public ANImageView getIcon() {
        return icon;
    }

    public void setIcon(ANImageView icon) {
        this.icon = icon;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
