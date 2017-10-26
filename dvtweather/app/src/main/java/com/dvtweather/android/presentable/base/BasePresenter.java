package com.dvtweather.android.presentable.base;

/**
 * Created by Masi Stoto on 27/01/17.
 */

import android.util.Log;

import com.androidnetworking.common.ANConstants;
import com.androidnetworking.error.ANError;
import com.dvtweather.android.model.ApiError;
import com.dvtweather.android.service.ServiceManager;
import com.dvtweather.android.utilities.rx.SchedulerProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.dvtweather.android.utilities.AppConstants;

import javax.inject.Inject;
import javax.net.ssl.HttpsURLConnection;

import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private static final String TAG = "BasePresenter";

    //private final ServiceManager mServiceManager;
    private final SchedulerProvider mSchedulerProvider;
    private final CompositeDisposable mCompositeDisposable;

    private V mMvpView;
    //----------------------------------------------------------------------------------
    @Inject
    public BasePresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        //this.mServiceManager = mServiceManager;
        this.mSchedulerProvider = schedulerProvider;
        this.mCompositeDisposable = compositeDisposable;
    }
    //----------------------------------------------------------------------------------
    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }
    //----------------------------------------------------------------------------------
    @Override
    public void onDetach() {
        mCompositeDisposable.dispose();
        mMvpView = null;
    }
    //----------------------------------------------------------------------------------
    public boolean isViewAttached() {
        return mMvpView != null;
    }
    //----------------------------------------------------------------------------------
    public V getMvpView() {
        return mMvpView;
    }
    //----------------------------------------------------------------------------------
    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }
    //----------------------------------------------------------------------------------
    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }
    //----------------------------------------------------------------------------------
    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }
    //----------------------------------------------------------------------------------
    @Override
    public void handleApiError(ANError error) {
        if (error == null || error.getErrorBody() == null) {
            getMvpView().onError("error");
            return;
        }

        if (error.getErrorCode() == AppConstants.API_STATUS_CODE_LOCAL_ERROR
                && error.getErrorDetail().equals(ANConstants.CONNECTION_ERROR)) {
            getMvpView().onError("error");
            return;
        }

        if (error.getErrorCode() == AppConstants.API_STATUS_CODE_LOCAL_ERROR
                && error.getErrorDetail().equals(ANConstants.REQUEST_CANCELLED_ERROR)) {
            getMvpView().onError("error");
            return;
        }

        final GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        final Gson gson = builder.create();

        try {
            ApiError apiError = gson.fromJson(error.getErrorBody(), ApiError.class);

            if (apiError == null || apiError.getMessage() == null) {
                getMvpView().onError("error");
                return;
            }

            switch (error.getErrorCode()) {
                case HttpsURLConnection.HTTP_UNAUTHORIZED:
                case HttpsURLConnection.HTTP_FORBIDDEN:
                case HttpsURLConnection.HTTP_INTERNAL_ERROR:
                case HttpsURLConnection.HTTP_NOT_FOUND:
                default:
                    getMvpView().onError(apiError.getMessage());
            }
        } catch (JsonSyntaxException | NullPointerException e) {
            Log.e(TAG, "handleApiError", e);
            getMvpView().onError("error");
        }
    }
    //----------------------------------------------------------------------------------
    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
    //----------------------------------------------------------------------------------
}
