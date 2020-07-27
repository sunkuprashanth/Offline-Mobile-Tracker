package com.example.offlinemobiletracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import static java.lang.Boolean.FALSE;

public class MainActivity extends AppCompatActivity implements LocationListener {

    double latitude;
    String phone,location_coor;
    double longitude;
    Button add_phone;
    LocationManager locationGPS;
    public boolean send = FALSE;
    Context context = this;
    static MainActivity ins;
    SharedPreferences.Editor my_store;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add_phone = findViewById(R.id.add_phone);
        ins = this;
        locationGPS = (LocationManager) getSystemService(context.LOCATION_SERVICE);

        SharedPreferences sharedPreferences = getSharedPreferences("store", MODE_PRIVATE);
        my_store = sharedPreferences.edit();

        SharedPreferences pref= getSharedPreferences("store", MODE_PRIVATE);
        phone = sharedPreferences.getString("phone",phone);
        Toast.makeText(this,phone,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                phone = data.getStringExtra("mobile_num");
                my_store.putString("phone",phone);
                my_store.commit();
                Toast.makeText(this,phone,Toast.LENGTH_LONG).show();
            }
        }
    }

    public void phone(View v)
    {
        Intent i = new Intent(MainActivity.this,AddPhone.class);
        startActivityForResult(i,1);
    }
    public void track(View v)
    {
        startActivity(new Intent(MainActivity.this,Track.class));
    }

    public static MainActivity getInst() { return ins; }

    public void send(String mobile,String location_coor){


        String msg=location_coor;
        SmsManager sms= SmsManager.getDefault();
        sms.sendTextMessage(mobile,null,msg,null,null);
        Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();
        send = FALSE;
    }

    public void get_gps() {

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            locationGPS.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 5, this);

        } else
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
    }

    @Override
    public void onLocationChanged(Location loc) {

        if(loc!=null) {
            longitude = loc.getLongitude();
            latitude = loc.getLatitude();

            location_coor = latitude+","+longitude;

            locationGPS.removeUpdates(this);

            if(send)
                send(phone,"device at:"+location_coor);
            else
                Toast.makeText(this, "Send Is False", Toast.LENGTH_SHORT).show();
        }
    }

    public void open_maps(String link){

        Intent mapIntent = new Intent(MainActivity.this, Locate_device.class);
        mapIntent.putExtra("locate",link);
        startActivity(mapIntent);
    }
    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

}

