package com.dvtweather.android.presentable.weather;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.widget.ANImageView;
import com.dvtweather.android.R;
import com.dvtweather.android.communication.LocationPreferencesHelper;
import com.dvtweather.android.model.WeatherDisplayModel;
import com.dvtweather.android.presentable.base.BaseActivity;
import com.dvtweather.android.utilities.CommonUtils;
import com.dvtweather.android.utilities.ImageHandler;
import com.dvtweather.android.utilities.RoundedImageView;

import javax.inject.Inject;
import butterknife.ButterKnife;

/**
 * Created by Masi Stoto on 27/01/17.
 */

public class WeatherActivity extends BaseActivity implements WeatherMvpView {

    @Inject
    WeatherMvpPresenter<WeatherMvpView> mPresenter;

    TextView weather_sync_date;
    RoundedImageView weather_image;
    TextView weather_max_temperature_value;
    TextView weather_min_temperature_value;
    TextView weather_location_city;
    TextView weather_location_country;

    //----------------------------------------------------------------------------------
    public static Intent getStartIntent(Context context) {
        return new Intent(context, WeatherActivity.class);
    }
    //----------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        setUpUI();
        mPresenter.onAttach(this);
        LocationPreferencesHelper locationPreferences = new LocationPreferencesHelper(getApplicationContext());
        if (locationPreferences.getLatitude() != null && locationPreferences.getLongitude() != null)
            mPresenter.getWeather(locationPreferences.getLatitude(), locationPreferences.getLongitude()); // make a call
        else {
            Toast.makeText(this, "No GPS coordinates found, using default.", Toast.LENGTH_SHORT).show();
            mPresenter.getWeather("34.56", "25.79"); // make a call
        }
    }
    //----------------------------------------------------------------------------------
    private void setUpUI() {
        weather_sync_date = (TextView) findViewById(R.id.weather_sync_date);
        weather_image = (RoundedImageView) findViewById(R.id.weather_image);
        weather_max_temperature_value = (TextView) findViewById(R.id.weather_max_temperature_value);
        weather_min_temperature_value = (TextView) findViewById(R.id.weather_min_temperature_value);
        weather_location_city = (TextView) findViewById(R.id.weather_location_city);
        weather_location_country = (TextView) findViewById(R.id.weather_location_country);
    }
    //----------------------------------------------------------------------------------
    @Override
    public void onBackPressed() {
        mPresenter.onDetach();
        finish();
    }
    //----------------------------------------------------------------------------------
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    //----------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------
    @Override
    public void setWeather(WeatherDisplayModel getWeatherResponse) {
        weather_sync_date.setText(CommonUtils.getToday());
        weather_max_temperature_value.setText(getWeatherResponse.getMax_tem() + "\u2103");
        weather_min_temperature_value.setText(getWeatherResponse.getMin_tem()+ "\u2103");
        new ImageHandler(this).display_image_from_url(weather_image, getWeatherResponse.getIcon_url() );
        weather_location_city.setText(getWeatherResponse.getCity()+ ", ");
        weather_location_country.setText(getWeatherResponse.getCountry());
    }
    //----------------------------------------------------------------------------------
}