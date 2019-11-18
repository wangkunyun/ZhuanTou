package com.ztkj.wky.zhuantou.isMy;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.addresspicker.huichao.addresspickerlibrary.CityPickerDialog;
import com.addresspicker.huichao.addresspickerlibrary.InitAreaTask;
import com.addresspicker.huichao.addresspickerlibrary.Util;
import com.addresspicker.huichao.addresspickerlibrary.address.City;
import com.addresspicker.huichao.addresspickerlibrary.address.County;
import com.addresspicker.huichao.addresspickerlibrary.address.Province;
import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.Activity.mine.NameRenZhengActivity;
import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.ImageUtils;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.MyUtils.StringUtils;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.GeRenBean;
import com.ztkj.wky.zhuantou.bean.HeadBean;
import com.ztkj.wky.zhuantou.landing.NewLoginActivity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

import static android.support.constraint.Constraints.TAG;

public class GeRenActivity extends AppCompatActivity implements InitAreaTask.onLoadingAddressListener {

    @BindView(R.id.geren_goback)
    ImageView gerenGoback;
    //    @BindView(R.id.geren_bianji)
//    TextView gerenBianji;
    @BindView(R.id.geren_toolbar)
    Toolbar gerenToolbar;
    @BindView(R.id.gerent1)
    TextView gerent1;
    @BindView(R.id.geren_tab1)
    RelativeLayout gerenTab1;
    @BindView(R.id.geren_tab2)
    RelativeLayout gerenTab2;
    @BindView(R.id.gerent2)
    TextView gerent2;
    @BindView(R.id.geren_tab3)
    RelativeLayout gerenTab3;
    @BindView(R.id.gerent3)
    TextView gerent3;
    @BindView(R.id.geren_tab4)
    RelativeLayout gerenTab4;
    @BindView(R.id.gerent4)
    TextView gerent4;
    @BindView(R.id.geren_tab5)
    RelativeLayout gerenTab5;
    @BindView(R.id.geren_head)
    ImageView gerenHead;
    @BindView(R.id.gerenbg)
    LinearLayout gerenbg;
    @BindView(R.id.not_renzheng)
    TextView notRenzheng;
    @BindView(R.id.renzheng)
    TextView renzheng;
    @BindView(R.id.geren_tab6)
    RelativeLayout gerenTab6;

    private SharedPreferencesHelper sharedPreferencesHelper;
    private Intent intent;
    private String url = StringUtils.jiekouqianzui + "User/userSave";
    private String headurl = StringUtils.jiekouqianzui + "User/userHead";
    private String uid, token;
    private String username;
    private String integral;
    private String sex;
    private String address;
    private String head;
    private String jifen;
    private String phone;

    private String name1 = "";
    private String sex1 = "";
    private String address1 = "";

    public static final int CHOOSE_PICTURE = 0;
    public static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    public static Uri tempUri;
    private ArrayList<Province> provinces;
    private Dialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ge_ren);
        ButterKnife.bind(this);
        ActivityManager.getInstance().addActivity(this);
        sharedPreferencesHelper = new SharedPreferencesHelper(GeRenActivity.this, "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        username = (String) sharedPreferencesHelper.getSharedPreference("realname", "");
        integral = (String) sharedPreferencesHelper.getSharedPreference("integral", "");
        sex = (String) sharedPreferencesHelper.getSharedPreference("sex", "");
        address = (String) sharedPreferencesHelper.getSharedPreference("address", "");
        head = (String) sharedPreferencesHelper.getSharedPreference("head", "");
        phone = (String) sharedPreferencesHelper.getSharedPreference("phone", "");
        String name_renzheng = SPUtils.getInstance().getString("isrenzheng");
        if ("0".equals(username)) {
            gerent1.setText("暂未填写");
        } else {
            gerent1.setText(username);
        }
        gerent2.setText(phone);
        if ("0".equals(sex)) {
            gerent3.setText("暂未填写");
        } else if ("1".equals(sex)) {
            gerent3.setText("男");
        } else if ("2".equals(sex)) {
            gerent3.setText("女");
        }
        if ("0".equals(address)) {
            gerent4.setText("暂未填写");
        } else {
            gerent4.setText(address);
        }
        if ("0".equals(head)) {
            gerenHead.setImageResource(R.drawable.head_portrait);
        } else {
            Glide.with(GeRenActivity.this).load(head).into(gerenHead);
        }

        //是否已实名认证
        if (name_renzheng.equals("0")) { //没有认证
            notRenzheng.setVisibility(View.VISIBLE);
            renzheng.setVisibility(View.GONE);
        } else { //已认证
            notRenzheng.setVisibility(View.GONE);
            renzheng.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public void onLoadFinished() {
        progressDialog.dismiss();
        showAddressDialog();
    }

    @Override
    public void onLoading() {
        progressDialog = Util.createLoadingDialog(GeRenActivity.this, "请稍等...", true, 0);
        progressDialog.show();
    }

    private void showAddressDialog() {//初始化地址数据后，显示地址选择框
        new CityPickerDialog(GeRenActivity.this, provinces, null, null, null,
                new CityPickerDialog.onCityPickedListener() {
                    @Override
                    public void onPicked(Province selectProvince,
                                         City selectCity, County selectCounty) {
                        StringBuilder address = new StringBuilder();
                        address.append(
                                selectProvince != null ? selectProvince
                                        .getAreaName() : "")
                                .append(selectCity != null ? selectCity
                                        .getAreaName() : "");
                        if (selectCounty != null) {
                            String areaName = selectCounty.getAreaName();
                            if (areaName != null) {
                                address.append(areaName);
                            }
                        }
                        gerent4.setText(address.toString());
//                        Toast.makeText(GeRenActivity.this,address.toString(),Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    @Override
    public void onBackPressed() {
        gi();
    }

    @OnClick({R.id.gerent1, R.id.gerent2, R.id.gerent3, R.id.gerent4, R.id.geren_goback, R.id.geren_tab1, R.id.geren_tab2, R.id.geren_tab4, R.id.geren_tab5, R.id.geren_tab6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.geren_goback:
                gi();
                break;
            case R.id.gerent1:
                popuinit1();
                break;
            case R.id.gerent3:
                popuinit2();
                break;
            case R.id.gerent4:
                if (provinces == null) {
                    provinces = new ArrayList<>();
                }
                if (provinces.size() > 0) {
                    showAddressDialog();
                } else {
                    //没有地址数据，就去初始化
                    InitAreaTask initAreaTask = new InitAreaTask(GeRenActivity.this, provinces);
                    initAreaTask.execute(0);
                    initAreaTask.setOnLoadingAddressListener(GeRenActivity.this);
                }
                break;
            case R.id.geren_tab1:
                popuinit1();
                break;
            case R.id.geren_tab2:
                popuinit();
                break;
            case R.id.geren_tab4:
                popuinit2();
                break;
            case R.id.geren_tab5:
                if (provinces == null) {
                    provinces = new ArrayList<>();
                }
                if (provinces.size() > 0) {
                    showAddressDialog();
                } else {
                    //没有地址数据，就去初始化
                    InitAreaTask initAreaTask = new InitAreaTask(GeRenActivity.this, provinces);
                    initAreaTask.execute(0);
                    initAreaTask.setOnLoadingAddressListener(GeRenActivity.this);
                }
                break;
            case R.id.geren_tab6://实名认证
//                intent = new Intent(GeRenActivity.this, NameRenZhengActivity.class);
                intent = new Intent(GeRenActivity.this, NameRenZhengActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void popuinit2() {
        View contentView = LayoutInflater.from(GeRenActivity.this).inflate(R.layout.sex_pp, null);
        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.4f);
        //下面是p里面的东西
//        TextView ptextView = contentView.findViewById(R.id.ppt_tv1);
//        final EditText editText = contentView.findViewById(R.id.ppsz_edt);
        final TextView pbutton = contentView.findViewById(R.id.sexpp_t1);
        final TextView pbutton2 = contentView.findViewById(R.id.sexpp_t2);
        final PopupWindow window = new PopupWindow(contentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        window.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        View rootview = LayoutInflater.from(GeRenActivity.this).inflate(R.layout.activity_ge_ren, null);
//        ptextView.setText("5338-9498");
        pbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gerent3.setText(pbutton.getText());
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        pbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gerent3.setText(pbutton2.getText());
                backgroundAlpha(1f);
                window.dismiss();
            }
        });

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        window.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    private void popuinit1() {
        View contentView = LayoutInflater.from(GeRenActivity.this).inflate(R.layout.sz_item, null);
        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.4f);
        //下面是p里面的东西
//        TextView ptextView = contentView.findViewById(R.id.ppt_tv1);
        final EditText editText = contentView.findViewById(R.id.ppsz_edt);
        Button pbutton = contentView.findViewById(R.id.ppsz_btn1);
        Button pbutton2 = contentView.findViewById(R.id.ppsz_btn2);
        final PopupWindow window = new PopupWindow(contentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        window.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        View rootview = LayoutInflater.from(GeRenActivity.this).inflate(R.layout.activity_ge_ren, null);
//        ptextView.setText("5338-9498");
        pbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        pbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().length() < 2 || editText.getText().length() > 8) {
                    Toast.makeText(GeRenActivity.this, "昵称在2到8位之间", Toast.LENGTH_SHORT).show();
                } else {
                    gerent1.setText(editText.getText());
                    gi();
                    backgroundAlpha(1f);
                    window.dismiss();
                }
            }
        });

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        window.showAtLocation(rootview, Gravity.CENTER, 0, 0);
    }

    private void gi() {
        int se = 0;
        if ("男".equals(gerent3.getText())) {
            se = 1;
        } else if ("女".equals(gerent3.getText())) {
            se = 2;
        }
        OkHttpUtils.post()
                .url(url)
                .addParams("uid", uid)
                .addParams("name", gerent1.getText().toString())
                .addParams("phone", phone)
                .addParams("sex", se + "")
                .addParams("type", "1")
                .addParams("address", gerent4.getText().toString())
                .addParams("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.d("infora", response);
                        Gson gson = new Gson();
                        GeRenBean zcBean = gson.fromJson(response, GeRenBean.class);
                        if (zcBean.getErrno().equals("200")) {
                            finish();
                            sharedPreferencesHelper.put("realname", gerent1.getText().toString());
                            SPUtils.getInstance().put("realname", gerent1.getText().toString());
                            Toast.makeText(GeRenActivity.this, "上传信息成功", Toast.LENGTH_SHORT).show();
                        } else if (zcBean.getErrno().equals("666666")) {
                            Toast.makeText(GeRenActivity.this, "您的账号已在其他手机登录，如非本人操作，请修改密码", Toast.LENGTH_LONG).show();
                            JPushInterface.deleteAlias(GeRenActivity.this, Integer.parseInt(uid));
                            sharedPreferencesHelper.clear();
                            SPUtils.getInstance().clear();
                            ActivityManager.getInstance().exit();
                            intent = new Intent(GeRenActivity.this, NewLoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else if (zcBean.getErrno().equals("212")) {
                            finish();
                        } else {
                            Toast.makeText(GeRenActivity.this, zcBean.getErrmsg(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }


    private void popuinit() {
        View contentView = LayoutInflater.from(GeRenActivity.this).inflate(R.layout.add_del, null);
        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.5f);
        //下面是p里面的东西
        TextView popz = contentView.findViewById(R.id.xc_quxiao);
        TextView tcdl = contentView.findViewById(R.id.xc_xj);
        TextView tcdl2 = contentView.findViewById(R.id.xc_xc);
        final PopupWindow window = new PopupWindow(contentView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        window.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        window.setOutsideTouchable(true);
        window.setTouchable(true);
//        window.setAnimationStyle(R.style.anim_pop_bottombar);
        View rootview = LayoutInflater.from(GeRenActivity.this).inflate(R.layout.activity_ge_ren, null);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        popz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        tcdl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        tcdl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openAlbumIntent = new Intent(
                        Intent.ACTION_PICK);
                openAlbumIntent.setType("image/*");
//                openAlbumIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                openAlbumIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                startActivityForResult(openAlbumIntent, 101);
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        window.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }

    private void takePicture() {
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (Build.VERSION.SDK_INT >= 23) {
            // 需要申请动态权限
            int check = ContextCompat.checkSelfPermission(GeRenActivity.this, permissions[0]);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (check != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
        Intent openCameraIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment
                .getExternalStorageDirectory(), "mylgz_images.png");
        //判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= 24) {
//            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
//            StrictMode.setVmPolicy(builder.build());
//            builder.detectFileUriExposure();

            openCameraIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            tempUri = FileProvider.getUriForFile(GeRenActivity.this, "com.ztkj.wky.zhuantou.fileprovider", file);

        } else {
            tempUri = Uri.fromFile(new File(Environment
                    .getExternalStorageDirectory(), "mylgz_images.png"));
        }

        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
        startActivityForResult(openCameraIntent, TAKE_PICTURE);
    }

    private Uri uritempFile;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("wyaosile0", requestCode + "+" + resultCode + "" + data);
//        ActivityCompat.requestPermissions(GeRenActivity.this, permissions, requestCode);
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    startPhotoZoom(tempUri); // 开始对图片进行裁剪处理
                    break;
                case 101:
                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    Bitmap bitmap = null;

                    if (data != null) {
                        try {
                            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uritempFile));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        setImageToView(bitmap); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    protected void startPhotoZoom(Uri uri) {

        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        Log.i("tag", "The uri is not exist..");
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");

        intent.setDataAndType(tempUri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);

        uritempFile = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" + "ztkjovo.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uritempFile);

        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param
     */

    protected void setImageToView(Bitmap photo) {
//        Bundle bundle = data.getExtras();
//        if (bundle != null) {
//            Bitmap photo = bundle.getParcelable("data");
        Log.d(TAG, "setImageToView:" + photo);
        photo = ImageUtils.toRoundBitmap(photo); // 这个时候的图片已经被处理成圆形的了
//            gerenHead.setImageBitmap(photo);
        uploadPic(photo);
//        }
    }

    public static File saveBitmapFile(Bitmap bitmap, String filepath) {
        File file = new File(filepath);
        //将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private void uploadPic(Bitmap bitmap) {
        // 上传至服务器
        // ... 可以在这里把Bitmap转换成file，然后得到file的url，做文件上传操作
        // 注意这里得到的图片已经是圆形图片了
        // bitmap是没有做个圆形处理的，但已经被裁剪了
        String imagePath = ImageUtils.savePhoto(bitmap, Environment
                .getExternalStorageDirectory().getAbsolutePath(), String
                .valueOf("icon"));
        File file1 = saveBitmapFile(bitmap, imagePath);
        String name = file1.getName();
        if (!file1.exists()) {
            Toast.makeText(GeRenActivity.this, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
//            return;
        }
        if (imagePath != null) {
//            shouyeIcon.setImageBitmap(bitmap);
            // 拿着imagePath上传了
            // ...
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Disposition", "form-data;filename=enctype");

            OkHttpUtils.post()
                    .url(headurl)
                    .addParams("uid", uid)
                    .addParams("token", token)
//                    .addParams("username", username)
//                    .addParams("integral", integral)
//                    .addParams("sex", sex)
//                    .addParams("address", address)
//                    .addParams("jifen", jifen)
//                    .addParams("head", head)
                    .addFile("image", name, file1)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Request request, Exception e) {
//                            Log.d("icon_response","失败"+request);
                        }

                        @Override
                        public void onResponse(String response) {
                            Log.d("icon_response", response);
                            Gson gson = new Gson();
                            HeadBean headBean = gson.fromJson(response, HeadBean.class);
                            if (headBean.getErrno().equals("200")) {

                                Glide.with(GeRenActivity.this).load(headBean.getData().getUrl()).into(gerenHead);
                            } else if (headBean.getErrno().equals("666666")) {
                                Toast.makeText(GeRenActivity.this, "您的账号已在其他手机登录，如非本人操作，请修改密码", Toast.LENGTH_LONG).show();
                                JPushInterface.deleteAlias(GeRenActivity.this, Integer.parseInt(uid));
                                sharedPreferencesHelper.clear();
                                SPUtils.getInstance().clear();
                                intent = new Intent(GeRenActivity.this, NewLoginActivity.class);
                                startActivity(intent);
                                ActivityManager.getInstance().exit();
                                finish();
                            }
                        }
                    });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        } else {
            // 没有获取 到权限，从新请求，或者关闭app
            Toast.makeText(GeRenActivity.this, "需要存储权限", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}
