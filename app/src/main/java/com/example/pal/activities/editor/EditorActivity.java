package com.example.pal.activities.editor;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.pal.R;
import com.example.pal.fragments.editor.EditorToolBar;
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener;


public class EditorActivity extends AppCompatActivity implements View.OnTouchListener, ColorPickerDialogListener {

    public final int MODE_DRAW_FREE = 21;
    public final int MODE_DRAW_HEART = 22;
    public final int YES_CHANGES = 31;
    public final int NO_CHANGES = 32;

    FrameLayout frameLayout;
    CanvasView canvasView;
    Paint myPaint;
    Canvas canvas;

    String nameImage;
    String typeImage;
    String pathImage;
    int quality = 100;

    int mode;
    int saveChange;
    int colorPaint;

    int sX;
    int sY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.editor_activity);
        saveChange = NO_CHANGES;
        mode = MODE_DRAW_FREE;
        myPaint = new Paint();
        colorPaint = Color.BLACK;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Bundle arguments = getIntent().getExtras();
        if(arguments != null){
            nameImage = arguments.get("name").toString();
            typeImage = arguments.get("type").toString();
            pathImage = arguments.get("path").toString();

        }

        frameLayout = findViewById(R.id.editor_canvas);

        canvasView = new CanvasView( this);
        canvasView.setOnTouchListener(this);


        frameLayout.addView(canvasView);


    }

    public void onToolsClick(View view){
        final EditorToolBar bottomDialog = new EditorToolBar();
        bottomDialog.show(getSupportFragmentManager(), "ModalBottomSheet");
    }



    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        saveChange = YES_CHANGES;
        canvas = canvasView.getCanvas();
        myPaint.setColor(colorPaint);
        myPaint.setStrokeWidth(100);
        final int x = (int) motionEvent.getRawX();
        final int y = (int) motionEvent.getRawY();

        switch (mode){

            case MODE_DRAW_HEART:
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.heart);

                int eX = (int) motionEvent.getX();
                int eY = (int) motionEvent.getY();
                int halfW = bitmap.getWidth()/2;
                int halfH = bitmap.getHeight()/2;

                Rect rect = new Rect(eX-halfW, eY-halfH,
                        eX+halfW, eY+halfH);
                canvas.drawBitmap(bitmap, null, rect, null);

                break;

            case MODE_DRAW_FREE:
                myPaint.setColor(colorPaint);
                myPaint.setStrokeWidth(10);

                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        sX = (int) motionEvent.getX();
                        sY = (int) motionEvent.getY();
                        canvas.drawCircle(sX, sY, 5, myPaint);
                        break;

                    case MotionEvent.ACTION_MOVE:

                        canvas.drawLine(sX,
                                sY,
                                (int) motionEvent.getX(),
                                (int) motionEvent.getY(),
                                myPaint);

                        sX = (int) motionEvent.getX();
                        sY = (int) motionEvent.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        sX = (int) motionEvent.getX();
                        sY = (int) motionEvent.getY();
                        canvas.drawCircle(sX, sY, 5, myPaint);
                }



        }
        canvasView.invalidate();
        frameLayout.invalidate();

        return true;
    }

    public Bitmap getCanvas(){
        return canvasView.getBitmap();
    }

    public void setMode(int m){
        mode = m;
    }

    public void setSaveChange(int s){
        saveChange = s;
    }

    @Override
    public void onColorSelected(int dialogId, int color) {
        colorPaint = color;
    }

    @Override
    public void onDialogDismissed(int dialogId) {
    }

    @Override
    public void onBackPressed() {

        switch (saveChange){
            case YES_CHANGES:
                new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Выход.")
                        .setMessage("Имеются изменения. Всё равно хотите выйти?")
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }

                        })
                        .setNegativeButton("Нет", null)
                        .show();
                break;

            case NO_CHANGES:
                finish();
        }


    }
}
