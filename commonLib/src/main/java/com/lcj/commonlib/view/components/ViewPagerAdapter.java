package com.lcj.commonlib.view.components;

import android.view.View;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ViewPagerAdapter extends PagerAdapter {

    private ArrayList<View> views;

    public ViewPagerAdapter(ArrayList<View> views2) {
        this.views = views2;
    }

    //获得视图总数
    @Override
    public int getCount() {
        if (views != null) {
            return views.size();
        }
        return 0;
    }

    //判断是否由对象生成视图
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return (arg0 == arg1);
    }

    //初始化position位置的视图
    @NotNull
    @Override
    public Object instantiateItem(View container, int position) {
        ((ViewPager) container).addView(views.get(position), 0);
        return views.get(position);
    }

    //销毁position位置的视图
    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView(views.get(position));
    }
}
