package com.zuo.aidl_client;

import android.os.RemoteException;

/**
 * IMyAIDLService 接口
 *
 * @author zuo
 * @date 2020/5/12 14:55
 */
public class MyBinder extends IMyAIDLService.Stub {

    @Override
    public String getShowStr() throws RemoteException {
        return "来自客户端的问好";
    }
}
