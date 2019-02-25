package com.sun.seat_layout.Database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Ticket_list {


    @PrimaryKey(autoGenerate = true)
    int id;


    String seatnumber;

    public Ticket_list(int id, String seatnumber) {
        this.id = id;
        this.seatnumber = seatnumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeatnumber() {
        return seatnumber;
    }

    public void setSeatnumber(String seatnumber) {
        this.seatnumber = seatnumber;
    }
}
