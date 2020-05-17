package com.zuo.aidlservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zuo.aidlservice.databinding.ActivityMainBinding;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.IntRange;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    private List<Integer> data;
    @IntRange(from = 0, to = 3)
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setPresenter(new Presenter());
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(this, MyAIDLService.class);
        try {
            bindService(intent, conn, BIND_AUTO_CREATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initData() {
        data = new ArrayList<>();
        data.add(R.drawable.kb272);
        data.add(R.drawable.kb526);
        data.add(R.drawable.kb1488);
        showImg();
    }

    private void showImg() {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), data.get(index));
        binding.imgShow.setImageBitmap(bmp);
        binding.indexShow.setText((index + 1) + "/" + data.size());
        String hint = "服务端正在展示第 " + (index + 1) + " 张照片";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] array = null;
        if (null != bmp) {
            bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
            array = baos.toByteArray();
        }
        SocketParseBean socketParseBean = new SocketParseBean(hint, array);
        DataTempSaveUtils.getInstance().setSocketParseBean(socketParseBean);
    }

    /**
     * 实现 ServiceConnection。
     */
    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IMyAIDLService iMyAIDLService = IMyAIDLService.Stub.asInterface(service);
            try {
                SocketParseBean data = iMyAIDLService.getData();
                binding.backMsgShow.setText(data.getInfo());
            } catch (Exception e) {
                Log.i(TAG, "onServiceConnected: " + e.getMessage());
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    public class Presenter {

        public void last(View view) {
            if (index <= 0) {
                Toast.makeText(MainActivity.this, "没有上一张了！", Toast.LENGTH_SHORT).show();
                return;
            }
            index--;
            showImg();
        }

        public void next(View view) {
            if (index >= 2) {
                Toast.makeText(MainActivity.this, "没有下一张了！", Toast.LENGTH_SHORT).show();
                return;
            }
            index++;
            showImg();
        }
    }
}
