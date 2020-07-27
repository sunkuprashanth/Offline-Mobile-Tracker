package com.example.offlinemobiletracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.KeyEventDispatcher;

import android.app.Activity;
import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.Boolean.FALSE;

public class Track extends AppCompatActivity {

    EditText track_mob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        track_mob = findViewById(R.id.track_mob);

    }
    public void track(View v){
        MainActivity.getInst().send(track_mob.getText().toString(),"Get GPS");
        //DevicePolicyManager mDPM = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        //ComponentName mdev = new ComponentName(this, MyDeviceAdmin.class);
        //Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        //intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mdev);
        //mDPM.lockNow();

        //Uri gmmIntentUri = Uri.parse("http://maps.google.com/maps?daddr=17.361488,78.5286381");
    }
}