package com.example.notetaking.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notetaking.R;
import com.example.notetaking.entities.Note;

import java.util.List;

public class Notes_adapter extends RecyclerView.Adapter<Notes_adapter.NotesHolder>
{
List<Note>notes;

    public Notes_adapter(List <Note> notes) {
        this.notes = notes;
    }


    @NonNull
    @Override
    public NotesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesHolder holder, int position)
    {
        holder.setNote(notes.get(position));

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class NotesHolder extends RecyclerView.ViewHolder
    {
    TextView txt_note,txt_note_title;

         NotesHolder(@NonNull View itemView) {
            super(itemView);

            txt_note=itemView.findViewById(R.id.note_txt);
            txt_note_title=itemView.findViewById(R.id.note_title_txt);

        }
        void setNote(Note note)
        {

            txt_note.setText(note.getNoteText());
            txt_note_title.setText(note.getTitle());
        }
    }
}
