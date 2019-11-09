package com.ztkj.wky.zhuantou;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.text.TextUtils;

import com.baidu.mapapi.SDKInitializer;
import com.blankj.utilcode.util.AppUtils;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.push.EMPushHelper;
import com.hyphenate.push.EMPushType;
import com.hyphenate.push.PushListener;
import com.kongzue.baseokhttp.util.BaseOkHttp;
import com.tencent.bugly.crashreport.CrashReport;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.ztkj.wky.zhuantou.MyUtils.BDLocationUtils;
import com.ztkj.wky.zhuantou.huanxin.DemoHelper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import cn.jpush.android.api.JPushInterface;

public class MyApplication extends Application {


    public static Context applicationContext;
    private static MyApplication instance;
    // login user name
    public final String PREF_USERNAME = "username";
    public static BDLocationUtils bdLocationUtils;

    @Override
    public void onCreate() {
        MultiDex.install(this);
        super.onCreate();
        applicationContext = this;
        instance = this;
//        RxTool.init(this);

        //网络请求可以设置全局 设置请求头
        BaseOkHttp.serviceUrl = "";
        //打开log日志
        BaseOkHttp.DEBUGMODE = true;

//================================== bugly =====================================
        bugly();

//================================== bugly =====================================

//================================== 极光 =====================================
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        ZXingLibrary.initDisplayOpinion(this);
//================================== 极光 =====================================

//================================== 百度地图 =====================================
        SDKInitializer.initialize(this);
        bdLocationUtils = new BDLocationUtils(instance);
        bdLocationUtils.doLocation();//开启定位
//================================== 百度地图 =====================================


        huanxin();
    }

    /**
     * 腾讯bugly
     */
    private void bugly() {
        /* Bugly SDK初始化
         * 参数1：上下文对象
         * 参数2：APPID，平台注册时得到,注意替换成你的appId
         * 参数3：是否开启调试模式，调试模式下会输出'CrashReport'tag的日志
         * 注意：如果您之前使用过Bugly SDK，请将以下这句注释掉。
         */
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getApplicationContext());
        strategy.setAppVersion(AppUtils.getAppVersionName());
        strategy.setAppPackageName(AppUtils.getAppPackageName());
        strategy.setAppReportDelay(20000);                          //Bugly会在启动20s后联网同步数据

        /*  第三个参数为SDK调试模式开关，调试模式的行为特性如下：
            输出详细的Bugly SDK的Log；
            每一条Crash都会被立即上报；
            自定义日志将会在Logcat中输出。
            建议在测试阶段建议设置成true，发布时设置为false。*/

        CrashReport.initCrashReport(getApplicationContext(), "9e14c1ee99", true, strategy);

        //Bugly.init(getApplicationContext(), "1374455732", false);

    }

    /**
     * 环信
     */
    private void huanxin() {
        //init demo helper
        DemoHelper.getInstance().init(applicationContext);

        // 请确保环信SDK相关方法运行在主进程，子进程不会初始化环信SDK（该逻辑在EaseUI.java中）
        if (EaseUI.getInstance().isMainProcess(this)) {
            // 初始化华为 HMS 推送服务, 需要在SDK初始化后执行
//            HMSPushHelper.getInstance().initHMSAgent(instance);
            EMPushHelper.getInstance().setPushListener(new PushListener() {
                @Override
                public void onError(EMPushType pushType, long errorCode) {
                    // TODO: 返回的errorCode仅9xx为环信内部错误，可从EMError中查询，其他错误请根据pushType去相应第三方推送网站查询。
//                    EMLog.e("PushClient", "Push client occur a error: " + pushType + " - " + errorCode);
                }
            });
        }
    }

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

}