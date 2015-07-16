package sendemail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SmsListenerActivity extends Activity {

    private EditText sendTo;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

        sendTo = (EditText) findViewById(R.id.sendTo);
	}

	public void start(View v) {
        Intent intent = new Intent(SmsListenerActivity.this,SmsService.class);
        MailSenderInfo.getInstance().setToAddress(sendTo.getText().toString());
        //开始服务
        startService(intent);
	}

	public void stop(View v) {
        Intent intent = new Intent(SmsListenerActivity.this,SmsService.class);
        //停止服务
        stopService(intent);
	}

}
