package com.proyecto.listadorol.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.proyecto.listadorol.R;

import java.util.Timer;
import java.util.TimerTask;

public class LogoActivity extends AppCompatActivity {

    String TAG="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);


        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(LogoActivity.this, ListadoActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);


    }
    @Override
    protected void onStop(){
        super.onStop();
        this.TAG = this.TAG + " Stop";
    }


    @Override
    protected void onDestroy(){
        super.onDestroy();
        this.TAG = this.TAG + " Destroy";
    }


    @Override
    protected void onStart(){
        super.onStart();
        String TAG = "aniGRUD/MainActivitiy start";
    }


    @Override
    protected void onPause(){
        super.onPause();
        String TAG = "aniGRUD/MainActivitiy pause";
    }


    @Override
    protected void onRestart(){
        super.onRestart();
        String TAG = "aniGRUD/MainActivitiy Restart";
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
