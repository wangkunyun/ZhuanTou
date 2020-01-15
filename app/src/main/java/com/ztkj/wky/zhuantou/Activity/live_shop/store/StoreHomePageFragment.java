package com.ztkj.wky.zhuantou.Activity.live_shop.store;

/**
 * 作者：wky
 * 功能描述：店铺首页
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;

import com.blankj.utilcode.util.SPUtils;
import com.ztkj.wky.zhuantou.Activity.live_shop.ShopDetailActivity;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreHomePageFragment extends Fragment {

    //    String h5="http://shoph5.zhuantoukj.com/dist-H5/#/?ssc_id=1&type=isAndroid";
    @BindView(R.id.webHome)
    WebView webView;
    String id;
    public StoreHomePageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_store_home_page, container, false);
        ButterKnife.bind(this, view);
        WebSettings wetSettings = webView.getSettings();
        wetSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        wetSettings.setJavaScriptEnabled(true);
        wetSettings.setDomStorageEnabled(true);
        webView.setDrawingCacheEnabled(true);
        webView.clearCache(true);
        WebStorage.getInstance().deleteAllData();
        webView.addJavascriptInterface(new JsInterface(), "android");
        return view;
    }

    public class JsInterface {
        @JavascriptInterface
        public void HtmlToAndroid(String id) {
            if(id!=null){
                ShopDetailActivity.start(getActivity(),id);
            }
        }
    }


    String h5;
    public void loadH5Name(String cid) {
        h5 = Contents.homeStoreH5+"?ssc_id=" + cid + "&type=isAndroid";
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        id= SPUtils.getInstance().getString("storeId");
        loadH5Name(id);
        webView.loadUrl(h5);

    }
}
