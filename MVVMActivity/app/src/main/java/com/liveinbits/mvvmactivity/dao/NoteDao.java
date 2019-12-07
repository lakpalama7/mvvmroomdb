package com.liveinbits.mvvmactivity.dao;

import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(NoteModel noteModel);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<NoteModel> noteModelList);

    @Delete
    void deleteNote(NoteModel noteModel);

    @Query("SELECT * FROM notes WHERE id =:id")
    NoteModel getSingleNote(int id);

    @Query("select * from notes order by date desc")
    LiveData<List<NoteModel>> getNoteList(); //livedata is handle by room in the background.

    @Query("delete from notes")
    int deleteAllNotes();

    @Query("select count(*) from notes")
    int getCount();
}
