package com.example.game.ball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;



public class DrawView extends View {
    Paint linePaint = new Paint();
    Paint generalPaint = new Paint();
    Paint targetPaint = new Paint();
    private int stopX;
    private int stopY;
    private int centerX;
    private int centerY;
    private int targetX;
    private int targetY;
    private int targetRadius;

    public void setStopX(int stopX) {
        this.stopX = stopX;
    }

    public void setStopY(int stopY) {
        this.stopY = stopY;
    }

    public void setTargetX(int targetX) {
        this.targetX = targetX;
    }

    public void setTargetY(int targetY) {
        this.targetY = targetY;
    }

    public void setTargetRadius(int targetRadius) {
        this.targetRadius = targetRadius;
    }

    public DrawView(Context context) {
        super(context);
    }

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        centerX = getWidth() / 2; //zakotwienie sznurka oś x
        centerY = getHeight() / 2; //zakotwienie sznurka oś y
        setPaintStyles();
        canvas.drawCircle(centerX + targetX, centerY + targetY, targetRadius, targetPaint); //rysowanie punktu do trafi


        canvas.drawLine(centerX, centerY, centerX + stopX, centerY + stopY, linePaint); //rysowanie linii (sznurka)

    }


    private void setPaintStyles(){

        linePaint.setStrokeWidth(10); //szerokość linii (sznurka)
        targetPaint.setColor(Color.RED); //kolor punktu
    }


}
