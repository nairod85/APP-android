package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivitySMS extends AppCompatActivity {

    private EditText message;
    private Button envoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_m_s);



        //Récupération des champs graphiques
        message = (EditText) findViewById(R.id.txtMessage);
        envoi = (Button) findViewById(R.id.btnenvoyer);
        //Clic bouton


        envoi.setOnClickListener(new Button.OnClickListener() {


            public void onClick(View v) {
                String msg = message.getText().toString();
                String num = getIntent().getExtras().getString("message");
                Log.i("debug", num);
                try{
                    if (msg.length() > 0) {
                        SmsManager smsManager =  SmsManager.getDefault();
                        smsManager.sendTextMessage(num, null,
                                msg, null, null);
                        Toast.makeText(ActivitySMS.this, "SMS envoyé", Toast.LENGTH_SHORT).show();
                        //wait(10);
                        finish();


                    } else {
                        Toast.makeText(ActivitySMS.this, "Ecrivez un message", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        });

    }

}
