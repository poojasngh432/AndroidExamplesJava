package com.example.tutorialsproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tutorialsproject.R;
import com.example.tutorialsproject.adapter.CustomListAdapter;
import com.example.tutorialsproject.database.model.ListItem;
import com.example.tutorialsproject.util.UiUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListViewActivity extends AppCompatActivity {

    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;

    String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry", "WebOS","Ubuntu","Windows7","Max OS X"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        ButterKnife.bind(this);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayAdapter();
            }
        });

        cursorAdapter();

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customAdapter();
            }
        });

    }

    private void arrayAdapter() {
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.single_list_item, mobileArray);

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                UiUtil.showToast(ListViewActivity.this, "item : " + i);
            }
        });
    }

    private void cursorAdapter() {
//        String[] fromColumns = {ContactsContract.Data.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
//        int[] toViews = {R.id.display_name, R.id.phone_number};
//        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.person_name_and_number, cursor, fromColumns, toViews, 0);
//
//        ListView listView = (ListView) findViewById(R.id.list_view);
//        listView.setAdapter(adapter);

    }

    private void customAdapter() {
        ArrayList userList = getListData();
        final ListView lv = (ListView) findViewById(R.id.list_view);
        lv.setAdapter(new CustomListAdapter(this, userList));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                ListItem user = (ListItem) lv.getItemAtPosition(position);
                Toast.makeText(ListViewActivity.this, "Selected :" + " " + user.getName()+", "+ user.getLocation(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private ArrayList getListData() {
        ArrayList<ListItem> results = new ArrayList<>();
        ListItem user1 = new ListItem();
        user1.setName("Suresh Dasari");
        user1.setDesignation("Team Leader");
        user1.setLocation("Hyderabad");
        results.add(user1);
        ListItem user2 = new ListItem();
        user2.setName("Rohini Alavala");
        user2.setDesignation("Agricultural Officer");
        user2.setLocation("Guntur");
        results.add(user2);
        ListItem user3 = new ListItem();
        user3.setName("Trishika Dasari");
        user3.setDesignation("Charted Accountant");
        user3.setLocation("Guntur");
        results.add(user3);

        results.add(user1);
        results.add(user2);
        results.add(user3);
        results.add(user1);
        results.add(user2);
        results.add(user3);
        results.add(user1);
        results.add(user2);
        results.add(user3);
        results.add(user1);
        results.add(user2);
        results.add(user3);

        return results;
    }

}
