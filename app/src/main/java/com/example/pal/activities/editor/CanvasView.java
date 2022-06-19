package com.example.pal.activities.editor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class CanvasView extends View {
    private Bitmap image;
    private Paint paint;

    public CanvasView(Context context) {
        super(context);
        paint = new Paint();
    }

    public void clear() {
        Canvas canvas = new Canvas(image);
        canvas.drawColor(Color.WHITE);
    }

    public Bitmap getBitmap() {
        return image;
    }

    public Canvas getCanvas() {
        return new Canvas(image);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        image = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.RGB_565);
        clear();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(image, 0, 0, paint);
    }
}
