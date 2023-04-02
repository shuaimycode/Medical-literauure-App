package com.he.yiliao;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fragment> mFragments;   //存放视图
    private ViewPager viewPager;
    private TabLayout mTabLayout;
    private List<String> mtitle;  //存放底部标题

    private ImageButton imbtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide(); //隐藏标题栏
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        imbtn = findViewById(R.id.imbtn_search);
        imbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });


    }

    private void initView() {
        mTabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);

        mFragments=new ArrayList<>();
        mFragments.add(new home());
        mFragments.add(new Literature());
        mFragments.add(new me());

        mtitle=new ArrayList<String>();
        mtitle.add("主页");
        mtitle.add("文献");
        mtitle.add("我的");

        //实例化适配器
        MyAdapt adapt = new MyAdapt(getSupportFragmentManager(), mFragments, mtitle);
        viewPager.setAdapter(adapt);

        mTabLayout.setupWithViewPager(viewPager);//给tab设置一个viewpager
        //viewpager的监听
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override  //选中
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        Toast.makeText(MainActivity.this, "这是主页", Toast.LENGTH_SHORT).show();
                        break;

                    case 1:
                        Toast.makeText(MainActivity.this, "这是文献", Toast.LENGTH_SHORT).show();
                        break;

                    case 2:
                        Toast.makeText(MainActivity.this, "这是我的", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override   // //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
