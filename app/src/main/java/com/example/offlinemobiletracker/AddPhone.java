package com.example.offlinemobiletracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddPhone extends AppCompatActivity {

    EditText phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phone);

        phone = findViewById(R.id.phone);


    }

    public void updateNumber(View v)
    {

        String mobile = phone.getText().toString();
        if(mobile !="")
        {
            Intent i = new Intent();
            i.putExtra("mobile_num",mobile);
            setResult(RESULT_OK,i);
            finish();
        }

    }
}
