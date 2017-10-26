package com.dvtweather.android.presentable.weather;

import android.util.Log;

import com.androidnetworking.error.ANError;
import com.dvtweather.android.R;
import com.dvtweather.android.communication.ApiEndPoint;
import com.dvtweather.android.communication.AppApiHelper;
import com.dvtweather.android.model.GetWeatherRequest;
import com.dvtweather.android.model.GetWeatherResponse;
import com.dvtweather.android.model.WeatherDisplayModel;
import com.dvtweather.android.presentable.base.BasePresenter;
import com.dvtweather.android.utilities.rx.SchedulerProvider;
import javax.inject.Inject;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Masi Stoto on 27/01/17.
 */

public class WeatherPresenter<V extends WeatherMvpView> extends BasePresenter<V> implements WeatherMvpPresenter<V>{
    //---------------------------------------------------------------------------------
    @Inject
    public WeatherPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(schedulerProvider, compositeDisposable);
    }
    //---------------------------------------------------------------------------------
    @Override
    public void getWeather(String lat, String lon) {
        getMvpView().showLoading();
        getCompositeDisposable().add(new AppApiHelper().makeWeatherApiCall(new GetWeatherRequest.ServerGetWeatherRequest(lon, lat))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<GetWeatherResponse>() {
                    @Override
                    public void accept(GetWeatherResponse response) throws Exception { // spend 15-20min into this
                        Log.i("response", response.toString());
                        if (!isViewAttached()) {
                            return;
                        }

                        WeatherDisplayModel weatherDisplayModel = new WeatherDisplayModel();
                        weatherDisplayModel.setMax_tem(String.valueOf((int) Math.round(response.main.getTempMax()- 273.15F)));
                        weatherDisplayModel.setMin_tem(String.valueOf((int) Math.round(response.main.getTempMin()- 273.15F)));
                        weatherDisplayModel.setIcon_url(response.weather[0].getIcon());
                        weatherDisplayModel.setCity(response.getName());
                        weatherDisplayModel.setCountry(String.valueOf(response.sys.getCountry()));

                        try { // we have data conversion which might fail thus, crashing the app.
                            getMvpView().setWeather(weatherDisplayModel);
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                        getMvpView().hideLoading();
                    }

                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();

                        // handle the login error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));
    }
    //---------------------------------------------------------------------------------
}
