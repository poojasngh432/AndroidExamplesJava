package com.example.tutorialsproject.database.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "person_table")
public class Person {
    /**
     * The @Entity(tableName = "person_table") annotation is telling Room that this is an Entity object that is mapped to a table called person_table.
     */
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String address;
    private String phoneNo;
    /**
     With a SQL relational database, you would model the Person class as a table. Each instance of that person would be a row in that table. In order to store and retrieve this data, SQL commands need to be be issued to the database, telling it to retrieve and store the data.
     */

    public Person(String name, String address, String phoneNo) {
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
