package com.example.in_class_demo.InClass07;

import java.util.ArrayList;

public class Notes {

    private ArrayList<Note> notes;


    public Notes() {
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "notes=" + notes +
                '}';
    }

}
