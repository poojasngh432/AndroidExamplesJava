package com.example.tutorialsproject.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.tutorialsproject.database.dao.NoteDao;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    /**
     * Now that you have an Entity and a DAO, you’re going to need an object to tie things together.
     * This is what the Database object does.
     * You tell Room that the class is a Database object using the @Database annotation.
     * The entities parameter tells your database which entities are associated with that database.
     * There is also a version that is set to 1. The database version will need to be changed when performing a database migration
     * Your database class can be named whatever you want it to be, but it needs to be abstract, and it needs to extend RoomDatabase.
     * All of your DAOs need to have abstract methods that return the corresponding DAO. This is how Room associates a DAO with a database.
     */

    private static NoteDatabase instance;

    public abstract NoteDao noteDao();

    //synchronized means only one thread at a time can access this method
    public static synchronized NoteDatabase getInstance(Context context){
       if(instance == null){
           instance = Room.databaseBuilder(context.getApplicationContext(), NoteDatabase.class, "note_database")
                   .fallbackToDestructiveMigration()
                   .addCallback(roomCallback)          //attaching callback to db
                   .build();
       }
       return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
      private NoteDao noteDao;

      private PopulateDbAsyncTask(NoteDatabase db) {
          noteDao = db.noteDao();
      }

      @Override
      protected Void doInBackground(Void... voids) {
          noteDao.insert(new Note("Title 1","Description 1",1));
          noteDao.insert(new Note("Title 2","Description 2",2));
          noteDao.insert(new Note("Title 3","Description 3",3));
          return null;
      }
    }

}
