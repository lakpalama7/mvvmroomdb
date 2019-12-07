package com.liveinbits.mvvmactivity.Repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.liveinbits.mvvmactivity.dao.AppDatabase;
import com.liveinbits.mvvmactivity.dao.NoteModel;
import com.liveinbits.mvvmactivity.utils.SampleDataProvider;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppDataRepository {

    private static AppDataRepository ourInstance;
    public LiveData<List<NoteModel>> noteModelList;
    private AppDatabase appDatabase;

    private Executor executor= Executors.newSingleThreadExecutor();

    public static AppDataRepository getInstance(Context context) {
        return ourInstance=new AppDataRepository(context);
    }

    private AppDataRepository(Context context) {
        appDatabase=AppDatabase.getInstance(context);
        noteModelList= getLiveDataNoteList();
    }

    private LiveData<List<NoteModel>> getLiveDataNoteList() {
        return appDatabase.noteDao().getNoteList();
    }

    public void addSampleData() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                appDatabase.noteDao().insertAll(SampleDataProvider.getListNote());
            }
        });
    }

    public NoteModel getNoteById(int noteid) {
        return appDatabase.noteDao().getSingleNote(noteid);
    }

    public void saveEditedOrNewNote(final NoteModel singleNote) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                appDatabase.noteDao().insertNote(singleNote);
            }
        });
    }


    public void deleteSingleNote(final NoteModel singlenote) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                appDatabase.noteDao().deleteNote(singlenote);
            }
        });
    }

    public void deleteAllNote() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                appDatabase.noteDao().deleteAllNotes();
            }
        });
    }
}
