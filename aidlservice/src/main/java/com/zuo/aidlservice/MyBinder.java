package com.zuo.aidlservice;

import android.os.RemoteException;

/**
 * @author zuo
 * @date 2021/4/6 19:32
 */
public class MyBinder extends IMyAidlInterface.Stub {
    public String clientHint = "";

    @Override
    public DataBean getData() throws RemoteException {
        return DataTempSaveUtils.getInstance().getDataBean();
    }

    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
        clientHint = aString;
    }
}
