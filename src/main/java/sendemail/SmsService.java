package sendemail;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class SmsService extends Service {
    private SmsRecevier recevier;
    private boolean isListening = false;
    private static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";
    private String sendTo;

    @Override
    public void onCreate(){
        recevier = new SmsRecevier();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        IntentFilter filter = new IntentFilter(ACTION);
        filter.setPriority(1000);// 设置优先级最大
        registerReceiver(recevier, filter);
        isListening = true;

        super.onStart(intent, startId);

        Toast.makeText(this, "开始转发", Toast.LENGTH_SHORT).show();
        Log.d("SmsService", "onServiceStart");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (recevier != null && isListening) {
            unregisterReceiver(recevier);
            isListening = false;
            Toast.makeText(this, "停止转发成功", Toast.LENGTH_SHORT).show();
        }

        super.onDestroy();
        Log.d("SmsService", "onServiceDestroy");
    }

    //其他对象通过bindService 方法通知该Service时该方法被调用
    @Override
    public IBinder onBind(Intent intent) {
        IntentFilter filter = new IntentFilter(ACTION);
        filter.setPriority(1000);// 设置优先级最大
        registerReceiver(recevier, filter);
        isListening = true;
        Toast.makeText(this, "开始转发", Toast.LENGTH_SHORT).show();
        Log.d("SmsService", "onServiceBind");
        return null;
    }

    //其它对象通过unbindService方法通知该Service时该方法被调用
    @Override
    public boolean onUnbind(Intent intent) {
        if (recevier != null && isListening) {
            unregisterReceiver(recevier);
            isListening = false;
            Toast.makeText(this, "停止转发成功", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "尚未开始", Toast.LENGTH_SHORT).show();
        Log.d("SmsService", "onServiceUnbind");
        return super.onUnbind(intent);
    }

}
