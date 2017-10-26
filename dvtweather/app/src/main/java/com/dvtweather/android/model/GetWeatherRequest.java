package com.dvtweather.android.model;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Masi Stoto on 08/01/17.
 */

public class GetWeatherRequest {

    private GetWeatherRequest() {
        // This class is not publicly instantiable
    }
    //----------------------------------------------------------------------------------------------
    public static class ServerGetWeatherRequest {
        @Expose
        @SerializedName("longitude")
        private String longitude;

        @Expose
        @SerializedName("latitude")
        private String latitude;
        //------------------------------------------------------------------------------------------
        public ServerGetWeatherRequest(String longitude, String latitude) {
            this.longitude = longitude;
            this.latitude = latitude;
        }
        //------------------------------------------------------------------------------------------
        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        //------------------------------------------------------------------------------------------
        public JSONObject toJson(){
            try {
                return new JSONObject(new Gson().toJson(this));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new JSONObject(); // just an empty this
        }
    }
    //----------------------------------------------------------------------------------------------
}
