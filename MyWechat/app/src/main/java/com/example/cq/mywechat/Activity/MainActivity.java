package com.example.cq.mywechat.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

import com.example.cq.mywechat.Adapters.FragmentAdapter;
import com.example.cq.mywechat.Fragments.PyqFragment;
import com.example.cq.mywechat.Fragments.TxlFragment;
import com.example.cq.mywechat.Fragments.WoFragment;
import com.example.cq.mywechat.R;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements android.view.View.OnClickListener {
    private ViewPager mViewPager;// 用来放置界面切换
    private PagerAdapter mPagerAdapter;// 初始化View适配器
    private List<View> mViews = new ArrayList<View>();// 用来存放Tab01-04
    // 四个Tab，每个Tab包含一个按钮
    private LinearLayout mTabAddress;
    private LinearLayout mTabFrd;
    private LinearLayout mTabSetting;
    // 四个按钮
    private ImageView mAddressImg;
    private ImageView mFrdImg;
    private ImageView mSettingImg;

    private TxlFragment txlFragment;
    private WoFragment woFragment;
    private PyqFragment pyqFragment;
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private FragmentAdapter fragmentAdapter;

    private ImageButton top_add;
    private int id;
    public Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        Intent intent = getIntent();
        setContentView(R.layout.activity_main);

        initView();
        initViewPage();
        initEvent();


    }

    private void initViewPage() {
        // 初始化三个布局

        txlFragment = new TxlFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id",id);

        txlFragment.setArguments(bundle);


        pyqFragment = new PyqFragment();
        woFragment = new WoFragment();

        fragments.add(txlFragment);
        fragments.add(pyqFragment);
        fragments.add(woFragment);

        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),fragments);
        mViewPager.setAdapter(fragmentAdapter);

        /*//适配器初始化并设置
        mPagerAdapter = new PagerAdapter() {
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mViews.get(position));
            }
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = mViews.get(position);
                container.addView(view);
                return view;
            }
            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }
            @Override
            public int getCount() {
                return mViews.size();
            }
        };
*/
    }

    private void initEvent() {
        top_add.setOnClickListener(this);
        mTabAddress.setOnClickListener(this);
        mTabFrd.setOnClickListener(this);
        mTabSetting.setOnClickListener(this);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //ViewPage左右滑动时
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
            @Override
            public void onPageSelected(int arg0) {
                int currentItem = mViewPager.getCurrentItem();
                switch (currentItem) {
                    case 0:
                        resetImg();
                        mAddressImg.setImageResource(R.drawable.txl_selected);
                        break;
                    case 1:
                        resetImg();
                        mFrdImg.setImageResource(R.drawable.pyq_selected);
                        break;
                    case 2:
                        resetImg();
                        mSettingImg.setImageResource(R.drawable.wo_selected);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });


        top_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this,v);
                popupMenu.getMenuInflater().inflate(R.menu.popumenu,popupMenu.getMenu());
                popupMenu.show();
            }
        });

    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpage);

        // 初始化四个LinearLayout
        mTabAddress = (LinearLayout) findViewById(R.id.id_tab_address);
        mTabFrd = (LinearLayout) findViewById(R.id.id_tab_frd);
        mTabSetting = (LinearLayout) findViewById(R.id.id_tab_settings);
        // 初始化四个按钮
        mAddressImg = (ImageView) findViewById(R.id.id_tab_address_img);
        mFrdImg = (ImageView) findViewById(R.id.id_tab_frd_img);
        mSettingImg = (ImageView) findViewById(R.id.id_tab_settings_img);

        top_add = findViewById(R.id.top_add);

    }

    //判断显示哪个，并设置按钮图片
    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.id_tab_address:
                mViewPager.setCurrentItem(0);
                resetImg();
                mAddressImg.setImageResource(R.drawable.txl_selected);
                break;
            case R.id.id_tab_frd:
                mViewPager.setCurrentItem(1);
                resetImg();
                mFrdImg.setImageResource(R.drawable.pyq_selected);
                break;
            case R.id.id_tab_settings:
                mViewPager.setCurrentItem(2);
                resetImg();
                mSettingImg.setImageResource(R.drawable.wo_selected);
                break;
            default:
                break;
        }
    }



    private void resetImg() {
        mAddressImg.setImageResource(R.drawable.txl);
        mFrdImg.setImageResource(R.drawable.pyq);
        mSettingImg.setImageResource(R.drawable.wo);
    }
}

