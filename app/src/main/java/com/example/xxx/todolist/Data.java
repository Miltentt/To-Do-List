package com.example.xxx.todolist;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.View;

public class Data extends AppCompatActivity  {

    private TimePicker simpleTimePicker;
    private int hours;
    private int minutes;
    private int colors[]={Color.parseColor("#8E0DCE"),Color.parseColor("#FF0000"),Color.parseColor("#009900"),Color.parseColor("#0080FF")};
    private int i= 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        simpleTimePicker = (TimePicker) findViewById(R.id.TimeP);
simpleTimePicker.setIs24HourView(true);
        hours = simpleTimePicker.getHour();
        minutes = simpleTimePicker.getMinute();
        simpleTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                hours = timePicker.getHour();
                minutes = timePicker.getMinute();
            }
        });
    }


    public void onClick(View view)
    {
        Bundle extras = new Bundle();
        Intent i = new Intent();
        final EditText NameInput = (EditText) findViewById(R.id.Name);
        String Name = NameInput.getText().toString();
        extras.putString("Name",Name);
        extras.putInt("hours",hours);
        extras.putInt("minutes",minutes);
        i.putExtras(extras);

       setResult(RESULT_OK,i);

        finish();



    }

}
