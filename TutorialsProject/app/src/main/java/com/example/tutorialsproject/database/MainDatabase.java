package com.example.tutorialsproject.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.tutorialsproject.database.dao.PersonDao;
import com.example.tutorialsproject.database.model.Person;

@Database(entities = {Person.class}, version = 10)
public abstract class MainDatabase extends RoomDatabase {

    private static MainDatabase mainDatabaseInstance;

    public abstract PersonDao personDao();

    public static synchronized MainDatabase getInstance(Context context){
        if(mainDatabaseInstance == null){
            mainDatabaseInstance = Room.databaseBuilder(context.getApplicationContext(), MainDatabase.class, "main_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)          //attaching callback to db
                    .build();
        }
        return mainDatabaseInstance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(mainDatabaseInstance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private PersonDao personDao;

        private PopulateDbAsyncTask(MainDatabase db) {
            personDao = db.personDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            personDao.insert(new Person("Crowley","Description 1","9879653484"));
            personDao.insert(new Person("Atticus","Description 2","9879679484"));
            personDao.insert(new Person("Arsedelus","Description 3","9878985484"));
            return null;
        }
    }
}
