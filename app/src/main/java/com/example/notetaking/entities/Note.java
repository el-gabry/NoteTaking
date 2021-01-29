 package com.example.notetaking.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

 @Entity(tableName = "notes")
public class Note implements Serializable
{
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name="title")
    private  String title;
    @ColumnInfo(name="note_text")
    private String noteText;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }
}
