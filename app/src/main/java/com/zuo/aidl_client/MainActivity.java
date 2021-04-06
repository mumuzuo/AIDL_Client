package com.zuo.aidl_client;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;

import com.zuo.aidl_client.databinding.ActivityMainBinding;
import com.zuo.aidlservice.DataBean;
import com.zuo.aidlservice.IMyAidlInterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    private boolean isBound;
    private IMyAidlInterface aidlInterface;
    private ClientBinder clientBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setPresenter(new Presenter());
        bindService();
        clientBinder = new ClientBinder();
    }


    @Override
    protected void onPause() {
        super.onPause();
        //解绑服务
        if (isBound) {
            try {
                unbindService(conn);
                isBound = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 实现 ServiceConnection。
     */
    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            aidlInterface = IMyAidlInterface.Stub.asInterface(service);
            getServiceData();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            aidlInterface = null;
        }
    };

    private void getServiceData() {
        DataBean data = null;
        try {
            data = aidlInterface.getData();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        binding.backMsgShow.setText(data == null ? "返回错误！" : data.getInfo());
        if (data != null && data.getData() != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(data.getData(), 0, data.getData().length);
            binding.imgShow.setImageBitmap(bitmap);
        }
    }

    public class Presenter {
        /**
         * 绑定服务，设置绑定后自动开启服务
         *
         * @return
         */
        public void toMsg(View view) {
         /*   String s = binding.clientInput.getText().toString();
            if (!TextUtils.isEmpty(s)) {
                try {
                    clientBinder.basicTypes(0,0,false,0,0,s);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }*/
            getServiceData();
        }
    }

    public void bindService() {
        Intent intent = new Intent();
        intent.setAction("com.zuo.aidlservice.MyAIDLService");
        //待使用远程Service所属应用的包名
        intent.setPackage("com.zuo.aidlservice");
        try {
            MainActivity.this.bindService(intent, conn, BIND_AUTO_CREATE);
            isBound = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
