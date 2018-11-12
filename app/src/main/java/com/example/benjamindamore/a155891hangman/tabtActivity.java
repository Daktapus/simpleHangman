package com.example.benjamindamore.a155891hangman;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class tabtActivity extends AppCompatActivity implements View.OnClickListener{

    public TextView tvtabt;
    public Button menu;
    public Button score;
    MediaPlayer lost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabt);

        tvtabt = (TextView) findViewById(R.id.tvtabt);
        menu = (Button) findViewById(R.id.menuBut);
        score = (Button) findViewById(R.id.scoreBut);

        menu.setOnClickListener(this);
        tvtabt.setOnClickListener(this);
        score.setOnClickListener(this);

        lost = MediaPlayer.create(this, R.raw.sad);

        lost.start();

    }

    @Override
    public void onClick(View v) {
        tvtabt.setText("Du har tabt");
        if(v==menu){
            Intent i = new Intent(tabtActivity.this, StartingScreen.class);
            this.startActivity(i);
        }
        else if (v==score) {
            Intent i = new Intent(tabtActivity.this, ScoreActivity.class);
            this.startActivity(i);
        }

    }
}
