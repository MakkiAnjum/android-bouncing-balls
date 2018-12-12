package com.contacts.haroonravian.threads;

/**
 * Created by Makki Anjum on 22/11/2018.
 */

public class sprites_new extends Thread {
    int x;
    int y;
    int r;
    int dx;
    int dy;
    int height;
    int width;
    int red, green, blue;

    sprites_new(int x, int y, int r, int dx, int dy, int height, int width, int red, int green, int blue) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.dx = dx;
        this.dy = dy;
        this.height = height;
        this.width = width;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public void run() {
        while (true) {
            this.x = this.x + this.dx;
            this.y = this.y + this.dy;

            if (this.y >= this.height || this.y < 50) {
                this.dy = -1 * this.dy;
            }

            if (this.x >= this.width || this.x < 50) {
                this.dx = -1 * this.dx;
            }

            try {
                Thread.sleep(7);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}