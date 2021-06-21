package com.example.tutorialsproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tutorialsproject.R;
import com.example.tutorialsproject.database.StaticClass;
import com.example.tutorialsproject.database.model.Person;
import com.example.tutorialsproject.database.model.Product;
import com.example.tutorialsproject.mvp.MVPMainActivity;
import com.example.tutorialsproject.mvp1mg.DiagnosticsHomeActivity;
import com.example.tutorialsproject.util.Products;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12, btn13, btn14, btn15, btn16, btn17, btn18, btn19, btn20, btn21, btn22, btn23, btn24, btn25, btn26, btn27, btn28, btn29, btn30, btn31, btn32, btn33, btn34, btn35, btn36, btn37, btn38, btn39, btn40, btn41, btn42;

    private Product[] product;
    private Product productObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn10 = findViewById(R.id.btn10);
        btn11 = findViewById(R.id.btn11);
        btn12 = findViewById(R.id.btn12);
        btn13 = findViewById(R.id.btn13);
        btn14 = findViewById(R.id.btn14);
        btn15 = findViewById(R.id.btn15);
        btn16 = findViewById(R.id.btn16);
        btn17 = findViewById(R.id.btn17);
        btn18 = findViewById(R.id.btn18);
        btn19 = findViewById(R.id.btn19);
        btn20 = findViewById(R.id.btn20);
        btn21 = findViewById(R.id.btn21);
        btn22 = findViewById(R.id.btn22);
        btn23 = findViewById(R.id.btn23);
        btn24 = findViewById(R.id.btn24);
        btn25 = findViewById(R.id.btn25);
        btn26 = findViewById(R.id.btn26);
        btn27 = findViewById(R.id.btn27);
        btn28 = findViewById(R.id.btn28);
        btn29 = findViewById(R.id.btn29);
        btn30 = findViewById(R.id.btn30);
        btn31 = findViewById(R.id.btn31);
        btn32 = findViewById(R.id.btn32);
        btn33 = findViewById(R.id.btn33);
        btn34 = findViewById(R.id.btn34);
        btn35 = findViewById(R.id.btn35);
        btn36 = findViewById(R.id.btn36);
        btn37 = findViewById(R.id.btn37);
        btn38 = findViewById(R.id.btn38);
        btn39 = findViewById(R.id.btn39);
        btn40 = findViewById(R.id.btn40);
        btn41 = findViewById(R.id.btn41);
        btn42 = findViewById(R.id.btn42);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn10.setOnClickListener(this);
        btn11.setOnClickListener(this);
        btn12.setOnClickListener(this);
        btn13.setOnClickListener(this);
        btn14.setOnClickListener(this);
        btn15.setOnClickListener(this);
        btn16.setOnClickListener(this);
        btn17.setOnClickListener(this);
        btn18.setOnClickListener(this);
        btn19.setOnClickListener(this);
        btn20.setOnClickListener(this);
        btn21.setOnClickListener(this);
        btn22.setOnClickListener(this);
        btn23.setOnClickListener(this);
        btn24.setOnClickListener(this);
        btn25.setOnClickListener(this);
        btn26.setOnClickListener(this);
        btn27.setOnClickListener(this);
        btn28.setOnClickListener(this);
        btn29.setOnClickListener(this);
        btn30.setOnClickListener(this);
        btn31.setOnClickListener(this);
        btn32.setOnClickListener(this);
        btn33.setOnClickListener(this);
        btn34.setOnClickListener(this);
        btn35.setOnClickListener(this);
        btn36.setOnClickListener(this);
        btn37.setOnClickListener(this);
        btn38.setOnClickListener(this);
        btn39.setOnClickListener(this);
        btn40.setOnClickListener(this);
        btn41.setOnClickListener(this);
        btn42.setOnClickListener(this);

        getData();

        //this is second commit
        this is third commit
    }

    private void getData() {
        Products p = new Products();
        product = p.PRODUCTS;
        productObj = product[0];
    }

    @Override
    public void onClick(View view) {
        Intent intent;

        switch (view.getId()) {
            case R.id.btn1:
                intent = new Intent(MainActivity.this, DataBindingActivity.class);
                startActivity(intent);
                break;
            case R.id.btn2:
                intent = new Intent(MainActivity.this, DIActivity.class);
                startActivity(intent);
                break;
            case R.id.btn3:
                intent = new Intent(MainActivity.this, IntentsActivity.class);
                startActivity(intent);
                break;
            case R.id.btn4:
                intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.btn5:
                intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
                break;
            case R.id.btn6:
                intent = new Intent(MainActivity.this, SingletonActivity.class);
                startActivity(intent);
                break;
            case R.id.btn7:
                intent = new Intent(MainActivity.this, ContentProviderActivity.class);
                startActivity(intent);
                break;
            case R.id.btn8:
                intent = new Intent(MainActivity.this, LifecycleActivity.class);
                startActivity(intent);
                break;
            case R.id.btn9:
                intent = new Intent(MainActivity.this, PermissionsActivity.class);
                startActivity(intent);
                break;
            case R.id.btn10:
                intent = new Intent(MainActivity.this, ServiceActivity.class);
                startActivity(intent);
                break;
            case R.id.btn11:
                intent = new Intent(MainActivity.this, NetworkingActivity.class);
                startActivity(intent);
                break;
            case R.id.btn12:
                intent = new Intent(MainActivity.this, ListViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn13:
                intent = new Intent(MainActivity.this, AsyncTaskActivity.class);
                startActivity(intent);
                break;
            case R.id.btn14:
                intent = new Intent(MainActivity.this, PlayGifActivity.class);
                startActivity(intent);
                break;
            case R.id.btn15:
                intent = new Intent(MainActivity.this, LiveDataActivity.class);
                startActivity(intent);
                break;
            case R.id.btn16:
                intent = new Intent(MainActivity.this, OrientationActivity.class);
                startActivity(intent);
                break;
            case R.id.btn17:
                intent = new Intent(MainActivity.this, SendingDataActivity.class);
                //Sending an Object bundle
                Bundle bundle = new Bundle();
                bundle.putSerializable("key_serializable", productObj);
                intent.putExtras(bundle);

                //Sending an ArrayList
                ArrayList<String> objectArr = new ArrayList<String>();
                objectArr.add("Thor");
                objectArr.add("Doctor Strange");
                objectArr.add("Loki");
                objectArr.add("Iron Man");

                bundle.putSerializable("ARRAYLIST", (Serializable) objectArr);
                intent.putExtra("BUNDLE", bundle);

                startActivity(intent);
                break;
            case R.id.btn18:
                intent = new Intent(MainActivity.this, DaggerActivity.class);
                startActivity(intent);
                break;
            case R.id.btn19:
                intent = new Intent(MainActivity.this, ListViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn20:
                intent = new Intent(MainActivity.this, OneWayDataBindingActivity.class);
                startActivity(intent);
                break;
            case R.id.btn21:
                intent = new Intent(MainActivity.this, RoomDBActivity.class);
                startActivity(intent);
                break;
            case R.id.btn22:
                intent = new Intent(MainActivity.this, RotationAsyncActivity.class);
                startActivity(intent);
                break;
            case R.id.btn23:
                intent = new Intent(MainActivity.this, RXJavaActivity.class);
                startActivity(intent);
                break;
            case R.id.btn24:
                intent = new Intent(MainActivity.this, ServiceActivity.class);
                startActivity(intent);
                break;
            case R.id.btn25:
                intent = new Intent(MainActivity.this, TwoWayDataBindingActivity.class);
                startActivity(intent);
                break;
            case R.id.btn26:
                intent = new Intent(MainActivity.this, ViewModelActivity.class);
                startActivity(intent);
                break;
            case R.id.btn27:
                intent = new Intent(MainActivity.this, ButterKnifeActivity.class);
                startActivity(intent);
                break;
            case R.id.btn28:
                intent = new Intent(MainActivity.this, GraphqlActivity.class);
                startActivity(intent);
                break;
            case R.id.btn29:
                intent = new Intent(MainActivity.this, RecyclerViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn30:
                intent = new Intent(MainActivity.this, FragmentActivity.class);
                startActivity(intent);
                break;
            case R.id.btn31:
                intent = new Intent(MainActivity.this, HandlerActivity.class);
                startActivity(intent);
                break;
            case R.id.btn32:
                intent = new Intent(MainActivity.this, LooperActivity.class);
                startActivity(intent);
                break;
            case R.id.btn33:
                intent = new Intent(MainActivity.this, MultipleAsyncTaskActivity.class);
                startActivity(intent);
                break;
            case R.id.btn34:
                intent = new Intent(MainActivity.this, ObservableActivity.class);
                startActivity(intent);
                break;
            case R.id.btn35:
                intent = new Intent(MainActivity.this, NetworkCallsActivity.class);
                startActivity(intent);
                break;
            case R.id.btn36:
                intent = new Intent(MainActivity.this, NetworkCallKotlinActivity.class);
                startActivity(intent);
                break;
            case R.id.btn37:
                intent = new Intent(MainActivity.this, MVPMainActivity.class);
                startActivity(intent);
                break;
            case R.id.btn38:
                intent = new Intent(MainActivity.this, DiagnosticsHomeActivity.class);
                startActivity(intent);
                break;
            case R.id.btn39:
                intent = new Intent(MainActivity.this, NetworkCallKotlinActivity.class);
                startActivity(intent);
                break;
            case R.id.btn40:
                intent = new Intent(MainActivity.this, NetworkCallKotlinActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
