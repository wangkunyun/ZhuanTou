package com.ztkj.wky.zhuantou.Activity.chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.ztkj.wky.zhuantou.Activity.chat.activity.SearchFriend;
import com.ztkj.wky.zhuantou.Activity.chat.fragment.AddressFriend;
import com.ztkj.wky.zhuantou.Activity.chat.fragment.AddressTeam;
import com.ztkj.wky.zhuantou.Activity.chat.group.CreateGroupChoosePeople;
import com.ztkj.wky.zhuantou.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressTab extends AppCompatActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tl)
    SegmentTabLayout tl;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.vp)
    ViewPager vp;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles = {"团队", "好友"};
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_tab);
        ButterKnife.bind(this);

        mFragments.add(new AddressTeam());
        mFragments.add(new AddressFriend());

        vp.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        tl.setTabData(mTitles);
        tl.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vp.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tl.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vp.setCurrentItem(0);


    }

    @OnClick({R.id.back, R.id.addFrieds})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.addFrieds:
//                Toast.makeText(this, "敬请期待！！！", Toast.LENGTH_SHORT).show();
                popuinit(view);
                break;
        }
    }


    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

    private void popuinit(View v) {
        View contentView = LayoutInflater.from(this).inflate(R.layout.pp3, null);
        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.7f);
        //下面是p里面的东西lin_pp3_btn1
        LinearLayout lin_pp3_btn1 = contentView.findViewById(R.id.lin_pp3_btn1);
        LinearLayout lin_pp3_btn2 = contentView.findViewById(R.id.lin_pp3_btn2);
        final PopupWindow window = new PopupWindow(contentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        lin_pp3_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundAlpha(1f);
                window.dismiss();
                intent = new Intent(AddressTab.this, SearchFriend.class);
                startActivity(intent);
            }
        });

        lin_pp3_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundAlpha(1f);
                window.dismiss();
                intent = new Intent(AddressTab.this, CreateGroupChoosePeople.class);
                startActivity(intent);
            }
        });

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        window.showAsDropDown(v);
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = AddressTab.this.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        AddressTab.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        AddressTab.this.getWindow().setAttributes(lp);
    }

}
