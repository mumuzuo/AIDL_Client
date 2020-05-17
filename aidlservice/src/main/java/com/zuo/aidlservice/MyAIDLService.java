package com.zuo.aidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

/**
 * 向客户端公开 IMyAIDLService 接口
 *
 * @author zuo
 * @date 2020/5/12 14:55
 */
public class MyAIDLService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    static class MyBinder extends IMyAIDLService.Stub {

        @Override
        public SocketParseBean getData() throws RemoteException {
            return DataTempSaveUtils.getInstance().getSocketParseBean();
        }
    }
}
