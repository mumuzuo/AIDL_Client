package com.zuo.aidlservice;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 解析socket服务传递的数据
 *
 * @author zuo
 * @date 2020/5/15 17:03
 */
public class SocketParseBean implements Parcelable {

    private String info;
    private byte[] data;

    public SocketParseBean() {
    }

    public SocketParseBean(String info, byte[] data) {
        this.info = info;
        this.data = data;
    }

    protected SocketParseBean(Parcel in) {
        info = in.readString();
        data = in.createByteArray();
    }

    public static final Creator<SocketParseBean> CREATOR = new Creator<SocketParseBean>() {
        @Override
        public SocketParseBean createFromParcel(Parcel in) {
            return new SocketParseBean(in);
        }

        @Override
        public SocketParseBean[] newArray(int size) {
            return new SocketParseBean[size];
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(info);
        dest.writeByteArray(data);
    }
}
