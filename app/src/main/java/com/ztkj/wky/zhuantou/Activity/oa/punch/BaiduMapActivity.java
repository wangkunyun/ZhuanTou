package com.ztkj.wky.zhuantou.Activity.oa.punch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kongzue.dialog.interfaces.OnDialogButtonClickListener;
import com.kongzue.dialog.util.BaseDialog;
import com.kongzue.dialog.v3.MessageDialog;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.BDLocationUtils;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.PoiListAdapter;
import com.ztkj.wky.zhuantou.base.Contents;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaiduMapActivity extends AppCompatActivity
        implements OnGetPoiSearchResultListener, View.OnClickListener, OnGetGeoCoderResultListener {
    @BindView(R.id.bigsearch_back)
    ImageView bigsearch_back;
    @BindView(R.id.bmapView)
    MapView bmapView;
    @BindView(R.id.poi_list)
    ListView mPoiList;
    @BindView(R.id.tv_cur_dingwei)
    TextView tv_cur_dingwei;
    @BindView(R.id.refresh_localtion)
    TextView refresh_localtion;
    BaiduMap map;
    LatLng latLng;
    // 声明 PoiSearch
    private PoiSearch mPoiSearch = null;
    private List<PoiInfo> mAllPoi = new ArrayList<>();
    private Marker mMarkerA;
    // 初始化全局 bitmap 信息，不用时及时 recycle
    private BitmapDescriptor bitmapA = BitmapDescriptorFactory.fromResource(R.mipmap.icon_location);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidu_map);
        ButterKnife.bind(this);
        org.greenrobot.eventbus.EventBus.getDefault().register(this);
        refresh_localtion.setOnClickListener(this);
        bigsearch_back.setOnClickListener(this);
        confirm.setOnClickListener(this);
        bigsearch_edt.setOnClickListener(this);
        radusString = getIntent().getStringExtra("radius");
        setMap();
        mPoiList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                poiInfo = mAllPoi.get(i);
                if (poiInfo.getLocation() == null) {
                    ToastUtils.setGravity(Gravity.CENTER, 0, 0);
                    ToastUtils.showShort("无");
                    return;
                } else {
                    latLng = poiInfo.location;
                    address = poiInfo.address;
                    addPoiLoction(poiInfo.location);
                }

            }
        });

    }

    int radius;
    PoiInfo poiInfo;
    String radusString;
    BDLocationUtils bdLocationUtils;

    private void setMap() {
        //哈哈哈 海荣的分支创建完成
        //获取地图控件引用
        map = bmapView.getMap();
        bdLocationUtils = new BDLocationUtils(BaiduMapActivity.this);
        bdLocationUtils.doLocation();
//        if (Contents.LOCATION != null) {
//            setAdress(Contents.LOCATION);
//        }
        // 创建poi检索实例，注册搜索事件监听
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(this);
        // 初始化搜索模块，注册事件监听
        mSearch = GeoCoder.newInstance();
        mSearch.setOnGetGeoCodeResultListener(this);
        //显示卫星图层
        map.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        map.setMyLocationEnabled(true);
        if (String.valueOf(Contents.LATITUDE) != null && String.valueOf(Contents.LONGITUDE) != null) {
            latLng = new LatLng(Contents.LATITUDE, Contents.LONGITUDE);
            //定义地图状态
            MapStatus mMapStatus = new MapStatus.Builder()
                    .target(latLng)
                    .zoom(18.0f)
                    .build();
            //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
            MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
            //改变地图状态
            map.setMapStatus(mMapStatusUpdate);
        }
        try {
            MarkerOptions markerOptionsA = new MarkerOptions()
                    .position(latLng)
                    .icon(bitmapA)// 设置 Marker 覆盖物的图标
                    .zIndex(9)// 设置 marker 覆盖物的 zIndex
                    .draggable(true); // 设置 marker 是否允许拖拽，默认不可拖拽
            mMarkerA = (Marker) (map.addOverlay(markerOptionsA));
            if (radusString != null) {
                radius = Integer.parseInt(radusString);
            }
            setReverseGeoCode(latLng);
            bdLocationUtils.stopLoacation();
        } catch (NumberFormatException e) {
            return;
        }
    }

    private void setReverseGeoCode(LatLng latLng) {
        ReverseGeoCodeOption reverseGeoCodeOption = new ReverseGeoCodeOption()
                .location(latLng) // 设置反地理编码位置坐标
                .newVersion(1) // 设置是否返回新数据 默认值0不返回，1返回
                .radius(radius) //  POI召回半径，允许设置区间为0-1000米，超过1000米按1000米召回。默认值为1000
                .pageNum(0);
        // 发起反地理编码请求，该方法必须在监听之后执行，否则会在某些场景出现拿不到回调结果的情况
        mSearch.reverseGeoCode(reverseGeoCodeOption);
    }

    /**
     * 更新到子节点的位置
     *
     * @param latLng 子节点经纬度
     */
    private void addPoiLoction(LatLng latLng) {
        map.clear();
        OverlayOptions markerOptions = new MarkerOptions().position(latLng).icon(bitmapA);
        map.addOverlay(markerOptions);
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.target(latLng);
        builder.zoom(18);
        map.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        bdLocationUtils.stopLoacation();
    }

    @Override
    public void onGetPoiResult(PoiResult poiResult) {

    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

    }

    @Override
    public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

    }

    @BindView(R.id.confirm)
    TextView confirm;
    @BindView(R.id.bigsearch_edt)
    RelativeLayout bigsearch_edt;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.refresh_localtion:
                if (bdLocationUtils != null) {
                    bdLocationUtils.refreshLoacation();
                    if (Contents.LOCATION != null) {
                        setAdress(Contents.LOCATION);
                    }
                    if (String.valueOf(Contents.LATITUDE) != null && String.valueOf(Contents.LONGITUDE) != null) {
                        latLng = new LatLng(Contents.LATITUDE, Contents.LONGITUDE);
                        addPoiLoction(latLng);
                        setReverseGeoCode(latLng);
                    }

                }
                break;
            case R.id.bigsearch_back:
                finish();
                break;
            case R.id.confirm:
                if (address == null) {
                    ToastUtils.setGravity(Gravity.CENTER, 0, 0);
                    ToastUtils.showShort("请选择地址");
                } else {
                    initMsg(address);

                }
                break;
            case R.id.bigsearch_edt:
                Intent intent = new Intent(BaiduMapActivity.this, SearchLoacaltionActivity.class);
                startActivityForResult(intent, 1);
                break;
        }

    }

    private void initMsg(String locationAdreess) {
        MessageDialog.build(BaiduMapActivity.this)
                .setTitle("提示")
                .setMessage("是否使用【" + locationAdreess + "】为考勤地点")
                .setOkButton("确定", new OnDialogButtonClickListener() {
                    @Override
                    public boolean onClick(BaseDialog baseDialog, View v) {
                        request();
                        return false;
                    }
                })
                .setCancelButton("取消", new OnDialogButtonClickListener() {
                    @Override
                    public boolean onClick(BaseDialog baseDialog, View v) {
                        return false;
                    }
                })
                .show();
    }

    private void setAdress(String addres) {
        address = addres;
        tv_cur_dingwei.setText("[当前位置]：" + addres);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            address = data.getStringExtra("address");
            if (address != null && !address.equals("")) {
                // 发起Geo搜索
                setAdress(address);
                mSearch.geocode(new GeoCodeOption()
                        .city("北京")// 城市
                        .address(address)); // 地址
            } else {

            }
        }
    }

    private void request() {
        OkHttpUtils.post().url(Contents.AMENDPUNCHININFO)
                .addParams("token", SPUtils.getInstance().getString("token"))
                .addParams("phone", "13460720766")//SPUtils.getInstance().getString("phone")
                .addParams("x", String.valueOf(latLng.longitude))
                .addParams("y", String.valueOf(latLng.latitude))
                .addParams("address", address)
                .addParams("cid", SPUtils.getInstance().getString("cid"))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.get("errno").equals("200")) {
                        Toast.makeText(BaiduMapActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    String address;
    // 搜索模块，也可去掉地图模块独立使用
    private GeoCoder mSearch = null;

    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
        map.clear();
        map.addOverlay(new MarkerOptions().position(geoCodeResult.getLocation()).icon(bitmapA));
        map.setMapStatus(MapStatusUpdateFactory.newLatLng(geoCodeResult.getLocation()));
        latLng = new LatLng(geoCodeResult.getLocation().latitude, geoCodeResult.getLocation().longitude);
        setReverseGeoCode(latLng);
    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

        if (reverseGeoCodeResult == null || reverseGeoCodeResult.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
            Toast.makeText(BaiduMapActivity.this, "未找到结果", Toast.LENGTH_LONG).show();
            return;
        }
        if (reverseGeoCodeResult.error == SearchResult.ERRORNO.NO_ERROR) {
            mAllPoi = reverseGeoCodeResult.getPoiList();
            if (null != mAllPoi && mAllPoi.size() > 0) {
                PoiListAdapter poiListAdapter = new PoiListAdapter(this, mAllPoi);
                mPoiList.setAdapter(poiListAdapter);
            } else {
                Toast.makeText(BaiduMapActivity.this, "未找到结果", Toast.LENGTH_LONG).show();
            }

            return;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBus(String event) {
        setAdress(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bdLocationUtils.stopLoacation();
        bmapView.onDestroy();
        bmapView = null;
        EventBus.getDefault().unregister(this);
    }
}


