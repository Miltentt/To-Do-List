package com.example.xxx.todolist;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.*;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.LinkedList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements RecycleViewAdapter.ItemClickListener {
    private LinearLayout linear;
    private int j = 0;
    private RecyclerView recyclerView;
    private RecycleViewAdapter adapter;
    private List<Task> task = new LinkedList();
    private DataBase db;
    private AlarmBAse pendings;
    private List<PendingIntent> broadcast = new LinkedList<>();
    private List<PendingBroadcasts> ids = new LinkedList<>();
    private int[] color = {1, 2, 3, 4};
    private TextView lol;
    private AlarmManager alarmManager;


    @Override
    protected void onResume() {
        super.onResume();
        db = Room.databaseBuilder(getApplicationContext(), DataBase.class, "Taskbase2")
                .allowMainThreadQueries()
                .build();
        task = db.taskDao().loadAllTasks();
        linear = (LinearLayout) findViewById(R.id.linear);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecycleViewAdapter(this, task);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        lol = findViewById(R.id.lol);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        pendings = Room.databaseBuilder(getApplicationContext(), AlarmBAse.class, "pending")
                .allowMainThreadQueries()
                .build();
        ids = pendings.PendingDao().loadAllTasks();
        setContentView(R.layout.activity_main);

        if (ids.isEmpty() == false) {
            for (int i = 0; i < ids.size(); i++) {
                Intent intent = new Intent(this, NotificationActivity.class);
                broadcast.add(PendingIntent.getBroadcast(this, i, intent, PendingIntent.FLAG_UPDATE_CURRENT));

            }
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.layout.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.Delete: {
                Intent i = new Intent(this, DeleteActivity.class);
                startActivityForResult(i, 3);
                break;
            }
            case R.id.DeleteAll: {

                db.clearAllTables();
                pendings.clearAllTables();
                finish();
                startActivity(getIntent());
                break;

            }
        }


        return super.onOptionsItemSelected(item);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 2: {
                    Bundle NameData = data.getExtras();

                    task.add(new Task(NameData.getString("Name"), NameData.getInt("hours"), NameData.getInt("minutes"), 0, color[j]));
                    db.taskDao().Insert(task.get(task.size() - 1));
                    Alarm(task.get(task.size() - 1).getHours(), task.get(task.size() - 1).getMinutes(), task.get(task.size() - 1).getName());
                    j++;
                    if (j == 3)
                        j = 0;
                    break;
                }
                case 3: {
                    Bundle positions = data.getExtras();
                    ArrayList<Integer> position = new ArrayList<>();
                    position = positions.getIntegerArrayList("positions");
                    for (int n : position) {


                    }
                    break;
                }
            }
        }
    }


    public void Actionbutt(View view) {
        Intent i = new Intent(this, Data.class);
        startActivityForResult(i, 2);
    }


    private void Alarm(int hours, int minutes, String name) {
        int id;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minutes);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this, NotificationActivity.class);
        if (broadcast.isEmpty()) {
            id = 0;
            pendings.PendingDao().Insert(new PendingBroadcasts(id));
        } else {
            id = broadcast.size();
            pendings.PendingDao().Insert(new PendingBroadcasts(id));
        }
        intent.putExtra("name", name);
        broadcast.add(PendingIntent.getBroadcast(this, id, intent, PendingIntent.FLAG_UPDATE_CURRENT));
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), broadcast.get(id));


    }

    public void delete(View view) {
        db.clearAllTables();
        pendings.clearAllTables();
        finish();
        startActivity(getIntent());

    }

    public void deletee(View view) {
        Intent i = new Intent(this, DeleteActivity.class);
        startActivityForResult(i, 3);
    }

    @Override
    public void onItemClick(View view, int position) {

lol.setText("xDDDDDD");
        if (task.get(position).getChecked() == 0)
            task.get(position).setChecked(1);
        else
            task.get(position).setChecked(0);
        db.taskDao().update(task.get(position));
    }
}
