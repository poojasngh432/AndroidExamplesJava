package com.assignment.healofytask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{
    private LinearLayout ll;
    private Dialog dialogBox = null;
    float dX;
    float dY;
    int lastAction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ll = findViewById(R.id.root);

        ListView listView = (ListView)findViewById(R.id.postsListView);

        ArrayList<String> contactsList = new ArrayList<String>();

        for (int index = 0; index < 10; index++) {
            contactsList.add("This is post " + index + ", dated:  " + Calendar.getInstance().getTime().toString());
        }

        listView.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                R.layout.posts_list_item, android.R.id.text1,contactsList));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showDialog();
                findViewById(R.id.root).post(new Runnable() {
                    public void run() {
                        //findViewById(R.id.root).getForeground().setAlpha(220);
                    }
                });
            }
        });

    }

    private void showDialog() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialogBox = new Dialog(MainActivity.this, android.R.style.Theme_Material_Light_Dialog_Alert);
        } else {
            dialogBox = new Dialog(MainActivity.this);
        }

        int width = (int)(getResources().getDisplayMetrics().widthPixels*1);
        int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);

        dialogBox.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (dialogBox.getWindow() != null) {
            dialogBox.getWindow().setGravity(Gravity.BOTTOM);
        }
        dialogBox.setContentView(R.layout.popup_layout);

        dialogBox.getWindow().setLayout(width, height);


        // find the ListView in the popup layout
        ListView listView = (ListView)dialogBox.findViewById(R.id.commentsListView);

        setSimpleList(listView);

        dialogBox.getWindow().getAttributes().windowAnimations = R.style.dialog_animation;
        dialogBox.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialogBox.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.popup_bg));
        dialogBox.setCancelable(true);
        dialogBox.getWindow().getDecorView().setOnTouchListener(MainActivity.this);
        dialogBox.show();
        dialogBox.setCanceledOnTouchOutside(false);
    }


    void setSimpleList(ListView listView){

        ArrayList<String> contactsList = new ArrayList<String>();

        for (int index = 0; index < 100; index++) {
            contactsList.add("I am @ index " + index + " today " + Calendar.getInstance().getTime().toString());
        }

        listView.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                R.layout.comments_list_item, android.R.id.text1,contactsList));
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        float val = view.getY();
        float val2 = event.getRawY();
        if(val2 > 1400){
            dialogBox.dismiss();
        }

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                dX = view.getX() - event.getRawX();
                dY = view.getY() - event.getRawY();
                lastAction = MotionEvent.ACTION_DOWN;
                break;

            case MotionEvent.ACTION_MOVE:
                view.setY(event.getRawY() + dY);
                //view.setX(event.getRawX() + dX);
                lastAction = MotionEvent.ACTION_MOVE;

                break;

            case MotionEvent.ACTION_UP:
                    dY = view.getY() - event.getRawY();
                    lastAction = MotionEvent.ACTION_UP;
                break;

            default:
                return false;
        }
        return true;
    }
}
