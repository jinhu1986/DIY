package com.jinhu.diy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jinhu.diy.R;
import com.jinhu.diy.bean.ShopBean;

import java.util.List;

/**
 * 类的用途：
 * Created by jinhu
 * 2017/5/13  11:05
 */

public class JiesuanAdapter extends RecyclerView.Adapter {
    List<ShopBean.DataBean> mList;
    Context mContext;

    public JiesuanAdapter(List<ShopBean.DataBean> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_more_01, parent, false);
                ViewHolder_01 holder_01 = new ViewHolder_01(view);
                return holder_01;
            case 1:
                View view_02 = LayoutInflater.from(mContext).inflate(R.layout.item_more_02, parent, false);
                ViewHolder_02 holder_02 = new ViewHolder_02(view_02);
                return holder_02;
            case 2:
                View view_03 = LayoutInflater.from(mContext).inflate(R.layout.item_more_03, parent, false);
                ViewHolder_03 holder_03 = new ViewHolder_03(view_03);
                return holder_03;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ShopBean.DataBean dataBean = mList.get(position);
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case 0:
                ViewHolder_01 holder_01 = (ViewHolder_01) holder;
                Glide.with(mContext).load(dataBean.getGoods_img()).into(holder_01.mImageView_01);
                holder_01.mTextView.setText(dataBean.getGoods_name());
                break;
            case 1:
                ViewHolder_02 holder_02 = (ViewHolder_02) holder;
                Glide.with(mContext).load(dataBean.getGoods_img()).into(holder_02.mImageView_01);
                Glide.with(mContext).load(dataBean.getGoods_img()).into(holder_02.mImageView_02);
                holder_02.mTextView.setText(dataBean.getGoods_name());
                break;
            case 2:
                ViewHolder_03 holder_03 = (ViewHolder_03) holder;
                Glide.with(mContext).load(dataBean.getGoods_img()).into(holder_03.mImageView_01);
                holder_03.mTextView.setText(dataBean.getGoods_name());
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        int i = position % 3;
        switch (i) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder_01 extends RecyclerView.ViewHolder {
        ImageView mImageView_01;
        TextView mTextView;
        ImageView mImageView_02;

        public ViewHolder_01(View itemView) {
            super(itemView);
            mImageView_01 = (ImageView) itemView.findViewById(R.id.iv_item_01_more_01);
            mTextView = (TextView) itemView.findViewById(R.id.tv_item_01_more_01);
        }
    }

    static class ViewHolder_02 extends RecyclerView.ViewHolder {
        ImageView mImageView_01;
        TextView mTextView;
        ImageView mImageView_02;

        public ViewHolder_02(View itemView) {
            super(itemView);
            mImageView_01 = (ImageView) itemView.findViewById(R.id.iv_item_02_more_01);
            mTextView = (TextView) itemView.findViewById(R.id.tv_item_02_more_01);
            mImageView_02 = (ImageView) itemView.findViewById(R.id.iv_item_02_more_02);
        }
    }

    static class ViewHolder_03 extends RecyclerView.ViewHolder {
        ImageView mImageView_01;
        TextView mTextView;
        ImageView mImageView_02;

        public ViewHolder_03(View itemView) {
            super(itemView);
            mImageView_01 = (ImageView) itemView.findViewById(R.id.iv_item_03_more_01);
            mTextView = (TextView) itemView.findViewById(R.id.tv_item_03_more_01);
        }
    }
}
