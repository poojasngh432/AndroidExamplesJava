package com.example.tutorialsproject.database.model;

import androidx.room.Query;

import com.example.tutorialsproject.BaseClass;
import com.example.tutorialsproject.database.MainDatabase;
import com.example.tutorialsproject.database.dao.PersonDao;

import java.util.List;

public class PersonRepository {
    private PersonDao personDao;

    public PersonRepository() {
        MainDatabase mainDatabase = BaseClass.getInstance().getMainDatabase();
        personDao = mainDatabase.personDao();
    }

    public void insert(Person person){
        personDao.insert(person);
    }

    public void delete(Person person){
        personDao.delete(person);
    }

    public void deleteAll(){
        personDao.deleteAllPeople();
    }

    public List<Person> getAllData(){
        return personDao.getAllPeople();
    }

}
