package com.liveinbits.mvvmactivity.dao;

import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "notes")
public class NoteModel {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private Date date;
    private String text;

    @Ignore
    public NoteModel() {
    }
    @Ignore
    public NoteModel(Date date, String text) {
        this.date = date;
        this.text = text;
    }

    public NoteModel(int id, Date date, String text) {
        this.id = id;
        this.date = date;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
