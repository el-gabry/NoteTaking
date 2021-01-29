package com.example.notetaking.listeners;

import com.example.notetaking.entities.Note;

public interface NotesListeners
{
    void onNoteClicked(Note note, int position);
}
