package com.ztkj.wky.zhuantou.Activity.oa;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.ImageUtils;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.IsUpDatePictureBean;
import com.ztkj.wky.zhuantou.bean.UpdatePictureBeam;
import com.ztkj.wky.zhuantou.landing.NewLoginActivity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

public class Enterprise extends AppCompatActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.legal_person_name)
    EditText legalPersonName;
    @BindView(R.id.legal_person_ID)
    EditText legalPersonID;
    @BindView(R.id.business_license_number)
    EditText businessLicenseNumber;
    @BindView(R.id.re_update_picture)
    RelativeLayout reUpdatePicture;
    @BindView(R.id.submit_enterprise)
    Button submitEnterprise;

    @BindView(R.id.img_add_picture)
    ImageView imgAddPicture;

    private String TAG = "Enterprise";
    private SharedPreferencesHelper sharedPreferencesHelper, sp_create_team;
    private String cid;
    private String uid, token;
    private Intent intent;

    public static Uri tempUri;
    public static final int CHOOSE_PICTURE = 0;
    public static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise);
        ButterKnife.bind(this);
        //获取cid
        sp_create_team = new SharedPreferencesHelper(this, "Create_team");
        cid = (String) sp_create_team.getSharedPreference("Create_team_cid", "");
        sharedPreferencesHelper = new SharedPreferencesHelper(Enterprise.this, "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");


    }

    @OnClick({R.id.back, R.id.img_add_picture, R.id.submit_enterprise})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.img_add_picture:
                popuinit();
                break;
            case R.id.submit_enterprise:
                if (legalPersonName.getText().toString().equals("")) {
                    Toast.makeText(this, "请输入法人信息", Toast.LENGTH_SHORT).show();
                    return;
                } else if (legalPersonID.getText().toString().equals("")) {
                    Toast.makeText(this, "请输入法人身份证号", Toast.LENGTH_SHORT).show();
                    return;
                } else if (businessLicenseNumber.getText().toString().equals("")) {
                    Toast.makeText(this, "请输入营业执照", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    request();
                }
                break;
        }
    }

    private void request() {
        OkHttpUtils.post()
                .url(Contents.ISUPDATEPICTURE)
                .addParams("cid", cid)
                .addParams("token", token)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                IsUpDatePictureBean isUpDatePictureBean = new Gson().fromJson(response, IsUpDatePictureBean.class);
                int type = isUpDatePictureBean.getData().getType();  // 0==未上传  1==已上传
                if (type == 1) {
                    OkHttpUtils.post()
                            .url(Contents.ENTERPRISE)
                            .addParams("cid", cid)
                            .addParams("token", token)
                            .addParams("legal_person", legalPersonName.getText().toString())
                            .addParams("id_number", legalPersonID.getText().toString())
                            .addParams("registration_id", businessLicenseNumber.getText().toString())
                            .build().execute(new StringCallback() {
                        @Override
                        public void onError(Request request, Exception e) {

                        }

                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(Enterprise.this, "上传成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(Enterprise.this, "请上传营业执照", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    /**
     * 上传图片
     */
    private void popuinit() {
        View contentView = LayoutInflater.from(Enterprise.this).inflate(R.layout.add_del, null);
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
        View rootview = LayoutInflater.from(Enterprise.this).inflate(R.layout.activity_ge_ren, null);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            //点击其他地方取消
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        popz.setOnClickListener(new View.OnClickListener() {
            //点击取消 取消
            @Override
            public void onClick(View v) {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        //调用相机
        tcdl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        //调取相册
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

    /**
     * 调用相机
     */
    private void takePicture() {
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (Build.VERSION.SDK_INT >= 23) {
            // 需要申请动态权限
            int check = ContextCompat.checkSelfPermission(Enterprise.this, permissions[0]);
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
            tempUri = FileProvider.getUriForFile(Enterprise.this, "com.ztkj.wky.zhuantou.fileprovider", file);

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
        intent.putExtra("aspectX", 298);
        intent.putExtra("aspectY", 210);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 298);
        intent.putExtra("outputY", 210);
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
        Log.e("imagePath", imagePath + "+" + name);
        if (!file1.exists()) {
            Toast.makeText(Enterprise.this, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
//            return;
        }
        if (imagePath != null) {
//            shouyeIcon.setImageBitmap(bitmap);
            // 拿着imagePath上传了
            // ...
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Disposition", "form-data;filename=enctype");

            OkHttpUtils.post()
                    .url(Contents.UPDATEPICTURE)
                    .addParams("cid", cid)
                    .addParams("token", token)
                    .addFile("images", name, file1)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Request request, Exception e) {

                }

                @Override
                public void onResponse(String response) {
                    UpdatePictureBeam updatePictureBeam = new Gson().fromJson(response, UpdatePictureBeam.class);
                    Log.e(TAG, "onResponse: " + response);
                    if (updatePictureBeam.getErrno().equals("200")) {
                        Log.e(TAG, "onResponse: " + updatePictureBeam.getData().getBusiness_license());
                        Glide.with(Enterprise.this).load(updatePictureBeam.getData().getBusiness_license()).into(imgAddPicture);

                    } else if (updatePictureBeam.getErrno().equals("666666")) {
                        Toast.makeText(Enterprise.this, "您的账号已在其他手机登录，如非本人操作，请修改密码", Toast.LENGTH_LONG).show();
                        JPushInterface.deleteAlias(Enterprise.this, Integer.parseInt(uid));
                        sharedPreferencesHelper.clear();
                        SPUtils.getInstance().clear();
                        intent = new Intent(Enterprise.this, NewLoginActivity.class);
                        startActivity(intent);
                        ActivityManager.getInstance().exit();
                        finish();
                    }
                }
            });
//                    .execute(new StringCallback() {
//                        @Override
//                        public void onError(Request request, Exception e) {
////                            Log.d("icon_response","失败"+request);
//                        }
//
//                        @Override
//                        public void onResponse(String response) {
//                            Log.d("icon_response", response);
//                            Gson gson = new Gson();
//                            HeadBean headBean = gson.fromJson(response, HeadBean.class);
//                            if (headBean.getErrno().equals("200")) {
//
//
//                            } else if (headBean.getErrno().equals("666666")) {
//                                Toast.makeText(Enterprise.this, "您的账号已在其他手机登录，如非本人操作，请修改密码", Toast.LENGTH_LONG).show();
//                                JPushInterface.deleteAlias(Enterprise.this, Integer.parseInt(uid));
//                                sharedPreferencesHelper.clear();
//                                intent = new Intent(Enterprise.this, NewLoginActivity.class);
//                                startActivity(intent);
//                                ActivityManager.getInstance().exit();
//                                finish();
//                            }
//                        }
//                    });
        }
    }

}
