package com.example.offlinemobiletracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import java.util.StringTokenizer;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class RecieveSMS extends BroadcastReceiver {

    boolean send = FALSE;
    Context context;
    String phone;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];
            for (int i = 0; i < 1; i++) {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                String phone = msgs[i].getOriginatingAddress();
                String body = msgs[i].getMessageBody();
                read_sms(phone, body);
            }
        }
    }
    public void read_sms(String mobile, String msg) {
        phone = MainActivity.getInst().phone;
        if (msg.equals("Get GPS")) {
            if (mobile.contains(phone)) {
                send = TRUE;
                MainActivity.getInst().get_gps();
                MainActivity.getInst().send = TRUE;
            } else {
                MainActivity.getInst().send(mobile,"Not Authorised");
            }
        }
        else if(msg.contains("device at:")){
            StringTokenizer st = new StringTokenizer(msg,"device at:");
            //st = new StringTokenizer(st.nextToken(),",");
            MainActivity.getInst().open_maps(st.nextToken());
        }
    }
}