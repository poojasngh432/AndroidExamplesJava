package com.example.fragmentsproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fragmentsproject.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button ex1, ex2, ex3, ex4, ex5, ex6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ex1 = findViewById(R.id.ex_1);
        ex2 = findViewById(R.id.ex_2);
        ex3 = findViewById(R.id.ex_3);
        ex4 = findViewById(R.id.ex_4);
        ex5 = findViewById(R.id.ex_5);
        ex6 = findViewById(R.id.ex_6);

        ex1.setOnClickListener(this);
        ex2.setOnClickListener(this);
        ex3.setOnClickListener(this);
        ex4.setOnClickListener(this);
        ex5.setOnClickListener(this);
        ex6.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                Toast.makeText(this, "This is an about icon menu", Toast.LENGTH_SHORT).show();
             //   showDialogForDetails();
                return true;
           /* case R.id.share:
                // do something
                return true;*/
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.ex_1){
            Intent i1 = new Intent(this, Example1Activity.class);
            startActivity(i1);
        }else if(view.getId() == R.id.ex_2){
            Intent i2 = new Intent(this, Example2Activity.class);
            startActivity(i2);
        }else if(view.getId() == R.id.ex_3){
            Intent i3 = new Intent(this, Example3Activity.class);
            startActivity(i3);
        }else if(view.getId() == R.id.ex_4){

        }else if(view.getId() == R.id.ex_5){

        }else if(view.getId() == R.id.ex_6){

        }
    }
}
