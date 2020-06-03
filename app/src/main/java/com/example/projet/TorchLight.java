package com.example.projet;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AutomaticZenRule;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class TorchLight extends AppCompatActivity implements SensorEventListener {

    private ImageView OnOff;
    private TextView TauxLux;

    private SensorManager sensorManager;
    private Sensor accelerometerSensor;

    private boolean AccelerometreDispo;
    private boolean PasPremiereFois = true;

    private float dernier_X, dernier_Y, dernier_Z;

    private Vibrator vibreur;

    private static final int CAMERA_REQUEST = 50;
    private boolean flashLightStatus = false;

    private static float LightValue = 0;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OnOff = findViewById(R.id.ImageOnOff);
        TauxLux = findViewById(R.id.TauxLux);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        vibreur = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            AccelerometreDispo = true;
        } else {
            AccelerometreDispo = false;
        }

        Sensor mLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (mLight != null) {
            sensorManager.registerListener(TorchLight.this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
            LightValue = sensorEvent.values[0];
            TauxLux.setText("Taux de Lumière : " + sensorEvent.values[0] + " Lux");
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            float instantT_X = sensorEvent.values[0];
            float instantT_Y = sensorEvent.values[1];
            float instantT_Z = sensorEvent.values[2];

            if (PasPremiereFois) {
                float difference_X = Math.abs(dernier_X - instantT_X);
                float difference_Y = Math.abs(dernier_Y - instantT_Y);
                float difference_Z = Math.abs(dernier_Z - instantT_Z);

                float shake = 10f;
                if ((difference_X > shake && difference_Y > shake) || (difference_X > shake && difference_Z > shake) || (difference_Y > shake && difference_Z > shake)) {
                    if (LightValue < 50) {
                        if (!flashLightStatus) {
                            flashLightOn();
                            vibreur.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                        } else {
                            flashLightOn();
                        }
                    }
                    if (LightValue > 50) {
                        if (flashLightStatus) {
                            flashLightOff();
                            vibreur.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                        } else {
                            flashLightOff();
                        }
                    }
                }
            }
            dernier_X = instantT_X;
            dernier_Y = instantT_Y;
            dernier_Z = instantT_Z;
            PasPremiereFois = true;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (AccelerometreDispo) {
            sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void flashLightOn() {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            assert cameraManager != null;
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, true);
            flashLightStatus = true;
            OnOff.setImageResource(R.drawable.on_icon);
        } catch (CameraAccessException ignored) {
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void flashLightOff() {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            assert cameraManager != null;
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, false);
            flashLightStatus = false;
            OnOff.setImageResource(R.drawable.off_icon);
        } catch (CameraAccessException ignored) {
        }
    }

    @SuppressLint("Assert")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                AutomaticZenRule imageFlashlight = null;
                assert false;
                imageFlashlight.setEnabled(true);
            } else {
                Toast.makeText(TorchLight.this, "Permission Denied for the Camera", Toast.LENGTH_SHORT).show();
            }
        }
    }
}