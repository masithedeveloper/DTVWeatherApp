package com.dvtweather.android.model;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Masi Stoto on 08/01/17.
 */

public class GetLocationRequest {

    private GetLocationRequest() {
        // This class is not publicly instantiable
    }
    //----------------------------------------------------------------------------------------------
    public static class ServerGetLocationRequest {
        //------------------------------------------------------------------------------------------
        public ServerGetLocationRequest() {
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
        //------------------------------------------------------------------------------------------
    }
    //----------------------------------------------------------------------------------------------
}
