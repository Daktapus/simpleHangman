package com.example.benjamindamore.a155891hangman;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;


public class VundetActivity extends AppCompatActivity implements View.OnClickListener {

    public Button score;
    public Button menu;
    public TextView won;
    public KonfettiView kv;
    MediaPlayer winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vundet);

        score = (Button) findViewById(R.id.scoreBut);
        won = (TextView) findViewById(R.id.tvvundet);
        menu = (Button) findViewById(R.id.menuBut);
        kv = (KonfettiView) findViewById(R.id.konView);

        score.setOnClickListener(this);
        won.setOnClickListener(this);
        menu.setOnClickListener(this);

        winner = MediaPlayer.create(this, R.raw.victory);


       kv.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(new Size(12,5f))
                .setPosition(-50f, kv.getWidth() + 50f, -50f, -50f)
                .stream(300, 5000L);
        winner.start();
    }

    @Override
    public void onClick(View v) {
        if(v==score) {
            won.setText("Du har vundet!!");
            Intent won = new Intent(VundetActivity.this, ScoreActivity.class);
            this.startActivity(won);
            finish();
        }
        else if (v==menu){
            Intent menu = new Intent (VundetActivity.this, StartingScreen.class);
            this.startActivity(menu);
            finish();
        }
        finish();
    }
}