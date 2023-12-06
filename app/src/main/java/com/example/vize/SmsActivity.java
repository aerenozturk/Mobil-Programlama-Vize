package com.example.vize;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SmsActivity extends AppCompatActivity {
    EditText msgInput, telInput;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) !=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},1);
        }
        msgInput = findViewById(R.id.msg_input);
        telInput = findViewById(R.id.tel_input);
        btnSend = findViewById(R.id.btn_send);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMesagge();
            }
        });
    }
    private void sendMesagge() {
        String phoneNumber =telInput.getText().toString();
        String message = msgInput.getText().toString();

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber,null,message,null,null);

            Toast.makeText(this,"Mesaj Gönderildi.", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this,"!!!Mesaj Gönderilemedi!!!",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            String errorMessage = "-------";
            Log.e("SmsActivity",errorMessage,e);
            //Derste yapılandan farklı bir Log göstermek istedim.
        }
    }
}