package ti.andsmsverification;

import java.util.Date;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.common.Log;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SMSBroadcastReceiver extends BroadcastReceiver {
	private static final String LCAT = "TiAndroidRequeststoragepermissionModule";
	
	@SuppressWarnings("deprecation")
	@Override
	public void onReceive(Context context, Intent intent) {
		// SMS 메시지를 파싱합니다.
		Bundle bundle = intent.getExtras();
		Object messages[] = (Object[])bundle.get("pdus");
		SmsMessage smsMessage[] = new SmsMessage[messages.length];

		for(int i = 0; i < messages.length; i++) {
		    // PDU 포맷으로 되어 있는 메시지를 복원합니다.
		    smsMessage[i] = SmsMessage.createFromPdu((byte[])messages[i]);
		}

		// SMS 수신 시간 확인
		Date curDate = new Date(smsMessage[0].getTimestampMillis());
		Log.d(LCAT, "문자 수신 시간 : " + curDate.toString());

		// SMS 발신 번호 확인
		String origNumber = smsMessage[0].getOriginatingAddress();

		// SMS 메시지 확인
		String message = smsMessage[0].getMessageBody().toString();
		Log.d(LCAT, "문자 내용 / 발신자 : "+origNumber+", 내용 : " + message);
		
		try {
        	KrollDict data = new KrollDict();
        	data.put("from", origNumber);
        	data.put("message", message);
        	data.put("date", curDate);

        	TiAndroidSMSVerificationModule.getInstance().fireEvent("onSMSReceive", data);
        } catch (Exception e) {
            Log.d(LCAT, "onSMSReceive: " + e.getMessage());
        }
	}
}