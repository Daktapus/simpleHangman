package com.example.benjamindamore.a155891hangman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StartingScreen extends AppCompatActivity implements View.OnClickListener {
    public static List liste = new ArrayList<ListItemObject>();
    public Set<String> save=new HashSet<String>(liste);

    Button button1;
    Button button2;
    Button button3;

    //TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set default view til starting screen
        setContentView(R.layout.activity_starting_screen);

        button1 = (Button) findViewById(R.id.start);
        button2 = (Button) findViewById(R.id.highscore);
        button3 = (Button) findViewById(R.id.regler);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View kkk) {
        if(kkk==button1)    {
            Intent i = new Intent(StartingScreen.this, Hangman.class);
            StartingScreen.this.startActivity(i);

        }   else if (kkk==button2){
            Intent j = new Intent(StartingScreen.this, ScoreActivity.class);
            StartingScreen.this.startActivity(j);

        }   else if (kkk==button3){
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Spillets regler");
            dialog.setMessage("Dette spil går ud på at gætte det skjulte ord. Hver stjerne symboliserer et bogstav. Hvis det bogstav der gættes på findes i ordet, vil bogstavet erstatte stjernen på den rette placering i ordet. Gættes der derimod på det forkerte bogstav, vil der blive tegnet en del af den hængende mand. Når hele manden er tegnet, og ordet ikke er gættet, er spillet tabt. Gætter man ordet inden manden bliver hængt, er spillet vundet");
            dialog.show();
        }
        }

//ITERATION 1 STARTING SCREEN. IGNORE

        /*switch(kkk.getId()){
            case  R.id.start:
                Intent e = new Intent(this,Hangman.class);
                startActivity(e);
                break;

            case R.id.highscore:
                Intent f = new Intent(this, ScoreActivity.class);
                startActivity(f);
                break;

            default:
                Toast.makeText(this, "Default hit", Toast.LENGTH_SHORT).show();
                break;
        }*/
    }