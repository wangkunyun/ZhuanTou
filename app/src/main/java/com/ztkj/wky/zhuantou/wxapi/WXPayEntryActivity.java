package com.ztkj.wky.zhuantou.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.ztkj.wky.zhuantou.Activity.live_shop.ConfirmOrderActivity;
import com.ztkj.wky.zhuantou.Activity.live_shop.PaySucessfulActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by ZJL
 * on 2018/4/4 0004.
 */

public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {
    private IWXAPI api;
    //TODO　need to replace your APP_ID
    private static String app_id = "wx1ee881b1f44c7e98";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, app_id);
        api.handleIntent(getIntent(), this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        int code = baseResp.errCode;
        switch (code) {
            case 0:
                finish();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (ConfirmOrderActivity.confirmOrderActivity != null) {
                            ConfirmOrderActivity.confirmOrderActivity.finish();
                        }
                        PaySucessfulActivity.start(WXPayEntryActivity.this);
                    }
                }, 1000);
                break;
            case -2:
                ConfirmOrderActivity.payStauts = 2;
                finish();
                break;
            default:
                Toast.makeText(this, code + "", Toast.LENGTH_SHORT).show();
                break;
        }

    }

}
