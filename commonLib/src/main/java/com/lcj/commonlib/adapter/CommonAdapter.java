package com.lcj.commonlib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.util.ArrayList;


public abstract class CommonAdapter<T> extends Adapter<RecyclerView.ViewHolder> {
    protected ArrayList<T> mData;
    protected final LayoutInflater mInflater;
    protected Context mContext;
    protected View mHeaderView;
    protected static final int HEADER_TYPE = 0;
    protected static final int ITEM_TYPE = 1;
    protected int headerViewsCount = 0;

    protected CommonAdapter(Context context, ArrayList<T> dataList) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mData = dataList;
    }

    public void addDatas(ArrayList<T> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    public void setHeaderView(View view) {
        mHeaderView = view;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < headerViewsCount) {
            return HEADER_TYPE;
        } else {
            return ITEM_TYPE;
        }
    }
}
