package com.example.notetaking;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notetaking.database.NotesDatabase;
import com.example.notetaking.entities.Note;

public class create_new_note extends AppCompatActivity {
EditText edtNoteTitle,edtNote;
ImageView btn_save;
Note saved_note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_note);

    edtNoteTitle=findViewById(R.id.edtNoteTitle);
    edtNote=findViewById(R.id.edtNote);
        btn_save=findViewById(R.id.btnDone);
    btn_save.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            saveNote();
        }
    });
if (getIntent().getBooleanExtra("update",false))
{
    saved_note= (Note) getIntent().getSerializableExtra("note");
    setUpdatedNote();


}


    }

    private void setUpdatedNote()
    {
        edtNoteTitle.setText(saved_note.getTitle());
        edtNote.setText(saved_note.getNoteText());


    }

    public  void saveNote()
    {
        if (edtNoteTitle.getText().toString().trim().isEmpty())
        {

            Toast.makeText(this, "title can't be empty", Toast.LENGTH_SHORT).show();
        return;
        }
        Note note=new Note();
        note.setTitle(edtNoteTitle.getText().toString());
        note.setNoteText(edtNote.getText().toString());
        if (saved_note!=null)
        {
            note.setId(saved_note.getId());
        }

        class saveNoteTask extends AsyncTask<Void,Void,Void>
        {


            @Override
            protected Void doInBackground(Void... voids) {
                NotesDatabase.getDatabase(getApplicationContext()).noteDao().insertNote(note);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Intent intent=new Intent();
                setResult(RESULT_OK,intent);
finish();
            }
        }
    new saveNoteTask().execute();
    }


}