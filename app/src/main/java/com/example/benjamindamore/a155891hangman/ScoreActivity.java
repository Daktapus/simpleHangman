package com.example.benjamindamore.a155891hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import static com.example.benjamindamore.a155891hangman.StartingScreen.liste;

public class ScoreActivity extends AppCompatActivity implements View.OnClickListener {

    public ListView listView;
    public Adapter a;
    public Button menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        a= new Adapter(getBaseContext(), R.layout.layout_item, liste);
        menu = (Button) findViewById(R.id.menuBut);
        listView = (ListView) findViewById(R.id.scoreView);

        menu.setOnClickListener(this);
        listView.setAdapter(a);
    }

    @Override
    public void onClick(View v) {
        if(v==menu) {
            Intent i = new Intent(ScoreActivity.this, StartingScreen.class);
            this.startActivity(i);
        }
    }
}
