package com.example.notetaking;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.notetaking.adapters.NotesAdapter;
import com.example.notetaking.adapters.Notes_adapter;
import com.example.notetaking.database.NotesDatabase;
import com.example.notetaking.entities.Note;
import com.example.notetaking.listeners.NotesListeners;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NotesListeners {
FloatingActionButton fab_createNote;
RecyclerView notes_recycler;
NotesAdapter notesAdapter;
Notes_adapter notes_adapter;
List<Note>noteList;
    private int Request_code_add_note=1;
    private int Request_code_update_note=2;
    private int Request_code_show_note=3;
    int noteClickedPosition=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notes_recycler=findViewById(R.id.recycler_notes);
        notes_recycler.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        noteList=new ArrayList<>();
        //notesAdapter=new NotesAdapter(noteList,this::onNoteClicked);
        notes_adapter=new Notes_adapter(noteList);


        notes_recycler.setAdapter(notes_adapter);

        fab_createNote=findViewById(R.id.FAB_createNote);
        fab_createNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivityForResult(new Intent(getApplicationContext(),create_new_note.class),Request_code_add_note);

            }
        });
        getNotes(Request_code_show_note);
    }
    private void getNotes(int request_code)
    {

        class getNotesTask extends AsyncTask<Void,Void,List<Note>>
        {


            @Override
            protected List<Note> doInBackground(Void... voids)
            {

                return NotesDatabase.getDatabase(getApplicationContext()).noteDao().getAllNotes();
            }

            @Override
            protected void onPostExecute(List<Note> notes) {
                super.onPostExecute(notes);
               if (noteList.size()==0)
               {
                   noteList.addAll(notes);
                   notesAdapter.notifyDataSetChanged();
               }
               else
               {

                   noteList.add(0,notes.get(0));
                   notesAdapter.notifyItemInserted(0);

               }

                notes_recycler.smoothScrollToPosition(0);
              /* if (request_code==Request_code_show_note)
               {
                   noteList.addAll(notes);
                   notesAdapter.notifyDataSetChanged();

               }
               else if (request_code==Request_code_add_note)
               {
                   noteList.add(0,notes.get(0));
                   notesAdapter.notifyItemInserted(0);
                   notes_recycler.smoothScrollToPosition(0);

               }
               else if(request_code==Request_code_update_note)
               {

                   noteList.remove(noteClickedPosition);
                   noteList.add(noteClickedPosition,notes.get(noteClickedPosition));
                    notesAdapter.notifyItemChanged(noteClickedPosition);
               }
                notes_recycler.smoothScrollToPosition(0);
*/

            }
        }
        new getNotesTask().execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

      /*  if (requestCode==Request_code_add_note&&resultCode==RESULT_OK)
        {
            getNotes(Request_code_add_note);

        }
        else if (requestCode==Request_code_update_note&&resultCode==RESULT_OK)
        {
            getNotes(Request_code_update_note);

        }*/
        if (requestCode==Request_code_update_note&&resultCode==resultCode)
            {

                getNotes(Request_code_add_note);
            }


    }

    @Override
    public void onNoteClicked(Note note, int position)
    {noteClickedPosition=position;
Intent intent=new Intent(getApplicationContext(),create_new_note.class);
intent.putExtra("update",true);
intent.putExtra("note",note);
startActivityForResult(intent,Request_code_update_note);
    }
}