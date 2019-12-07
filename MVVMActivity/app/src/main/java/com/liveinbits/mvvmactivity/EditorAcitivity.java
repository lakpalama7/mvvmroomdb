package com.liveinbits.mvvmactivity;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.liveinbits.mvvmactivity.dao.NoteModel;
import com.liveinbits.mvvmactivity.utils.Constant;
import com.liveinbits.mvvmactivity.viewmodel.EditorViewModel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class EditorAcitivity extends AppCompatActivity {

    private EditText editText;
    private EditorViewModel editorViewModel;
    private boolean isNewNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_acitivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_check);

        editText=findViewById(R.id.edit_text_note);

        getMutableLiveData();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void getMutableLiveData() {
        Observer<NoteModel> observer=new Observer<NoteModel>() {
            @Override
            public void onChanged(NoteModel noteModel) {
                if(noteModel!=null) {
                    editText.setText(noteModel.getText());
                }
            }
        };
        editorViewModel= ViewModelProviders.of(this).get(EditorViewModel.class);
        editorViewModel.mutableLiveData.observe(EditorAcitivity.this,observer);

        Bundle bundle=getIntent().getExtras();
        if(bundle==null){
            setTitle("New Note");
            isNewNote=true;
        }
        else{
            setTitle("Edit Note");
            int noteid=bundle.getInt(Constant.NOTE_KEY_ID);
            editorViewModel.setNoteId(noteid);
            isNewNote=false;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(!isNewNote){
            getMenuInflater().inflate(R.menu.single_item_delete_layout,menu);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()== android.R.id.home){
            saveEditedNote();
            finish();
        }
        else if(item.getItemId()==R.id.single_delete){
            deleteSingleNote();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteSingleNote() {
        editorViewModel.deleteSingleNote();
    }

    private void saveEditedNote() {
        editorViewModel.saveEditedNote(editText.getText().toString().trim());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveEditedNote();
    }
}
