package com.example.pal.activities.editor;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;

import com.example.pal.R;
import com.example.pal.activities.MainActivity;
import com.example.pal.fragments.editor.EditorToolBar;
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener;

import java.io.IOException;


public class EditorActivity extends AppCompatActivity implements View.OnTouchListener, ColorPickerDialogListener {

    //константы режимов рисования
    private final int MODE_DRAW_FREE = 21;
    private final int MODE_DRAW_HEART = 22;

    //константы проверки изменения холста
    public final int YES_CHANGES = 31;
    public final int NO_CHANGES = 32;

    FrameLayout frameLayout;
    CanvasView canvasView;

    //объявление кисти и холста
    Paint myPaint;
    Canvas canvas;
    int colorPaint;

    //переменные режима рисования и проверки изменений
    int mode;
    int saveChange;
    int fromActivity;
    String pathFile;

    //координаты
    int sX;
    int sY;

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String pref_key_color;
    String pref_key_mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.editor_activity);

        pref = PreferenceManager.getDefaultSharedPreferences(EditorActivity.this);
        pref_key_color = this.getString(R.string.pref_save_color);
        pref_key_mode = this.getString(R.string.pref_save_mode);

        //изначально выбран режим свободного рисования
        //изменений не было
        saveChange = NO_CHANGES;
        mode = pref.getInt(pref_key_mode, 0);

        if (mode == 0) {
            editor = pref.edit();
            editor.putInt(pref_key_mode, MODE_DRAW_FREE);
            editor.putInt(pref_key_color, Color.BLACK);
            editor.commit();
        }

        //настройка кисти
        myPaint = new Paint();
        colorPaint = pref.getInt(pref_key_color, 0);



        Bundle arguments = getIntent().getExtras();

        if(arguments != null){
            //из какого фрагмента был сделан переход
            fromActivity =  arguments.getInt("from");
            pathFile = arguments.get("path").toString() + arguments.get("name").toString() + arguments.get("type").toString();
        }
        //строчка убирает верхнюю панель с статусами телефона (часы, батарея)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        frameLayout = findViewById(R.id.editor_canvas);

        canvasView = new CanvasView( this);

        //настройка холста
        canvasView.setFrom(fromActivity);
        canvasView.setImageFile(pathFile);
        //установка слушателя холста
        canvasView.setOnTouchListener(this);

        //добавление холста в FrameLayout
        frameLayout.addView(canvasView);
    }

    //обработка кнопки меню инстрюментов
    public void onToolsClick(View view){
        final EditorToolBar bottomDialog = new EditorToolBar();
        bottomDialog.show(getSupportFragmentManager(), "ModalBottomSheet");
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        saveChange = YES_CHANGES;

        //получение холста
        canvas = canvasView.getCanvas();
        //установка выбранного цвета
        myPaint.setColor(colorPaint);
        myPaint.setStrokeWidth(100);
        final int x = (int) motionEvent.getRawX();
        final int y = (int) motionEvent.getRawY();

        //режимы рисования
        switch (mode){

            //рисование с помощью ресурса Drawable
            case MODE_DRAW_HEART:
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.heart).copy(Bitmap.Config.ARGB_8888, true);

                int eX = (int) motionEvent.getX();
                int eY = (int) motionEvent.getY();
                int halfW = bitmap.getWidth()/2;
                int halfH = bitmap.getHeight()/2;

                //объявление прямоугольника для ресурса
                Rect rect = new Rect(eX-halfW, eY-halfH,
                        eX+halfW, eY+halfH);


                //рисование ресурса в прямоугольник
                ColorFilter filter = new PorterDuffColorFilter(colorPaint, PorterDuff.Mode.SRC_ATOP);
                myPaint.setColorFilter(filter);
                canvas.drawBitmap(bitmap, null, rect, myPaint);
                break;

                //рисование свободной кистью
            case MODE_DRAW_FREE:
                myPaint.setColor(colorPaint);
                myPaint.setStrokeWidth(10);

                //получение состояния действия
                switch (motionEvent.getAction()){
                    //палец опущен
                    case MotionEvent.ACTION_DOWN:
                        sX = (int) motionEvent.getX();
                        sY = (int) motionEvent.getY();
                        canvas.drawCircle(sX, sY, 5, myPaint);
                        break;
                    //палец передвигается
                    case MotionEvent.ACTION_MOVE:

                        canvas.drawLine(sX,
                                sY,
                                (int) motionEvent.getX(),
                                (int) motionEvent.getY(),
                                myPaint);

                        sX = (int) motionEvent.getX();
                        sY = (int) motionEvent.getY();
                        break;
                        //палец поднят
                    case MotionEvent.ACTION_UP:
                        sX = (int) motionEvent.getX();
                        sY = (int) motionEvent.getY();
                        canvas.drawCircle(sX, sY, 5, myPaint);
                }
        }
        //обновление холста и FrameLayout
        canvasView.invalidate();
        frameLayout.invalidate();

        return true;
    }

    //возвращает холст
    public Bitmap getCanvas(){
        return canvasView.getBitmap();
    }

    //установка режима рисования
    public void setMode(int m){
        mode = m;
    }

    //установка состояния холста
    public void setSaveChange(int s){
        saveChange = s;
    }
    //получение цвета
    public int getColorPaint(){
        return colorPaint;
    }
    //получение режима рисования
    public int getModeStatus(){
        return mode;
    }

    @Override
    public void onColorSelected(int dialogId, int color) {
        colorPaint = color;
    }

    @Override
    public void onDialogDismissed(int dialogId) {
    }

    //если при выходе из редактора в главное меню есть несохраненные изменения
    //всплывает диалоговое окно с предупреждением
    @Override
    public void onBackPressed() {

        switch (saveChange){
            //если есть изменения
            case YES_CHANGES:
                new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Выход.")
                        .setMessage("Имеются изменения. Всё равно хотите выйти?")
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                savePref();
                                finish();
                            }
                        })
                        .setNegativeButton("Нет", null)
                        .show();
                break;
                //если файл сохранен
            case NO_CHANGES:
                savePref();
                finish();
        }
    }

    private void savePref(){

        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(pref_key_color, colorPaint);
        editor.putInt(pref_key_mode, mode);
        editor.commit();
    }
}
