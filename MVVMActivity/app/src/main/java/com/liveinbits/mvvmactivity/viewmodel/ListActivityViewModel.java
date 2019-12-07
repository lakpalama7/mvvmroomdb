package com.liveinbits.mvvmactivity.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.liveinbits.mvvmactivity.Repository.AppDataRepository;
import com.liveinbits.mvvmactivity.dao.NoteModel;
import com.liveinbits.mvvmactivity.utils.SampleDataProvider;

import java.util.List;

public class ListActivityViewModel extends AndroidViewModel {

    public LiveData<List<NoteModel>> listNote;
    private AppDataRepository repository;
    public ListActivityViewModel(@NonNull Application application) {
        super(application);
        repository=AppDataRepository.getInstance(application.getApplicationContext());
        listNote=repository.noteModelList;
    }

    public void addSampleData() {
        repository.addSampleData();
    }

    public void deleteAllNote() {
        repository.deleteAllNote();
    }
}
