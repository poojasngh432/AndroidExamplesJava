package com.example.tutorialsproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tutorialsproject.R;
import com.example.tutorialsproject.database.dao.PersonDao;
import com.example.tutorialsproject.database.model.Person;
import com.example.tutorialsproject.database.model.PersonRepository;

import java.util.LinkedList;
import java.util.List;

public class RoomDBActivity extends AppCompatActivity {

    EditText etName, etAddress, etPhone;
    TextView valuesTV;
    Button addBtn, deleteAllBtn;
    Person person;
    private PersonRepository personRepository;
    private List<Person> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_db);

        etName = findViewById(R.id.et_name);
        etAddress = findViewById(R.id.et_address);
        etPhone = findViewById(R.id.et_phone);
        valuesTV = findViewById(R.id.tv_names);
        addBtn = findViewById(R.id.add_btn);
        deleteAllBtn = findViewById(R.id.delete_btn);
        list = new LinkedList<>();

        personRepository = new PersonRepository();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                person = new Person(etName.getText().toString(),etAddress.getText().toString(),etPhone.getText().toString());
                new InsertNoteAsyncTask(personRepository).execute(person);
            }
        });

        deleteAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DeleteAsyncTask(personRepository).execute(person);
            }
        });
    }

    private class InsertNoteAsyncTask extends AsyncTask<Person, Void, Void> {
        private PersonRepository personRepository;

        private InsertNoteAsyncTask(PersonRepository personRepository){
            this.personRepository = personRepository;
        }

        @Override
        protected Void doInBackground(Person... person){
            personRepository.insert(person[0]);
            list = personRepository.getAllData();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            String data = "Hermione";
            for(Person p: list){
                data = data + ", " + p.getName();
            }
            valuesTV.setText(data);
        }
    }

    private class DeleteAsyncTask extends AsyncTask<Person, Void, Void> {
        private PersonRepository personRepository;

        private DeleteAsyncTask(PersonRepository personRepository){
            this.personRepository = personRepository;
        }

        @Override
        protected Void doInBackground(Person... person){
            personRepository.deleteAll();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            valuesTV.setText("");
        }
    }
}
