package com.zuo.aidl_client;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.zuo.aidl_client.databinding.ActivityMainBinding;
import com.zuo.aidlservice.IMyAIDLService;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    private boolean isBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setPresenter(new Presenter());
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
            IMyAIDLService iMyAIDLService = IMyAIDLService.Stub.asInterface(service);
            try {
                String showStr = iMyAIDLService.getShowStr();
                binding.backMsgShow.setText(TextUtils.isEmpty(showStr) ? "返回错误！" : showStr);
            } catch (Exception e) {
                Log.i(TAG, "onServiceConnected: " + e.getMessage());
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public class Presenter {
        /**
         * 绑定服务，设置绑定后自动开启服务
         *
         * @return
         */
        public void bindService(View view) {
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
}
