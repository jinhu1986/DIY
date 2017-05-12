package com.jinhu.diy.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jinhu.diy.R;
import com.jinhu.diy.bean.GetBean;
import com.jinhu.diy.util.ImageUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * 类的用途：
 * Created by jinhu
 * 2017/5/11  14:12
 */

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mName;
        TextView mTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.image_01);
            mName = (TextView) itemView.findViewById(R.id.text_01);
            mTitle = (TextView) itemView.findViewById(R.id.text_02);
        }
    }

    private List<GetBean.ResultBean.DataBean> list;

    public RvAdapter(List<GetBean.ResultBean.DataBean> list) {
        this.list = list;
    }

    @Override
    public RvAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RvAdapter.ViewHolder holder, int position) {
        GetBean.ResultBean.DataBean dataBean = list.get(position);
        ImageLoader.getInstance().displayImage(dataBean.getThumbnail_pic_s(), holder.mImageView, ImageUtils.getOptions());
        holder.mName.setText(dataBean.getAuthor_name());
        holder.mTitle.setText(dataBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
