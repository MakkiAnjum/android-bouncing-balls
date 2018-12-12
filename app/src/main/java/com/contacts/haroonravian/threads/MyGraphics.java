package com.contacts.haroonravian.threads;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

/**
 * Created by Makki on 22/11/2018.
 */

public class MyGraphics extends View {

    int height;
    int width;
    int count = 0;

    Paint red = new Paint();
    sprites_new[] obj = new sprites_new[500];

    public MyGraphics(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        height = getHeight() - 50;
        width = getWidth() - 50;

        for (int i = 0; i < count; i++) {
            red.setARGB(170, obj[i].red, obj[i].green, obj[i].blue);
            canvas.drawCircle(obj[i].x, obj[i].y, obj[i].r, red);
        }
        invalidate();
    }

    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_UP) {
            int touchX = Math.round(event.getX());
            int touchY = Math.round(event.getY());

            int dxx = (1 + (int) (Math.random() * 5));
            int dyy = (1 + (int) (Math.random() * 5));

            //int radius = (30+(int)(Math.random()*60));;

            Random rnd = new Random();
            int red = rnd.nextInt(220);
            int green = rnd.nextInt(220);
            int blue = rnd.nextInt(220);


            obj[count] = new sprites_new(touchX, touchY, 50, dxx, dyy, height, width, red, green, blue);

            obj[count].start();

            count++;

            postInvalidate();
        }
        return true;
    }


}

