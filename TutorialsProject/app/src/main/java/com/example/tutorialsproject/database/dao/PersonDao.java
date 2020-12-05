package com.example.tutorialsproject.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.tutorialsproject.database.model.Person;

import java.util.List;

@Dao
public interface PersonDao {

    @Insert
    void insert(Person person);

    @Delete
    void delete(Person person);

    @Query("DELETE FROM person_table")
    void deleteAllPeople();

    @Query("SELECT * FROM person_table ORDER BY name DESC")
    List<Person> getAllPeople();

}
