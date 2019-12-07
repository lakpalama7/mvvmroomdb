package com.liveinbits.mvvmactivity.viewmodel;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.liveinbits.mvvmactivity.Repository.AppDataRepository;
import com.liveinbits.mvvmactivity.dao.NoteModel;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EditorViewModel extends AndroidViewModel {

    public MutableLiveData<NoteModel> mutableLiveData=new MutableLiveData<>();
    private AppDataRepository repository;

    private Executor executor= Executors.newSingleThreadExecutor();
    public EditorViewModel(@NonNull Application application) {
        super(application);
        repository=AppDataRepository.getInstance(application.getApplicationContext());
    }

    public void setNoteId(final int noteid) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                NoteModel noteModel=repository.getNoteById(noteid);
                mutableLiveData.postValue(noteModel);//in backgroudthread use postValue(); //outside backgroud thread use setValue();
            }
        });
    }

    public void saveEditedNote(String noteText) {
        NoteModel singleNote=mutableLiveData.getValue();
        if(singleNote==null){
            if(TextUtils.isEmpty(noteText)){
                return;
            }
            else{
                singleNote=new NoteModel(new Date(),noteText);
            }

        }
        else{
            singleNote.setText(noteText);
            singleNote.setDate(new Date());

        }
        repository.saveEditedOrNewNote(singleNote);

    }

    public void deleteSingleNote() {
        repository.deleteSingleNote(mutableLiveData.getValue());
    }
}
