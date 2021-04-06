package com.zuo.aidl_client;

import android.os.RemoteException;

import com.zuo.aidlservice.DataBean;
import com.zuo.aidlservice.IMyAidlInterface;

/**
 * @author zuo
 * @date 2021/4/6 18:53
 */
public class ClientBinder extends IMyAidlInterface.Stub {
    private DataBean dataBean;


    public ClientBinder setDataBean(DataBean dataBean) {
        this.dataBean = dataBean;
        return this;
    }

    @Override
    public DataBean getData() throws RemoteException {
        return dataBean;
    }

    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }
}
