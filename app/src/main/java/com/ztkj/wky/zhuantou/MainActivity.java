package com.ztkj.wky.zhuantou;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.utils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.MPermissionUtils;
import com.ztkj.wky.zhuantou.MyUtils.StringUtils;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.n1fra.LiveTabFragment;
import com.ztkj.wky.zhuantou.n1fra.N1Fragment;
import com.ztkj.wky.zhuantou.n1fra.N2Fragment;
import com.ztkj.wky.zhuantou.n1fra.N3Fragment;
import com.ztkj.wky.zhuantou.n1fra.N4Fragment;
import com.ztkj.wky.zhuantou.n1fra.N5Fragment;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.content_frag)
    FrameLayout contentFrag;
    @BindView(R.id.rb_1)
    RadioButton rb1;
    @BindView(R.id.rb_3)
    RadioButton rb3;
    @BindView(R.id.rg_tab)
    RadioGroup rgTab;
    @BindView(R.id.send_igv)
    ImageView sendIgv;
    @BindView(R.id.rb2)
    RelativeLayout rb2;
    @BindView(R.id.rb_4)
    RadioButton rb4;
    @BindView(R.id.rb_5)
    RadioButton rb5;
    private String phone, password;
    private N1Fragment n1Fragment;
    private N2Fragment n2Fragment;
    private N3Fragment n3Fragment;
    private N4Fragment n4Fragment;
    private N5Fragment n5Fragment;
    private LiveTabFragment liveTabFragment;
    private int position = 0;
    private SharedPreferencesHelper sharedPreferencesHelper;
    //打开你的消息界面
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ActivityManager.getInstance().addActivity(this);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        sharedPreferencesHelper = new SharedPreferencesHelper(MainActivity.this, "anhua");
        phone = (String) sharedPreferencesHelper.getSharedPreference("phone", "");
        password = (String) sharedPreferencesHelper.getSharedPreference("password", "");
        //如果之前登录过，APP 长期在后台再进的时候也可能会导致加载到内存的群组和会话为空
        // ，可以在主页面的 oncreate 里也加上这两句代码，当然，更好的办法应该是放在程序的开屏页。
        EMClient.getInstance().groupManager().loadAllGroups();
        EMClient.getInstance().chatManager().loadAllConversations();
//        登录环信
        if (!StringUtils.isEmpty(password)&&!StringUtils.isEmpty(phone)){
            String md5 = md5Decode(password);
            EMClient.getInstance().login(phone, md5, new EMCallBack() { //回调
                @Override
                public void onSuccess() {
                    EMClient.getInstance().groupManager().loadAllGroups();
                    EMClient.getInstance().chatManager().loadAllConversations();
                    Log.e("SplashActivity", "登录聊天服务器成功！");
                }

                @Override
                public void onProgress(int progress, String status) {

                }

                @Override
                public void onError(int code, String message) {
                    Log.e("SplashActivity", "登录聊天服务器失败！");
                }
            });
        }
        MPermissionUtils.requestPermissionsResult(this, 1,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.VIBRATE,
                        Manifest.permission.WAKE_LOCK,
                        Manifest.permission.SYSTEM_ALERT_WINDOW,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.ACCESS_FINE_LOCATION ,
                         Manifest.permission.ACCESS_COARSE_LOCATION ,
                },

                //这个是悬浮窗设置 但是小米手机设置悬浮窗权限的时候要求必须要在设置里面打开
                new MPermissionUtils.OnPermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                    }

                    @Override
                    public void onPermissionDenied() {
                    }
                });

        setTabSelection(position);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        position = 0;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putInt("position", position);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        position = savedInstanceState.getInt("position");
        setTabSelection(position);
        super.onRestoreInstanceState(savedInstanceState);
    }

    public void setTabSelection(int position) {
        //记录position
        this.position = position;
        //更改底部导航栏按钮状态
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
//        transaction.hide(shouYeFragment).hide(caiFuFragment).hide(jdFragment).hide(woDeFragment).commit();
        transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                rb1.setBackgroundResource(R.drawable.tab_icon_zhuye_sel);
                rb3.setBackgroundResource(R.drawable.tab_icon_me_set);
                rb4.setBackgroundResource(R.drawable.tabqifu2);
                rb5.setBackgroundResource(R.drawable.tabfujin2);
                setWindowBrightness(WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE);
                Contents.strExit = "1";

                rb1.setChecked(true);
                if (n1Fragment == null) {
                    n1Fragment = new N1Fragment();
                    transaction.add(R.id.content_frag, n1Fragment);
                } else {
                    transaction.show(n1Fragment);
                }
                break;
            case 1:
                rb1.setBackgroundResource(R.drawable.tab_icon_zhuye_default);
                rb3.setBackgroundResource(R.drawable.tab_icon_me_set);
                rb4.setBackgroundResource(R.drawable.tabqifu2);
                rb5.setBackgroundResource(R.drawable.tabfujin2);
                setWindowBrightness(WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_FULL);
                Contents.strExit = "1";

//                rb2.setChecked(true);
//                if (n2Fragment == null) {
                n2Fragment = new N2Fragment();
                transaction.add(R.id.content_frag, n2Fragment);
//                } else {
//                    transaction.show(n2Fragment);
//                }
                break;
            case 2:
                rb1.setBackgroundResource(R.drawable.tab_icon_zhuye_default);
                rb3.setBackgroundResource(R.drawable.tab_icon_me_default);
                rb4.setBackgroundResource(R.drawable.tabqifu2);
                rb5.setBackgroundResource(R.drawable.tabfujin2);
                setWindowBrightness(WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE);
                Contents.strExit = "0";
                rb3.setChecked(true);
                if (n3Fragment == null) {
                    n3Fragment = new N3Fragment();
                    transaction.add(R.id.content_frag, n3Fragment);
                } else {
                    transaction.show(n3Fragment);
                }
                break;

            case 3:
                rb1.setBackgroundResource(R.drawable.tab_icon_zhuye_default);
                rb3.setBackgroundResource(R.drawable.tab_icon_me_set);
                rb4.setBackgroundResource(R.drawable.tabqifu1);
                rb5.setBackgroundResource(R.drawable.tabfujin2);
                setWindowBrightness(WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE);
                Contents.strExit = "1";

                rb4.setChecked(true);
                if (n4Fragment == null) {
                    n4Fragment = new N4Fragment();
                    transaction.add(R.id.content_frag, n4Fragment);
                } else {
                    transaction.show(n4Fragment);
                }
                break;

            case 4:
                rb1.setBackgroundResource(R.drawable.tab_icon_zhuye_default);
                rb3.setBackgroundResource(R.drawable.tab_icon_me_set);
                rb4.setBackgroundResource(R.drawable.tabqifu2);
                rb5.setBackgroundResource(R.drawable.tabfujin1);
                setWindowBrightness(WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE);
                Contents.strExit = "1";

                rb5.setChecked(true);
                if (liveTabFragment == null) {
                    liveTabFragment = new LiveTabFragment();
                    transaction.add(R.id.content_frag, liveTabFragment);
                } else {
                    transaction.show(liveTabFragment);
                }
                break;

        }
        transaction.commit();
    }

    private void setWindowBrightness(float brightness) {
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.screenBrightness = brightness;
        window.setAttributes(lp);
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (n1Fragment != null)
            transaction.hide(n1Fragment);
        if (n2Fragment != null)
            transaction.hide(n2Fragment);
        if (n3Fragment != null)
            transaction.hide(n3Fragment);
        if (n4Fragment != null)
            transaction.hide(n4Fragment);
        if (liveTabFragment != null)
            transaction.hide(liveTabFragment);
        transaction.commit();
    }

    @Override
    public void finish() {
        super.finish();
    }

    @OnClick({R.id.rb_1, R.id.rb_3, R.id.rb2, R.id.rb_4, R.id.rb_5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_1:
                setTabSelection(0);
                break;
            case R.id.rb2:
                setTabSelection(1);
                break;
            case R.id.rb_3:
                setTabSelection(2);
                break;
            case R.id.rb_4:
                setTabSelection(3);
                break;
            case R.id.rb_5:
                setTabSelection(4);
                break;
        }
    }

    private long firstPressedTime;

    // System.currentTimeMillis() 当前系统的时间
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - firstPressedTime < 2000) {
//            super.onBackPressed();
            //必须要将这个页面设为栈顶 不然无效 无法全面退出页面
            finish();
            System.exit(0);
        } else {
            Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
            firstPressedTime = System.currentTimeMillis();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MPermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }

    /**
     * 32位MD5加密
     *
     * @param content -- 待加密内容
     * @return
     */
    public String md5Decode(String content) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(content.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("NoSuchAlgorithmException", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UnsupportedEncodingException", e);
        }
        //对生成的16字节数组进行补零操作
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) {
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

}
