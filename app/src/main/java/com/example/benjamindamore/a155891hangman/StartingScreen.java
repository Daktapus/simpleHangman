package com.example.benjamindamore.a155891hangman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StartingScreen extends AppCompatActivity implements View.OnClickListener {

    Button button1;

    //TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set default view til starting screen
        setContentView(R.layout.activity_starting_screen);

        button1 = (Button) findViewById(R.id.start);

        button1.setOnClickListener(this);
    }

    @Override
    public void onClick(View kkk) {
        switch(kkk.getId()){
            case R.id.start:
                Intent e = new Intent(this,Hangman.class);
                startActivity(e);
                break;
            default:
                Toast.makeText(this, "Default hit", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}