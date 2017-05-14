package com.jinhu.diy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jinhu.diy.R;
import com.jinhu.diy.bean.ShopBean;

import java.util.List;

/**
 * 类的用途：
 * Created by jinhu
 * 2017/5/13  8:28
 */

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {
    List<ShopBean.DataBean> list;
    Context mContext;

    public ShopAdapter(List<ShopBean.DataBean> list, Context context) {
        this.list = list;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public void allBoxChecked(boolean flag) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setIs_allow_credit(flag);
        }
    }

    public void checkBoxFanXuan() {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setIs_allow_credit(!list.get(i).isIs_allow_credit());
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ShopBean.DataBean dataBean = list.get(position);
        Glide.with(mContext).load(dataBean.getGoods_img()).into(holder.mImageView);
        holder.mTextView_01.setText(dataBean.getGoods_name());
        holder.mTextView_02.setText(dataBean.getEfficacy());
        holder.mTextView_03.setText(dataBean.getShop_price() + "");
        holder.mCheckBox.setChecked(dataBean.isIs_allow_credit());
        holder.mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = holder.mCheckBox.isChecked();
                dataBean.setIs_allow_credit(checked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextView_01;
        TextView mTextView_02;
        TextView mTextView_03;
        CheckBox mCheckBox;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.image_01);
            mTextView_01 = (TextView) itemView.findViewById(R.id.text_01);
            mTextView_02 = (TextView) itemView.findViewById(R.id.text_02);
            mTextView_03 = (TextView) itemView.findViewById(R.id.text_03);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.check_item_00);
        }
    }
}
