package com.ztkj.wky.zhuantou.Activity.live_shop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.DisplayUtil;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.SearchItemAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.SearchResultBean;
import com.ztkj.wky.zhuantou.bean.ShopCatatoryBean;
import com.ztkj.wky.zhuantou.bean.ShopSearchBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchShopsActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.bigsearch_back)
    ImageView bigsearchBack;
    @BindView(R.id.bigsearch_edt)
    EditText bigsearchEdt;
    @BindView(R.id.confirm)
    TextView confirm;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.lv_shop)
    RecyclerView lvShop;
    SearchItemAdapter searchItemAdapter;
    List<SearchResultBean.DataBean> list = new ArrayList<>();
    @BindView(R.id.iv_close)
    ImageView iv_close;

    public static void start(Context context) {
        Intent starter = new Intent(context, SearchShopsActivity.class);
        context.startActivity(starter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_shops);
        ButterKnife.bind(this);
        bigsearchEdt.setFocusable(true);
        bigsearchEdt.setFocusableInTouchMode(true);
        bigsearchEdt.requestFocus();
        DisplayUtil.showKeyboard(SearchShopsActivity.this);
        searchItemAdapter = new SearchItemAdapter(SearchShopsActivity.this);
        lvShop.setLayoutManager(new LinearLayoutManager(SearchShopsActivity.this));
        lvShop.setAdapter(searchItemAdapter);
        confirm.setOnClickListener(this);
        bigsearchBack.setOnClickListener(this);
        iv_close.setOnClickListener(this);
        // 当输入关键字变化时，动态更新建议列表
        bigsearchEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                if (cs.length() <= 0) {
                    iv_close.setVisibility(View.INVISIBLE);
                    if (list.size() > 0 && searchItemAdapter.getItemCount() > 0) {
                        list.clear();
                        searchItemAdapter.clearData();
                        searchItemAdapter.notifyDataSetChanged();
                    }
                    return;
                }
                iv_close.setVisibility(View.VISIBLE);
                getShopList(cs.toString());

            }
        });
        bigsearchEdt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEND || i == EditorInfo.IME_ACTION_DONE || (keyEvent != null && KeyEvent.KEYCODE_ENTER == keyEvent.getKeyCode() && KeyEvent.ACTION_DOWN == keyEvent.getAction())) {
                    //处理事件
                    if (bigsearchEdt.getText().toString() != null) {
                        SearchShopActivity.start(SearchShopsActivity.this, bigsearchEdt.getText().toString());
                        finish();
                    } else {
                        ToastUtils.showShort("请输入商品名称");
                    }
                }
                return false;
            }
        });


    }

    SearchResultBean shopSearchBean;
    int page = 1;

    private void getShopList(String searchName) {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.getKeyWord)
                .addParams("keyword", searchName)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        shopSearchBean = new Gson().fromJson(response, SearchResultBean.class);
                        if (shopSearchBean != null) {
                            if (shopSearchBean.getData() != null) {
                                if (shopSearchBean.getData().size() > 0) {
                                    if (list.size() > 0 && searchItemAdapter.getItemCount() > 0) {
                                        list.clear();
                                        searchItemAdapter.clearData();
                                    }
                                    list = shopSearchBean.getData();
                                    searchItemAdapter.setData(list);
                                    searchItemAdapter.notifyDataSetChanged();
                                }
                            }
                        }

                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirm:
                if (!TextUtils.isEmpty(bigsearchEdt.getText().toString().trim())) {
                    SearchShopActivity.start(SearchShopsActivity.this, bigsearchEdt.getText().toString());
                    finish();
                } else {
                    ToastUtils.showShort("请输入商品名称");
                }

                break;
            case R.id.bigsearch_back:
                finish();
                break;
            case R.id.iv_close:
                bigsearchEdt.setText("");
                iv_close.setVisibility(View.INVISIBLE);
                break;
        }
    }
}
