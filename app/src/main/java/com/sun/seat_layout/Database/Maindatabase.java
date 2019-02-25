package com.sun.seat_layout.Database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.sun.seat_layout.Dao.Data_manipulation;

@Database(entities = {Ticket_list.class},version = 1)
public abstract class Maindatabase extends RoomDatabase {

    private static Maindatabase INSTANCE;

    public abstract Data_manipulation userDao();

    public static Maindatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), Maindatabase.class, "user-database")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }


}
