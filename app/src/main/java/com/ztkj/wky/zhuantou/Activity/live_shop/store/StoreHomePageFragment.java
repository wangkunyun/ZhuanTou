package com.ztkj.wky.zhuantou.Activity.live_shop.store;

/**
 * 作者：wky
 * 功能描述：店铺首页
 *
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ztkj.wky.zhuantou.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreHomePageFragment extends Fragment {


    public StoreHomePageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_store_home_page, container, false);
    }

}
