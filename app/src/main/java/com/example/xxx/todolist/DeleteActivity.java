package com.example.xxx.todolist;

import android.app.ActionBar;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DeleteActivity extends AppCompatActivity implements Delete_Recycler.ItemClickListener
{
    private RecyclerView recyclerView;
    private Delete_Recycler adapter;
    private List<Task> task = new LinkedList();
    private DataBase db;
    private LinearLayout linear;
    private RecyclerView recyclerView1;

    private ArrayList positions = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.deleteactivity);
        db = Room.databaseBuilder(getApplicationContext(), DataBase.class, "Taskbase2")
                .allowMainThreadQueries()
                .build();
        task = db.taskDao().loadAllTasks();
        linear = (LinearLayout) findViewById(R.id.linear);
        recyclerView = (RecyclerView) findViewById(R.id.tasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Delete_Recycler(this, task);
        adapter.setClickListener(this::onItemClick);
        recyclerView.setAdapter(adapter);
getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            back();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(View view, int position) {

       db.taskDao().delete(task.get(position));
positions.add(position);
       task.remove(position);
       adapter.notifyDataSetChanged();
    }

    public void back ()
    {
        Bundle extras = new Bundle();
        Intent i = new Intent();
        final EditText NameInput = (EditText) findViewById(R.id.Name);

        extras.putParcelableArrayList("positions",positions);
        i.putExtras(extras);
        setResult(RESULT_OK,i);
        finish();



    }
}
