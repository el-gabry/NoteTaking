package com.example.notetaking.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notetaking.R;
import com.example.notetaking.entities.Note;
import com.example.notetaking.listeners.NotesListeners;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder>
{
    List<Note>notes;
    NotesListeners notesListeners;
    public NotesAdapter(List<Note>notes,NotesListeners notesListeners)
    {
        this.notes=notes;
        this.notesListeners=notesListeners;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
holder.setNote(notes.get(position));
holder.note_layout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        notesListeners.onNoteClicked(notes.get(position),position);
    }
});
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class NotesViewHolder extends RecyclerView.ViewHolder
    {
TextView txt_title,txt_note;
LinearLayout note_layout;
         NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_title=itemView.findViewById(R.id.txt_noteTitle);
            txt_note=itemView.findViewById(R.id.txt_note);
            note_layout=itemView.findViewById(R.id.notes_layout);
        }

        void setNote(Note note)
        {
            txt_title.setText(note.getTitle());
            txt_note.setText(note.getNoteText());

        }
    }
}
