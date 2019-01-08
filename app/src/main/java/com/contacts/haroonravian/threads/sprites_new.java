package com.contacts.haroonravian.threads;

import android.content.Context;
import android.widget.Toast;

public class sprites_new extends Thread {
    int x;
    int y;
    int w;
    int h;
    int yy;
    int width;
    int height;
    int left;
    int right;
    int randx;
    boolean m;
    Context context;

    sprites_new(Context context) {
        this.context = context;
    }

    sprites_new() {

    }

    sprites_new(int x, int y, int w, int h, int yy, int width, int height, int left,
                int right, boolean m, int ranx) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.yy = yy;
        this.height = height;
        this.width = width;
        this.left = left;
        this.right = right;
        this.m = m;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {

            if (this.y < this.height) {
                this.y = this.y + this.yy;
                this.h = this.y + 300;
            }
            if (this.y >= this.height) {
                this.y = -290;
                this.h = this.y + 300;
                if (this.m == true) {
                    this.randx = ((int) (Math.random() * width));
                }
                this.yy = (1 + (int) (Math.random() * 4));
            }

            if (randx < left) {
                this.x = left / 3;
                this.w = (left / 3) + (left / 3);
            } else if (randx > left && randx < right) {
                this.x = (left) + (left / 3);
                this.w = (left) + (left / 3) + (left / 3);
            } else if (randx > right) {
                this.x = (right) + (left / 3);
                this.w = (right) + (left / 3) + (left / 3);
            } else {


            }

            try {

                this.x = left / 3;
                this.w = (left / 3) + (left / 3);


                Thread.sleep(4);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
