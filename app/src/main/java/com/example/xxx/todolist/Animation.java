package com.example.xxx.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class Animation extends AppCompatActivity {
private ImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_screen);
        image = (ImageView) findViewById(R.id.imageView);

       android.view.animation.Animation animation = AnimationUtils.loadAnimation(this,R.anim.transition);
image.startAnimation(animation);

Intent i = new Intent(this,MainActivity.class);
Thread timer = new Thread(){
    public void run()
            {
                try{
                    sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }

        };
timer.start();
    }
}
