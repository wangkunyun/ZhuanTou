package com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.apply;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.readapply.AllApplyFor;
import com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.readapply.HaveApply;
import com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.readapply.NoApply;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.XtablayoutAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Examine extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.xTablayout)
    XTabLayout xTablayout;
    @BindView(R.id.apply_choose_type)
    TextView applyChooseType;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.type_car)
    TextView typeCar;
    @BindView(R.id.type_seal)
    TextView typeSeal;
    @BindView(R.id.type_baoxiao)
    TextView typeBaoxiao;
    @BindView(R.id.type_over_time)
    TextView typeOverTime;
    @BindView(R.id.type_leave)
    TextView typeLeave;
    @BindView(R.id.type_go_out)
    TextView typeGoOut;
    @BindView(R.id.type_out_work)
    TextView typeOutWork;
    @BindView(R.id.type_chuchai)
    TextView typeChuchai;
    @BindView(R.id.type_pay)
    TextView typePay;
    @BindView(R.id.lin_choose_type)
    LinearLayout linChooseType;

    private boolean type = false;
    private XtablayoutAdapter xtablayoutAdapter;
    private SharedPreferencesHelper sp_apply;
    List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examine);
        ButterKnife.bind(this);

        layoutTitleTv.setText("审批");
        sp_apply = new SharedPreferencesHelper(this, "apply");

        List<String> titles = new ArrayList<>();
        titles.add("未审批");
        titles.add("已审批");
        titles.add("抄送我的");
        fragments.add(new AllApplyFor());
        fragments.add(new HaveApply());
        fragments.add(new NoApply());


        xtablayoutAdapter = new XtablayoutAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(xtablayoutAdapter);
        viewPager.setOffscreenPageLimit(3);
        xtablayoutAdapter.notifyDataSetChanged();
        //将TabLayout和ViewPager关联起来。
//        xTablayout.setupWithViewPager(viewPager);
        //给TabLayout设置适配器
        xTablayout.setupWithViewPager(viewPager);
    }

    @OnClick({R.id.layout_back, R.id.type_all, R.id.apply_choose_type, R.id.type_car, R.id.type_seal, R.id.type_baoxiao, R.id.type_over_time, R.id.type_leave, R.id.type_go_out, R.id.type_out_work, R.id.type_chuchai, R.id.type_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.apply_choose_type:
                if (!type) {
                    linChooseType.setVisibility(View.VISIBLE);
                    type = true;
                } else {
                    linChooseType.setVisibility(View.GONE);
                    type = false;
                }
                break;
            case R.id.type_all:
                sp_apply.put("sp_apply_type", "0");
                Log.e("AllReport", "onViewClicked: " + sp_apply.getSharedPreference("sp_apply_type", ""));
                type = false;
                fragments.get(0).onResume();
                fragments.get(1).onResume();
                fragments.get(2).onResume();
                linChooseType.setVisibility(View.GONE);
                break;
            case R.id.type_car:
                sp_apply.put("sp_apply_type", "1");
                Log.e("AllReport", "onViewClicked: " + sp_apply.getSharedPreference("sp_apply_type", ""));
                type = false;
                fragments.get(0).onResume();
                fragments.get(1).onResume();
                fragments.get(2).onResume();
                linChooseType.setVisibility(View.GONE);

                break;
            case R.id.type_seal:
                sp_apply.put("sp_apply_type", "2");
                Log.e("AllReport", "onViewClicked: " + sp_apply.getSharedPreference("sp_apply_type", ""));

                type = false;
                fragments.get(0).onResume();
                fragments.get(1).onResume();
                fragments.get(2).onResume();
                linChooseType.setVisibility(View.GONE);
                break;
            case R.id.type_baoxiao:
                sp_apply.put("sp_apply_type", "3");
                Log.e("AllReport", "onViewClicked: " + sp_apply.getSharedPreference("sp_apply_type", ""));

                type = false;
                fragments.get(0).onResume();
                fragments.get(1).onResume();
                fragments.get(2).onResume();
                linChooseType.setVisibility(View.GONE);
                break;
            case R.id.type_over_time:
                sp_apply.put("sp_apply_type", "4");
                Log.e("AllReport", "onViewClicked: " + sp_apply.getSharedPreference("sp_apply_type", ""));

                type = false;
                fragments.get(0).onResume();
                fragments.get(1).onResume();
                fragments.get(2).onResume();
                linChooseType.setVisibility(View.GONE);
                break;
            case R.id.type_leave:
                sp_apply.put("sp_apply_type", "5");
                Log.e("AllReport", "onViewClicked: " + sp_apply.getSharedPreference("sp_apply_type", ""));

                type = false;
                fragments.get(0).onResume();
                fragments.get(1).onResume();
                fragments.get(2).onResume();
                linChooseType.setVisibility(View.GONE);
                break;
            case R.id.type_go_out:
                sp_apply.put("sp_apply_type", "6");
                Log.e("AllReport", "onViewClicked: " + sp_apply.getSharedPreference("sp_apply_type", ""));

                type = false;
                fragments.get(0).onResume();
                fragments.get(1).onResume();
                fragments.get(2).onResume();
                linChooseType.setVisibility(View.GONE);
                break;
            case R.id.type_out_work:
                sp_apply.put("sp_apply_type", "7");
                Log.e("AllReport", "onViewClicked: " + sp_apply.getSharedPreference("sp_apply_type", ""));

                type = false;
                fragments.get(0).onResume();
                fragments.get(1).onResume();
                fragments.get(2).onResume();
                linChooseType.setVisibility(View.GONE);
                break;
            case R.id.type_chuchai:
                sp_apply.put("sp_apply_type", "8");
                Log.e("AllReport", "onViewClicked: " + sp_apply.getSharedPreference("sp_apply_type", ""));

                type = false;
                fragments.get(0).onResume();
                fragments.get(1).onResume();
                fragments.get(2).onResume();
                linChooseType.setVisibility(View.GONE);
                break;
            case R.id.type_pay:
                sp_apply.put("sp_apply_type", "9");
                Log.e("AllReport", "onViewClicked: " + sp_apply.getSharedPreference("sp_apply_type", ""));

                type = false;
                fragments.get(0).onResume();
                fragments.get(1).onResume();
                fragments.get(2).onResume();
                linChooseType.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sp_apply.clear();
    }
}
