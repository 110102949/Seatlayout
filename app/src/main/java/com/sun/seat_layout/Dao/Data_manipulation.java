package com.sun.seat_layout.Dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.sun.seat_layout.Ticket_modelclass;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface Data_manipulation {



    @Insert
    Completable insert(List<Ticket_modelclass> ticket_modelclassList);


    @Delete
    void delete(Ticket_modelclass... ticket_modelclasses);




}
