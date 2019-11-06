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
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.StringUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.ImageUtils;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.EnterpriseLogoBean;

import org.json.JSONException;
import org.json.JSONObject;

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

public class ManageCompany extends AppCompatActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.rl_click_add_member)
    RelativeLayout rlClickAddMember;
    @BindView(R.id.rl_click_update_logo)
    RelativeLayout rlClickUpdateLogo;
    @BindView(R.id.rl_click_Enterprise)
    RelativeLayout rlClickEnterprise;
    @BindView(R.id.rl_click_enterprise_name)
    RelativeLayout rlClickEnterpriseName;
    @BindView(R.id.rl_click_scale)
    RelativeLayout rlClickScale;
    @BindView(R.id.rl_click_billing_information)
    RelativeLayout rlClickBillingInformation;
    @BindView(R.id.rl_click_work_address)
    RelativeLayout rlClickWorkAddress;
    @BindView(R.id.rl_click_change_manager)
    RelativeLayout rlClickChangeManager;
    private Intent intent;

    public static Uri tempUri;
    public static final int CHOOSE_PICTURE = 0;
    public static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    private SharedPreferencesHelper sharedPreferencesHelper, sp_create_team;
    private String cid;
    private String uid, token;
    private String TAG = "ManageCompany";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_company);
        ButterKnife.bind(this);
        intent = new Intent();
        //获取cid
        sp_create_team = new SharedPreferencesHelper(this, "Create_team");
        cid = (String) sp_create_team.getSharedPreference("Create_team_cid", "");
        sharedPreferencesHelper = new SharedPreferencesHelper(ManageCompany.this, "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");

    }

    @OnClick({R.id.back, R.id.rl_click_add_member, R.id.rl_click_update_logo, R.id.rl_click_Enterprise, R.id.rl_click_enterprise_name, R.id.rl_click_scale, R.id.rl_click_billing_information, R.id.rl_click_work_address, R.id.rl_click_change_manager, R.id.manage_break_team})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back://返回
                finish();
                break;
            case R.id.rl_click_add_member://添加成员到团队

                break;
            case R.id.rl_click_update_logo://企业Logo上传
                popuinit();
                break;
            case R.id.rl_click_Enterprise://企业认证
                intent.setClass(ManageCompany.this, Enterprise.class);
                startActivity(intent);
                break;
            case R.id.rl_click_enterprise_name://企业名称
                intent.setClass(ManageCompany.this, EnterpriseName.class);
                startActivity(intent);
                break;
            case R.id.rl_click_scale://所在行业
                intent.setClass(ManageCompany.this, Team_Guide.class);
                startActivity(intent);
                break;
            case R.id.rl_click_billing_information://开票信息
                intent.setClass(ManageCompany.this, BillingInformation.class);
                startActivity(intent);
                break;
            case R.id.rl_click_work_address://办公地址
                intent.setClass(ManageCompany.this, WorkAddress.class);
                startActivity(intent);
                break;
            case R.id.rl_click_change_manager://更换主管理员
                intent.setClass(ManageCompany.this, ChangeManager.class);
                startActivity(intent);
                break;
            case R.id.manage_break_team://解散团队
                popuinit2("是否确认解散团队，解散团队后部分信息将永久消失", "取消 ", "确定");
                break;
        }
    }

    private void breakTeam() {
        OkHttpUtils.post()
                .url(Contents.BREAKTEAM)
                .addParams("cid", cid)
                .addParams("token", token)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String errno = (String) jsonObject.get("errno");
                    if (StringUtils.equals(errno, "200")) {
                        Toast.makeText(ManageCompany.this, "团队解散成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void popuinit2(String s, String s1, final String s2) {
        View contentView = LayoutInflater.from(ManageCompany.this).inflate(R.layout.pp_telephone, null);
        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.2f);
        //下面是p里面的东西
        TextView ptextView = contentView.findViewById(R.id.ppt_tv1);
        Button pbutton = contentView.findViewById(R.id.ppt_btn1);
        Button pbutton2 = contentView.findViewById(R.id.ppt_btn2);
        ptextView.setText(s);
        pbutton.setText(s1);
        pbutton2.setText(s2);
        final PopupWindow window = new PopupWindow(contentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        window.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        View rootview = LayoutInflater.from(ManageCompany.this).inflate(R.layout.activity_sz, null);

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

                breakTeam();

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
        window.showAtLocation(rootview, Gravity.CENTER, 0, 0);
    }


    /**
     * 上传图片
     */
    private void popuinit() {
        View contentView = LayoutInflater.from(ManageCompany.this).inflate(R.layout.add_del, null);
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
        View rootview = LayoutInflater.from(ManageCompany.this).inflate(R.layout.activity_ge_ren, null);
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
            int check = ContextCompat.checkSelfPermission(ManageCompany.this, permissions[0]);
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
            tempUri = FileProvider.getUriForFile(ManageCompany.this, "com.ztkj.wky.zhuantou.fileprovider", file);

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
        photo = ImageUtils.toRoundBitmap(photo); // 这个时候的图片已经被处理成圆形的了
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
            Toast.makeText(ManageCompany.this, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
//            return;
        }
        if (imagePath != null) {
//            shouyeIcon.setImageBitmap(bitmap);
            // 拿着imagePath上传了
            // ...
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Disposition", "form-data;filename=enctype");

            OkHttpUtils.post()
                    .url(Contents.UPDATEENTERPEISELOGO)
                    .addParams("cid", cid)
                    .addParams("token", token)
                    .addFile("team_logo", name, file1)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Request request, Exception e) {
                }

                @Override
                public void onResponse(String response) {
                    Log.e(TAG, "onResponse: ========上传头像返回======" + response);
                    EnterpriseLogoBean enterpriseLogoBean = new Gson().fromJson(response, EnterpriseLogoBean.class);
                    if (enterpriseLogoBean.getErrno().equals("200")) {
                        Toast.makeText(ManageCompany.this, "上传成功", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


}
