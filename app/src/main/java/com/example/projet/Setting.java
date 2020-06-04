package com.example.projet;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class Setting extends AppCompatActivity {

    private Button btnAppel;
    private Button btnReadSMS;
    private Button btnCamera;
    private Button btnLocalisation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        btnAppel = (Button) findViewById(R.id.btnAppel);
        btnAppel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(com.example.projet.Setting.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    String [] permissions = {
                            Manifest.permission.CALL_PHONE
                    };
                    requestPermissions(permissions,1000);
                }
            }
        });

        btnReadSMS = (Button) findViewById(R.id.btnReadSms);
        btnReadSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(com.example.projet.Setting.this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
                    String [] permissions = {
                            Manifest.permission.READ_SMS
                    };
                    requestPermissions(permissions,1000);
                }
            }
        });

        btnCamera = (Button) findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(com.example.projet.Setting.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    String [] permissions = {
                            Manifest.permission.CAMERA
                    };
                    requestPermissions(permissions,1000);
                }
            }
        });

        btnLocalisation = (Button) findViewById(R.id.btnLocalisation);
        btnLocalisation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(com.example.projet.Setting.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(com.example.projet.Setting.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    String [] permissions = {
                            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
                    };
                    requestPermissions(permissions,1000);
                }
            }
        });

    }


}
