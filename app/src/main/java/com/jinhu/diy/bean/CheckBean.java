package com.jinhu.diy.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 类的用途：
 * Created by jinhu
 * 2017/5/12  19:16
 */

public class CheckBean implements Parcelable {
    public int id;
    public String image;
    public String name;
    public boolean check;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.image);
        dest.writeString(this.name);
        dest.writeByte(this.check ? (byte) 1 : (byte) 0);
    }

    public CheckBean() {
    }

    protected CheckBean(Parcel in) {
        this.id = in.readInt();
        this.image = in.readString();
        this.name = in.readString();
        this.check = in.readByte() != 0;
    }

    public static final Parcelable.Creator<CheckBean> CREATOR = new Parcelable.Creator<CheckBean>() {
        @Override
        public CheckBean createFromParcel(Parcel source) {
            return new CheckBean(source);
        }

        @Override
        public CheckBean[] newArray(int size) {
            return new CheckBean[size];
        }
    };
}
