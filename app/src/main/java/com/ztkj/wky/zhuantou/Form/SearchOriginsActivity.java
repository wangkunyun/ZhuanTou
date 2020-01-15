package com.ztkj.wky.zhuantou.Form;

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
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.Activity.live_shop.SearchShopActivity;
import com.ztkj.wky.zhuantou.MyUtils.DisplayUtil;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.SearchItemAdapter;
import com.ztkj.wky.zhuantou.adapter.SearchItemOriginAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.SearchOriginBean;
import com.ztkj.wky.zhuantou.bean.SearchResultBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchOriginsActivity extends AppCompatActivity implements View.OnClickListener ,SearchItemOriginAdapter.InviteMember{

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
    SearchItemOriginAdapter searchItemAdapter;
    List<SearchOriginBean.DataBean> list = new ArrayList<>();
    @BindView(R.id.iv_close)
    ImageView iv_close;
    SearchOriginBean originBean;
    String cid;
    public static void start(Context context,String cid) {
        Intent starter = new Intent(context, SearchOriginsActivity.class);
        starter.putExtra("cid",cid);
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
        cid=getIntent().getStringExtra("cid");
        DisplayUtil.showKeyboard(SearchOriginsActivity.this);
        searchItemAdapter = new SearchItemOriginAdapter(SearchOriginsActivity.this);
        lvShop.setLayoutManager(new LinearLayoutManager(SearchOriginsActivity.this));
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
//                if (i == EditorInfo.IME_ACTION_SEND || i == EditorInfo.IME_ACTION_DONE || (keyEvent != null && KeyEvent.KEYCODE_ENTER == keyEvent.getKeyCode() && KeyEvent.ACTION_DOWN == keyEvent.getAction())) {
//                    //处理事件
//                    if (bigsearchEdt.getText().toString() != null) {
//                        SearchShopActivity.start(SearchOriginsActivity.this, bigsearchEdt.getText().toString());
//                        finish();
//                    } else {
//                        ToastUtils.showShort("请输入商品名称");
//                    }
//                }
                return false;
            }
        });


    }

     private void inviteMenber(String phone) {
        OkHttpUtils.post().url(Contents.SHOPBASE+Contents.inviteMember)
                .addParams("cid",cid)
                .addParams("phone",phone)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e("df",response);
                    }
                });

    }

    private void getShopList(String searPhone) {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.originSearch)
                .addParams("cid", cid)
                .addParams("phone", searPhone)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        originBean = new Gson().fromJson(response, SearchOriginBean.class);
                        if (originBean != null) {
                            searchItemAdapter.setData(originBean.getData());
                        }

                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirm:
                if (!TextUtils.isEmpty(bigsearchEdt.getText().toString().trim())) {
                    getShopList(bigsearchEdt.getText().toString());

                } else {
                    ToastUtils.showShort("请输入手机号");
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

    @Override
    public void invite(String phone) {
        inviteMenber(phone);
    }
}
