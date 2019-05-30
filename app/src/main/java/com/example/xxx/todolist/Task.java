package com.example.xxx.todolist;


import android.arch.persistence.room.*;

@Entity
public class Task {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "hours")
    private int hours;
    @ColumnInfo(name = "minutes")
    private int minutes;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo (name = "checked")
    private int checked;
    @ColumnInfo (name = "color")
    private int color;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getChecked() {
        return checked;

    }

    public Task (String name, int hours, int minutes, int checked, int color)
    {
        this.hours=hours;
        this.minutes=minutes;
        this.name=name;
        this.checked=checked;
        this.color=color;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
