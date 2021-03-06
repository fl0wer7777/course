package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.DriveStep;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

import static com.amap.api.services.route.RouteSearch.DRIVING_SINGLE_DEFAULT;

public class MapActtivity extends Activity {

    MapView mapView = null;
    AMap aMap = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AMapLocationClient.updatePrivacyShow(MapActtivity.this,true,true);
        AMapLocationClient.updatePrivacyAgree(MapActtivity.this,true);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mapView = findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);

        initPermissions();

        initMap();

      //  doSearchQuery();
    }

    AMapLocationClient mlocationClient;
    AMapLocationClientOption mLocationOption;
    LocationSource.OnLocationChangedListener mListener;

    public void initMap() {
        if (aMap==null){

            aMap = mapView.getMap();
            aMap.getUiSettings().setZoomControlsEnabled(false);
          //  aMap.setLocationSource(this);// ??????????????????
            aMap.setLocationSource(new LocationSource() {
                @Override
                public void activate(OnLocationChangedListener listener) {
                    mListener = listener;
                    if (mlocationClient == null) {
                        try {

                            mlocationClient = new AMapLocationClient(MapActtivity.this);
                            mLocationOption = new AMapLocationClientOption();
                            //??????????????????
                            mlocationClient.setLocationListener(new AMapLocationListener() {

                                @Override
                                public void onLocationChanged(AMapLocation amapLocation) {
                                    if (mListener != null && amapLocation != null) {
                                        if (amapLocation != null && amapLocation.getErrorCode() == 0) {
                                            mListener.onLocationChanged(amapLocation);
                                            LatLng curLalng = new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude());
                                            myLating = curLalng;
                                            searchLatlonPoint = new LatLonPoint(curLalng.latitude, curLalng.longitude);

                                            aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(curLalng, 16f));
                                        } else {
                                            String str = "???????????????" + amapLocation.getErrorCode();
                                            Log.i("zq", str);
                                        }
                                    }
                                }
                            });
                            //??????????????????????????????
                            mLocationOption.setOnceLocation(true);
                            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
                            //??????????????????
                            mlocationClient.setLocationOption(mLocationOption);
                            // ????????????????????????????????????????????????????????????????????????????????????????????????????????????
                            // ??????????????????????????????????????????????????????????????????2000ms?????????????????????????????????stopLocation()???????????????????????????
                            // ???????????????????????????????????????????????????onDestroy()??????
                            // ?????????????????????????????????????????????????????????????????????stopLocation()???????????????????????????sdk???????????????
                            mlocationClient.startLocation();
                        }catch (Exception e){

                        }
                    }
                }

                @Override
                public void deactivate() {

                }
            });

            aMap.setOnCameraChangeListener(new AMap.OnCameraChangeListener() {
                @Override
                public void onCameraChange(CameraPosition cameraPosition) {

                }

                @Override
                public void onCameraChangeFinish(CameraPosition cameraPosition) {

                    searchLatlonPoint = new LatLonPoint(cameraPosition.target.latitude, cameraPosition.target.longitude);
                    doSearchQuery();
                }
            });

            aMap.getUiSettings().setMyLocationButtonEnabled(true);// ????????????????????????????????????
            aMap.setMyLocationEnabled(true);// ?????????true??????????????????????????????????????????false??????????????????????????????????????????????????????false
            aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);

        }
    }
    private LatLonPoint searchLatlonPoint;
    private PoiSearch poiSearch;

    protected void doSearchQuery() {
//        Log.i("MY", "doSearchQuery");

     //   PoiSearch.Query query = new PoiSearch.Query("", "", "");
        PoiSearch.Query query = new PoiSearch.Query("??????????????????", "??????");
        // ????????????????????????????????????????????????????????????poi????????????????????????????????????poi??????????????????????????????????????????
        query.setCityLimit(true);
        query.setPageSize(20);
        query.setPageNum(1);
        query.setExtensions(PoiSearch.EXTENSIONS_ALL);

        if (searchLatlonPoint != null) {
            try {
                poiSearch = new PoiSearch(this, query);
                poiSearch.setOnPoiSearchListener(new PoiSearch.OnPoiSearchListener() {

                    @Override
                    public void onPoiSearched(PoiResult poiResult, int i) {
                        if (poiResult != null && poiResult.getQuery() != null) {
                            if (poiResult.getQuery().equals(query)) {
                                LatLng latLng = new LatLng(poiResult.getPois().get(0).getLatLonPoint().getLatitude(),poiResult.getPois().get(0).getLatLonPoint().getLongitude());

                                router(latLng);

                                Log.i("zq","====="+poiResult.getPois().get(0).getLatLonPoint().getLatitude()+poiResult.getPois().get(0).getLatLonPoint().getLongitude()+"");
                                Toast.makeText(MapActtivity.this, poiResult.getPois().get(0).getLatLonPoint().getLatitude() + "-"
                                        + poiResult.getPois().get(0).getLatLonPoint().getLongitude(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onPoiItemSearched(PoiItem poiItem, int i) {

                    }
                });
                poiSearch.setBound(new PoiSearch.SearchBound(searchLatlonPoint, 1000, true));//
                poiSearch.searchPOIAsyn();
            }catch (Exception e){

            }
        }
    }

    LatLng myLating  = null;

    RouteSearch routeSearch;
    public void router(LatLng _end){
        try {
            routeSearch = new RouteSearch(this);
            routeSearch.setRouteSearchListener(new RouteSearch.OnRouteSearchListener() {
                @Override
                public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {
                }

                @Override
                public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) {
                    Log.e("CF", "onDriveRouteSearched: " + i);
                    List<DrivePath> pathList = driveRouteResult.getPaths();
                    List<LatLng> driverPath = new ArrayList<>();
                    for (DrivePath dp : pathList) {
                        List<DriveStep> stepList = dp.getSteps();
                        for (DriveStep ds : stepList) {
                            List<LatLonPoint> points = ds.getPolyline();
                            for (LatLonPoint llp : points) {
                                driverPath.add(new LatLng(llp.getLatitude(), llp.getLongitude()));
                            }
                        }
                    }

                    aMap.clear();
                    aMap.addPolyline(new PolylineOptions()
                            .addAll(driverPath)
                            .width(40)
                            //????????????????????????
                            .setUseTexture(true)
                            //??????????????????
                            .geodesic(false)
                            //??????????????????
                            .setCustomTexture(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground)))
                            //?????????????????????
                            .color(Color.argb(200, 0, 0, 0)));
                }

                @Override
                public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {
                }

                @Override
                public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {
                }
            });
        }catch (Exception e){

        }

        if(myLating!=null){
            //?????????????????????????????????????????????
            RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(
                    new LatLonPoint(myLating.latitude, myLating.longitude),
                    new LatLonPoint(_end.latitude, _end.longitude));

// fromAndTo???????????????????????????????????????drivingMode??????????????????
// ?????????????????????????????????????????????16?????????  ????????????????????????????????????????????????32??????????????????????????????????????????

            RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(
                    fromAndTo, DRIVING_SINGLE_DEFAULT, null, null, "");
            routeSearch.calculateDriveRouteAsyn(query);
        }else{
            Toast.makeText(this,"??????????????????",Toast.LENGTH_LONG).show();
        }
    }

    public void initPermissions(){
        RxPermissions rxPermissions = new RxPermissions( MapActtivity.this);
        rxPermissions.request(Manifest.permission.CAMERA,Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                      /*      initPosition();
                            ToastUtils.showShort("?????????????????????????????????????????????");*/
                        } else {
                            //  ToastUtils.showShort("???????????????");
                        }
                    }
                });

    }
}
