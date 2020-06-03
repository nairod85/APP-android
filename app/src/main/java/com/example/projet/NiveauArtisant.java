package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class NiveauArtisant extends AppCompatActivity implements SensorEventListener {

    private SensorManager mgr = null;
    private GameView view;

    private SensorManager sensorManager;
    private Sensor accelerometerSensor;

    private TextView xText_Degree;
    private TextView yText_Degree;

    float xAzimuth, yAzimuth;
    float xDegree, yDegree;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xText_Degree = findViewById( R.id.xText_Degree );
        yText_Degree = findViewById( R.id.yText_Degree );

        view = findViewById( R.id.theView );
        mgr = (SensorManager) getSystemService( SENSOR_SERVICE );
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        assert sensorManager != null;
        if (sensorManager.getDefaultSensor( Sensor.TYPE_ACCELEROMETER) != null ) {
            accelerometerSensor = sensorManager.getDefaultSensor( Sensor.TYPE_ACCELEROMETER );
        } else {
            xText_Degree.setText( "Pas d'accelerometre");
            yText_Degree.setText( "Pas d'accelerometre");
        }

//        List<Sensor> sensors = mgr.getSensorList( Sensor.TYPE_ALL );
//        for (Sensor sensor : sensors) {
//            Log.i( "DEBUG", sensor.getName() + " --- " + sensor.getVendor() );
//        }

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        xAzimuth = sensorEvent.values[0];
        yAzimuth = sensorEvent.values[1];

        xDegree = (float) Math.toDegrees(xAzimuth);
        yDegree = (float) Math.toDegrees(yAzimuth);

        xText_Degree.setText("Axe X :" + (int) xDegree + "°");
        yText_Degree.setText("Axe Y :" + (int) yDegree + "°");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mgr.registerListener( view, mgr.getDefaultSensor( Sensor.TYPE_ACCELEROMETER ), SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener( this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL );
    }

    @Override
    protected void onPause() {
        super.onPause();
        mgr.unregisterListener( view );
        sensorManager.unregisterListener( this );
    }
}


