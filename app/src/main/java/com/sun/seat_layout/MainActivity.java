package com.sun.seat_layout;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.sun.seat_layout.Database.Maindatabase;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ViewGroup layout;
    int seatGaping = 10;



    Maindatabase maindatabase;



    List<String> seat_list = new ArrayList<String>();


    List<Ticket_modelclass> selected_seats = new ArrayList<Ticket_modelclass>();


    int count_id = 1;

    Button seat_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        maindatabase = Room.databaseBuilder(MainActivity.this,
                Maindatabase.class, "sample-db").build();

/*

        if (maindatabase.data_manipulation().getall().size() > 0) {


            List<Ticket_modelclass> ticket_modelclasses = maindatabase.data_manipulation().getall();


            Log.d("suneel", ticket_modelclasses.get(0).getMovie_ticket());


        }

*/

        String seats = "111111/"
                + "001111/"
                + "111110/";


        layout = findViewById(R.id.layoutSeat);


        seats = "/" + seats;
        LinearLayout layout_main = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout_main.setLayoutParams(params);
        layout_main.setOrientation(LinearLayout.VERTICAL);
        layout_main.setPadding(8 * seatGaping, 8 * seatGaping, 8 * seatGaping, 8 * seatGaping);
        layout.addView(layout_main);
        LinearLayout layout = null;


        for (int i = 0; i < seats.length(); i++) {

            if (seats.charAt(i) == '1') {


                seat_list.add("A" + i);
                View v; // Creating an instance for View Object
                LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.seat, null);


                v.setId(Integer.parseInt("1" + i));
                seat_btn = (Button) v.findViewById(R.id.btn);
                seat_btn.setText("A" + i);

                seat_btn.setOnClickListener(this);
                v.setTag("A" + i);
                layout.addView(v);


            } else if (seats.charAt(i) == '0') {


                View v; // Creating an instance for View Object
                LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.empty_sea_layoutt, null);
                v.setId(Integer.parseInt("0" + i));
                layout.addView(v);


            } else {


                if (seats.charAt(i) == '/') {
                    layout = new LinearLayout(this);
                    layout.setOrientation(LinearLayout.HORIZONTAL);
                    layout_main.addView(layout);


                }


            }

        }


        Log.d("suneel_list", String.valueOf(seat_list.size()));


    }


    @Override
    public void onClick(View view) {


        Button button = (Button) view;


        if (button.getTag() == "0") {

            button.setBackgroundResource(R.drawable.seat_layout);
            button.setTextColor(Color.BLACK);
            button.setTag("");


            for (int i = 0; i < selected_seats.size(); i++) {

                if (selected_seats.get(i).equals(button.getText().toString())) {

                    selected_seats.remove(i);

                }
            }


        } else {
            button.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            button.setTextColor(Color.WHITE);
            button.setTag("0");



            selected_seats.add(new Ticket_modelclass(button.getText().toString()));



        }


    }

    public void submit(View view) {


        Completable.fromRunnable(new Runnable() {
            @Override
            public void run() {


                maindatabase.userDao().insert(selected_seats);


            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {


                        Toast.makeText(MainActivity.this, "completed", Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });


    }
    }

