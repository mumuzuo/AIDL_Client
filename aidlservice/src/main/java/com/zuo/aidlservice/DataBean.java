package com.zuo.aidlservice;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 解析socket服务传递的数据
 *
 * @author zuo
 * @date 2020/5/15 17:03
 */
public class DataBean implements Parcelable {

    private String info;
    private byte[] data;

    public DataBean() {
    }

    public DataBean(String info, byte[] data) {
        this.info = info;
        this.data = data;
    }

    protected DataBean(Parcel in) {
        info = in.readString();
        data = in.createByteArray();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(info);
        dest.writeByteArray(data);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
        @Override
        public DataBean createFromParcel(Parcel in) {
            return new DataBean(in);
        }

        @Override
        public DataBean[] newArray(int size) {
            return new DataBean[size];
        }
    };

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

}
