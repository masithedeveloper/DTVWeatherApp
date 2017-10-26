package com.dvtweather.android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Masi Stoto on 08/01/17.
 */

public class GetWeatherResponse {

    public Main main = new Main();
    public Sys sys = new Sys();
    public Weather[] weather = new Weather[]{};
    public String name = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public class Coord{
        @Expose
        @SerializedName("lon")
        private double lon;

        public double getLon() { return this.lon; }

        public void setLon(double lon) { this.lon = lon; }
        @Expose
        @SerializedName("lat")
        private double lat;

        public double getLat() { return this.lat; }

        public void setLat(double lat) { this.lat = lat; }
    }

    public class Weather
    {
        @Expose
        @SerializedName("id")
        private int id;

        public int getId() { return this.id; }

        public void setId(int id) { this.id = id; }
        @Expose
        @SerializedName("main")
        private String main;

        public String getMain() { return this.main; }

        public void setMain(String main) { this.main = main; }
        @Expose
        @SerializedName("description")
        private String description;

        public String getDescription() { return this.description; }

        public void setDescription(String description) { this.description = description; }

        private String icon;

        public String getIcon() { return this.icon; }

        public void setIcon(String icon) { this.icon = icon; }
    }

    public class Main
    {
        @Expose
        @SerializedName("temp")
        private double temp;

        public double getTemp() { return this.temp; }

        public void setTemp(double temp) { this.temp = temp; }
        @Expose
        @SerializedName("pressure")
        private double pressure;

        public double getPressure() { return this.pressure; }

        public void setPressure(double pressure) { this.pressure = pressure; }

        private int humidity;

        public int getHumidity() { return this.humidity; }

        public void setHumidity(int humidity) { this.humidity = humidity; }
        @Expose
        @SerializedName("temp_min")
        private double temp_min;

        public double getTempMin() { return this.temp_min; }

        public void setTempMin(double temp_min) { this.temp_min = temp_min; }
        @Expose
        @SerializedName("temp_max")
        private double temp_max; // important

        public double getTempMax() { return this.temp_max; }

        public void setTempMax(double temp_max) { this.temp_max = temp_max; }
        @Expose
        @SerializedName("sea_level")
        private double sea_level;

        public double getSeaLevel() { return this.sea_level; }

        public void setSeaLevel(double sea_level) { this.sea_level = sea_level; }
        @Expose
        @SerializedName("grnd_level")
        private double grnd_level;

        public double getGrndLevel() { return this.grnd_level; }

        public void setGrndLevel(double grnd_level) { this.grnd_level = grnd_level; }
    }

    public class Wind
    {
        @Expose
        @SerializedName("id")
        private double speed;

        public double getSpeed() { return this.speed; }

        public void setSpeed(double speed) { this.speed = speed; }
        @Expose
        @SerializedName("deg")
        private double deg;

        public double getDeg() { return this.deg; }

        public void setDeg(double deg) { this.deg = deg; }
    }

    public class Clouds
    {
        @Expose
        @SerializedName("all")
        private int all;

        public int getAll() { return this.all; }

        public void setAll(int all) { this.all = all; }
    }

    public class Sys
    {
        @Expose
        @SerializedName("message")
        private double message;

        public double getMessage() { return this.message; }

        public void setMessage(double message) { this.message = message; }
        @Expose
        @SerializedName("country")
        private String country;

        public String getCountry() { return this.country; }

        public void setCountry(String country) { this.country = country; }

        private int sunrise;

        public int getSunrise() { return this.sunrise; }

        public void setSunrise(int sunrise) { this.sunrise = sunrise; }
        @Expose
        @SerializedName("sunset")
        private int sunset;

        public int getSunset() { return this.sunset; }

        public void setSunset(int sunset) { this.sunset = sunset; }
    }

    public class RootObject
    {
        @Expose
        @SerializedName("coord")
        private Coord coord;

        public Coord getCoord() { return this.coord; }

        public void setCoord(Coord coord) { this.coord = coord; }
        @Expose
        @SerializedName("weather")
        private ArrayList<Weather> weather;

        public ArrayList<Weather> getWeather() { return this.weather; }

        public void setWeather(ArrayList<Weather> weather) { this.weather = weather; }
        @Expose
        @SerializedName("base")
        private String base;

        public String getBase() { return this.base; }

        public void setBase(String base) { this.base = base; }
        @Expose
        @SerializedName("main")
        private Main main;

        public Main getMain() { return this.main; }

        public void setMain(Main main) { this.main = main; }
        @Expose
        @SerializedName("wind")
        private Wind wind;

        public Wind getWind() { return this.wind; }

        public void setWind(Wind wind) { this.wind = wind; }
        @Expose
        @SerializedName("clouds")
        private Clouds clouds;

        public Clouds getClouds() { return this.clouds; }

        public void setClouds(Clouds clouds) { this.clouds = clouds; }
        @Expose
        @SerializedName("dt")
        private int dt;

        public int getDt() { return this.dt; }

        public void setDt(int dt) { this.dt = dt; }
        @Expose
        @SerializedName("sys")
        private Sys sys;

        public Sys getSys() { return this.sys; }

        public void setSys(Sys sys) { this.sys = sys; }
        @Expose
        @SerializedName("id")
        private int id;

        public int getId() { return this.id; }

        public void setId(int id) { this.id = id; }
        @Expose
        @SerializedName("name")
        private String name;

        public String getName() { return this.name; }

        public void setName(String name) { this.name = name; }
        @Expose
        @SerializedName("cod")
        private int cod;

        public int getCod() { return this.cod; }

        public void setCod(int cod) { this.cod = cod; }

        @Override
        public String toString() {
            return "RootObject{" +
                    "coord=" + coord +
                    ", weather=" + weather +
                    ", base='" + base + '\'' +
                    ", main=" + main +
                    ", wind=" + wind +
                    ", clouds=" + clouds +
                    ", dt=" + dt +
                    ", sys=" + sys +
                    ", id=" + id +
                    ", name='" + name + '\'' +
                    ", cod=" + cod +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "";
    }
}
