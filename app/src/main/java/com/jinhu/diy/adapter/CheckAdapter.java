package com.jinhu.diy.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.jinhu.diy.R;
import com.jinhu.diy.bean.CheckBean;

import java.util.List;

/**
 * 类的用途：
 * Created by jinhu
 * 2017/5/12  19:39
 */

public class CheckAdapter extends RecyclerView.Adapter<CheckAdapter.ViewHolder> {

    List<CheckBean> list;
    private OnClickListener mListener;

    public CheckAdapter(List<CheckBean> list) {
        this.list = list;
    }

    public interface OnClickListener {

        void setOnClickListener(boolean flag);

    }

    public void setOnClickListener(OnClickListener l) {

        this.mListener = l;

    }

    public void allBoxChecked(boolean flag) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).check = flag;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_check, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CheckBean checkBean = list.get(position);
        holder.mTextView.setText(checkBean.getName());
        holder.mCheckBox.setChecked(checkBean.check);

        holder.mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = holder.mCheckBox.isChecked();
                checkBean.check = checked;
                //当所有都选择时，全选自动选择
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).check) {
                        mListener.setOnClickListener(true);
                    } else {
                        mListener.setOnClickListener(false);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        CheckBox mCheckBox;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.item_text_check);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.item_check);
        }
    }

}
