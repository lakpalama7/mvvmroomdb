package com.liveinbits.mvvmactivity.converter;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverter {

    @TypeConverter
    public static Long getDateLong(Date date){
        return date==null?null:date.getTime();
    }
    @TypeConverter
    public static Date getDate(Long timestamp){
        return timestamp==null?null:new Date(timestamp);
    }
}
