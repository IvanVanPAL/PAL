package com.example.pal.activities.editor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.io.File;

public class CanvasView extends View {
    private Bitmap image;
    private Paint paint;
    private int from;
    private String filePath;


    public CanvasView(Context context) {
        super(context);
        paint = new Paint();
    }

    //очистка холста
    public void clear() {
        Canvas canvas = new Canvas(image);
        canvas.drawColor(Color.WHITE);
    }

    //получение изображения
    public Bitmap getBitmap() {
        return image;
    }

    //получение холста
    public Canvas getCanvas() {
        return new Canvas(image);
    }

    //откуда был открыт редактор
    public void setFrom(int from){
        this.from = from;
    }
    //установка пути открытого файла
    public void setImageFile(String pathFile){
        filePath = pathFile;
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        //если новый проект
        if(from == 0){
            image = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            clear();
            //если открыли проект
        }else {
            File imageFile = new File(filePath);
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            image = BitmapFactory.decodeFile(imageFile.getAbsolutePath(),bmOptions).copy(Bitmap.Config.ARGB_8888, true);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(image, 0, 0, paint);
    }
}
