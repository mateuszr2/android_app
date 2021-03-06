package com.example.game.ball;

import android.graphics.Point;



public class MyPoint extends Point {

    public MyPoint() {
    }

    public MyPoint(int x, int y) {
        super(x, y);
    }

    public MyPoint(Point src) {
        super(src);
    }

    public float distance(Point p){
        return (float) Math.sqrt(((x-p.x) * (x-p.x)) + ((y-p.y) * (y-p.y)));
    }

    public float distance(int x, int y){
        return (float) Math.sqrt(((x-this.x) * (x-this.x)) + ((y-this.y) * (y-this.y)));
    }
}
