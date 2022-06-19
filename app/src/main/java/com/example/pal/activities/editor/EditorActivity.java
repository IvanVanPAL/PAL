package com.example.pal.activities.editor;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.pal.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.pal.fragments.editor.EditorToolBar;


public class EditorActivity extends AppCompatActivity implements View.OnTouchListener {

    FrameLayout frameLayout;
    CanvasView canvasView;
    Paint myPaint;
    FloatingActionButton toolsCall;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor_activity);
        myPaint = new Paint();

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
        Canvas canvas = canvasView.getCanvas();


        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.heart);

        int eX = (int) motionEvent.getX();
        int eY = (int) motionEvent.getY();
        int halfW = bitmap.getWidth()/2;
        int halfH = bitmap.getHeight()/2;

        Rect rect = new Rect(eX-halfW, eY-halfH,
                eX+halfW, eY+halfH);
        canvas.drawBitmap(bitmap, null, rect, null);

        canvasView.invalidate();



        return true;
    }
}
