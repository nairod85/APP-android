package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AppelActivity extends AppCompatActivity {

    private Button btnCall;
    private EditText numAppel;
    private Button btnSMS;
    private final static int SECOND_CALL_ID= 1234;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appel_activity);


        numAppel = (EditText) findViewById(R.id.numAppel);
        btnCall = (Button) findViewById(R.id.btnCall);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("debug", "tel:#31#"+numAppel.getText().toString());
                Uri uri = Uri.parse("tel:"+numAppel.getText().toString());
                Intent intent = new Intent(Intent.ACTION_CALL, uri);
                if (ActivityCompat.checkSelfPermission(com.example.projet.AppelActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    String [] permissions = {
                            Manifest.permission.CALL_PHONE
                    };
                    requestPermissions(permissions,1000);
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent );


            }
        });



        btnSMS = (Button) findViewById(R.id.btnSMS);
        btnSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        com.example.projet.AppelActivity.this,
                        ActivitySMS.class
                );
                if (ActivityCompat.checkSelfPermission(com.example.projet.AppelActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    String [] permissions = {
                            Manifest.permission.SEND_SMS
                    };
                    requestPermissions(permissions,1000);
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                intent.putExtra("message", numAppel.getText().toString());
                startActivityForResult(intent, SECOND_CALL_ID);



            }
        });
    }
}

