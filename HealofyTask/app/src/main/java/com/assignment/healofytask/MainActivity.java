package com.assignment.healofytask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private LinearLayout ll;
    float dX;
    float dY;
    int lastAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ll = findViewById(R.id.root);

        ListView listView = (ListView) findViewById(R.id.postsListView);

        ArrayList<String> contactsList = new ArrayList<String>();

        for (int index = 0; index < 10; index++) {
            contactsList.add("This is post " + index + ", dated:  " + Calendar.getInstance().getTime().toString());
        }

        listView.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                R.layout.posts_list_item, android.R.id.text1, contactsList));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showPopupWindow();
            }
        });

    }

    private void showPopupWindow() {
        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        final View mView;
        LinearLayout headerLL;
        final EditText commentBox;
        final PopupWindow mPopupWindow;
        final int[] mPosX = {0};
        final int[] mPosY = {0};

        mView = layoutInflater.inflate(R.layout.popup_layout, null);

        int width = (int) (getResources().getDisplayMetrics().widthPixels * 1);
        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);

        mView.setBackgroundDrawable(getResources().getDrawable(R.drawable.popup_bg));

        mPopupWindow = new PopupWindow(mView, width, height, true);
        mPopupWindow.showAtLocation(ll, Gravity.BOTTOM, mPosX[0], mPosY[0]);
        mPopupWindow.setAnimationStyle(R.style.dialog_animation);

        ListView listView = (ListView) mView.findViewById(R.id.commentsListView);
        headerLL = mView.findViewById(R.id.headerLayout);
        commentBox = mView.findViewById(R.id.writeComment);

        setSimpleList(listView);
        mPopupWindow.setOutsideTouchable(false);

        headerLL.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        dX = view.getX() - event.getRawX();
                        dY = view.getY() - event.getRawY();
                        lastAction = MotionEvent.ACTION_DOWN;

                        Log.d("HEALOFY", "ACTION_DOWN dy : " + dY);
                        Log.d("HEALOFY", "ACTION_DOWN view.getY() : " + view.getY());
                        Log.d("HEALOFY", "ACTION_DOWN event.getRawY() : " + event.getRawY());

                        break;
                    case MotionEvent.ACTION_MOVE:

                        mView.setY(event.getRawY() + dY);
                        Log.d("HEALOFY", "ACTION_MOVE event.getRawY() : " + event.getRawY());
                       // Log.d("HEALOFY", "ACTION_MOVE commentBox.getY : " + commentBox.getY());
                       // Log.d("HEALOFY", "ACTION_MOVE commentBox.getScaleY : " + commentBox.getScaleY());
                        //Log.d("HEALOFY", "ACTION_MOVE dY : " + dY);

                        if (event.getRawY() >= 2000)
                            mPopupWindow.dismiss();
                        else
                            commentBox.setY(commentBox.getY());

                        lastAction = MotionEvent.ACTION_MOVE;

                        break;
                    case MotionEvent.ACTION_UP:
                        dY = view.getY() - event.getRawY();
                        lastAction = MotionEvent.ACTION_UP;

                        if(event.getRawY() < 900)
                            mView.setY(0);

                        Log.d("HEALOFY", "ACTION_UP view.getY() : " + view.getY());
                        Log.d("HEALOFY", "ACTION_UP event.getRawY() : " + event.getRawY());
                        Log.d("HEALOFY", "ACTION_UP dy : " + dY);
                        break;

                    default:
                        return false;
                }
                return true;
            }
        });

//        commentBox.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//
//                        dX = view.getX() - event.getRawX();
//                        dY = view.getY() - event.getRawY();
//                        lastAction = MotionEvent.ACTION_DOWN;
//
//                        Log.d("HEALOFY", "ACTION_DOWN dy : " + dY);
//                        Log.d("HEALOFY", "ACTION_DOWN view.getY() : " + view.getY());
//                        Log.d("HEALOFY", "ACTION_DOWN event.getRawY() : " + event.getRawY());
//
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//
//                        mView.setY(event.getRawY() + dY);
//                        Log.d("HEALOFY", "ACTION_MOVE event.getRawY() : " + event.getRawY());
//                        // Log.d("HEALOFY", "ACTION_MOVE commentBox.getY : " + commentBox.getY());
//                        // Log.d("HEALOFY", "ACTION_MOVE commentBox.getScaleY : " + commentBox.getScaleY());
//                        //Log.d("HEALOFY", "ACTION_MOVE dY : " + dY);
//
//                        if (event.getRawY() >= 2000)
//                            mPopupWindow.dismiss();
//                        else
//                            commentBox.setY(commentBox.getY());
//
//                        lastAction = MotionEvent.ACTION_MOVE;
//
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        dY = view.getY() - event.getRawY();
//                        lastAction = MotionEvent.ACTION_UP;
//
//                        if(event.getRawY() < 900)
//                            mView.setY(0);
//
//                        Log.d("HEALOFY", "ACTION_UP view.getY() : " + view.getY());
//                        Log.d("HEALOFY", "ACTION_UP event.getRawY() : " + event.getRawY());
//                        Log.d("HEALOFY", "ACTION_UP dy : " + dY);
//                        break;
//
//                    default:
//                        return false;
//                }
//                return true;
//            }
//        });

    }

    void setSimpleList(ListView listView) {

        ArrayList<String> contactsList = new ArrayList<String>();

        for (int index = 0; index < 20; index++) {
            contactsList.add("I am @ index " + index + " today " + Calendar.getInstance().getTime().toString());
        }

        listView.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                R.layout.comments_list_item, android.R.id.text1, contactsList));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("HEALOFY", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("HEALOFY", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("HEALOFY", "onDestroy");
    }


}
