package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;


public class Map2Activity extends Activity {


    MapView mapView;

    AMap aMap = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        AMapLocationClient.updatePrivacyShow(Map2Activity.this,true,true);
        AMapLocationClient.updatePrivacyAgree(Map2Activity.this,true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapView=  findViewById(R.id.map);

        if(aMap==null){
            aMap = mapView.getMap();

            setUpMap();
        }

    }

    public void setUpMap(){

        aMap.getUiSettings().setZoomControlsEnabled(false);
        aMap.setLocationSource(new LocationSource() {
            @Override
            public void activate(OnLocationChangedListener onLocationChangedListener) {

            }

            @Override
            public void deactivate() {

            }
        });


        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
    }
}
