package com.contacts.haroonravian.threads;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MyGraphics extends View {
    int height;
    int width;
    int right, left = 0;
    int x, y, w, h = 0;
    int bx = 130;
    int bw = bx + 150;
    int by = 0;
    int bh = by + 300;
    int endCount = 0;

    Paint line = new Paint();
    Paint blue = new Paint();
    Paint blue2 = new Paint();
    Paint red = new Paint();

    int randx, byy = 2;

    sprites_new[] blueCar = new sprites_new[4];
    sprites_new redCar;


    public MyGraphics(Context context) {
        super(context);
        sprites_new c = new sprites_new(context);

        height = 2476;
        width = 1440;
        right = (width / 3) + (width / 3);
        left = width / 3;

        byy = (1 + (int) (Math.random() * 4));
        blueCar[0] = new sprites_new(bx, by, bw, bh, byy, width, height, left, right, true, 10);
        byy = (1 + (int) (Math.random() * 4));
        blueCar[1] = new sprites_new(bx, by, bw, bh, byy, width, height, left, right, false, left - 6);
        byy = (1 + (int) (Math.random() * 4));
        blueCar[2] = new sprites_new(bx, by, bw, bh, byy, width, height, left, right, false, left + 6);
        byy = (1 + (int) (Math.random() * 4));
        blueCar[3] = new sprites_new(bx, by, bw, bh, byy, width, height, left, right, false, right + 6);
        redCar = new sprites_new(x, y, w, h, 0, width, height, left, right, false, left + 6);

        blueCar[0].start();
        blueCar[1].start();
        blueCar[2].start();
        blueCar[3].start();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.setBackgroundColor(Color.parseColor("#e8ede8"));
        line.setColor(Color.parseColor("#bebebf"));
        canvas.drawRect(left - 10, 0, left + 10, height, line);
        canvas.drawRect(right - 10, 0, right + 10, height, line);

        red.setARGB(255, 255, 0, 0);
        blue.setARGB(255, 0, 0, 255);
        blue2.setARGB(255, 100, 0, 220);
        canvas.drawRect(redCar.x, redCar.y, redCar.w, redCar.h, red);
        for (int i = 1; i < 4; i++) {
            canvas.drawRect(blueCar[i].x, blueCar[i].y, blueCar[i].w, blueCar[i].h, blue);
        }
        canvas.drawRect(blueCar[0].x, blueCar[0].y, blueCar[0].w, blueCar[0].h, blue2);
        for (int i = 0; i < 4; i++) {
            if (blueCar[i].h >= redCar.y && blueCar[i].y <= redCar.h && blueCar[i].x == redCar.x && endCount == 0) {

                Toast.makeText(getContext(), "end end end", Toast.LENGTH_SHORT).show();

                blueCar[0].interrupt();
                blueCar[1].interrupt();
                blueCar[2].interrupt();
                blueCar[3].interrupt();
                redCar.interrupt();
                endCount++;
                return;
            }
        }
        invalidate();
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (blueCar[0].isInterrupted() || blueCar[1].isInterrupted() || blueCar[2].isInterrupted() || blueCar[3].isInterrupted() || redCar.isInterrupted()) {
                blueCar[0].start();
                blueCar[1].start();
                blueCar[2].start();
                blueCar[3].start();
                redCar.start();
            }
            endCount = 0;

            right = (width / 3) + (width / 3);
            left = width / 3;

            int touchX = Math.round(event.getX());
            int touchY = Math.round(event.getY());

            if (touchY > height - 300) {
                y = height - 300;
                h = height - 10;
            } else if (touchY < 300) {
                y = 10;
                h = y + 300;
            } else {
                y = touchY - 150;
                h = y + 300;
            }

            if (touchX < left) {
                x = left / 3;
                w = (left / 3) + (left / 3);
            } else if (touchX > left && touchX < right) {
                x = (left) + (left / 3);
                w = (left) + (left / 3) + (left / 3);
            } else if (touchX > right && touchX < width) {
                x = (right) + (left / 3);
                w = (right) + (left / 3) + (left / 3);
            } else {
                x = left / 3;
                w = (left / 3) + (left / 3);
            }
            redCar = new sprites_new(x, y, w, h, 0, width, height, left, right, false, left + 6);

            postInvalidate();
        }
        return true;

    }
}
