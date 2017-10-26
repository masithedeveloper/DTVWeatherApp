package com.dvtweather.android.presentable.splash;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.dvtweather.android.R;
import com.dvtweather.android.service.SyncService;
import com.dvtweather.android.presentable.base.BaseActivity;
import com.dvtweather.android.presentable.weather.WeatherActivity;

import com.kishan.askpermission.AskPermission;
import com.kishan.askpermission.ErrorCallback;
import com.kishan.askpermission.PermissionCallback;
import com.kishan.askpermission.PermissionInterface;

import javax.inject.Inject;

import butterknife.ButterKnife;


/**
 * Created by Masi Stoto on 27/01/17.
 */

public class SplashActivity extends BaseActivity implements SplashMvpView, PermissionCallback, ErrorCallback {

    @Inject
    SplashMvpPresenter<SplashMvpView> mPresenter;

    //--------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        reqPermission();
    }
    //--------------------------------------------------------------------------------------
    @Override
    protected void onResume() {
        super.onResume();
    }
    //--------------------------------------------------------------------------------------
    private void reqPermission() {
        new AskPermission.Builder(this).setPermissions(
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_NETWORK_STATE)
                .setCallback(this)
                .setErrorCallback(this)
                .request(99);
    }
    //--------------------------------------------------------------------------------------
    @Override
    public void onPermissionsGranted(int requestCode){
        mPresenter.onAttach(SplashActivity.this);
    }
    //--------------------------------------------------------------------------------------
    @Override
    public void onPermissionsDenied(int requestCode){
        finish();
    }
    //--------------------------------------------------------------------------------------
    @Override
    public void onShowRationalDialog(final PermissionInterface permissionInterface, int requestCode){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("DvtWeather needs permissions for this app.");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                permissionInterface.onDialogShown();
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }
    //--------------------------------------------------------------------------------------
    @Override
    public void onShowSettings(final PermissionInterface permissionInterface, int requestCode){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("We need permissions for this app. Open setting screen?");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                permissionInterface.onSettingsShown();
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }
    //--------------------------------------------------------------------------------------
    /**
     * Making the screen wait so that the DVT logo can be shown
     */
    @Override
    public void openMainActivity() {
        startActivity(WeatherActivity.getStartIntent(SplashActivity.this));
        finish();
    }
    //--------------------------------------------------------------------------------------
    @Override
    public void startSyncService() {
        //reqPermission();
        SyncService.start(this); // should we rather request for permissions now??? ,maybe bafore this point
    }
    //--------------------------------------------------------------------------------------
    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }
    //--------------------------------------------------------------------------------------
}
