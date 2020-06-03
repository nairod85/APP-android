package com.example.projet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.AttributeSet;
import android.view.View;

public class GameView extends View implements SensorEventListener {

    private Paint paint = new Paint( Paint.ANTI_ALIAS_FLAG );
    private Bitmap ballBitmap;

    private int imageWidth;
    private int imageHeight;
    private int currentX;
    private int currentY;
    float x;
    float y;


    public GameView(Context context){
        super (context);
    }

    public GameView(Context context, AttributeSet attrSet){
        super (context, attrSet);
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        super.onSizeChanged(width, height, oldw, oldh);
        ballBitmap = BitmapFactory.decodeResource( getResources(), R.drawable.bulle );
        imageWidth = ballBitmap.getWidth();
        imageHeight = ballBitmap.getHeight();

        currentX = (width - imageWidth) / 2;
        currentY = (height - imageHeight) / 2;
    }




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap( ballBitmap, currentX, currentY, paint );
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        x = sensorEvent.values[0];
        y = sensorEvent.values[1];

        this.moveImage( -x*5, y*5 );

    }



    private void moveImage( float x, float y) {
        currentX += (int) x;
        currentY += (int) y;

        if (currentX < 0) {
            currentX = 0;
        } else if ( this. currentX + imageWidth > getWidth() ) {
            currentX = getWidth() - imageWidth;
        }

        if (currentY < 0) {
            currentY = 0;
        } else if ( this. currentY + imageHeight > getHeight() ) {
            currentY = getHeight() - imageHeight;
        }

        this.invalidate();
    }
}
