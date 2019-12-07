package com.liveinbits.mvvmactivity.utils;

import com.liveinbits.mvvmactivity.dao.NoteModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SampleDataProvider {

    private static final String NOTE1="hello world...";
    private static final String NOTE2="sri venkateshwara college \n of engineering..";
    private static final String NOTE3="cloud computing assignment next\n week from unit 1 to unit 3...";

    public static Date getDate(int diff){
        GregorianCalendar calendar=new GregorianCalendar();
        calendar.add(Calendar.MILLISECOND,diff);
        return calendar.getTime();
    }

    public static List<NoteModel> getListNote(){
        List<NoteModel> models=new ArrayList<>();

        models.add(new NoteModel(getDate(0),NOTE1));
        models.add(new NoteModel(getDate(-1),NOTE2));
        models.add(new NoteModel(getDate(-2),NOTE3));
        return models;
    }


}
