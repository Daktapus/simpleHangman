package com.example.benjamindamore.a155891hangman;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Hangman extends AppCompatActivity implements View.OnClickListener {

    Button button1, button2, button3;
    TextView textView, gameInfo;
    ImageView hangMan;
    galgelegLogik logik;
    EditText letterGuess;
    InputMethodManager inputManager;
    public galgelegLogik g = galgelegLogik.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set default view til starting screen
        setContentView(R.layout.activity_main);
        logik = new galgelegLogik();

        //ASYNCTASK, TJEKKER OM ORDNE BLIVER HENTET ORDENTLIGT OG GIVER LISTE.

        new AsyncTask() {
            @Override
            protected Object doInBackground(Object... arg0) {
                try {
                    logik.hentOrdFraDr();
                    return "Ordene blev korrekt hentet fra DR's server";
                } catch (Exception e) {
                    e.printStackTrace();
                    return "Ordene blev ikke hentet korrekt: "+e;
                }
            }

            @Override
            protected void onPostExecute(Object resultat) {
                System.out.println("resultat: \n" + resultat);
            }
        }.execute();

        //stackoverflow solution to hiding keyboard on buttonclick (https://stackoverflow.com/questions/3400028/close-virtual-keyboard-on-button-press)
        inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        hangMan=(ImageView) findViewById(R.id.hangMan);
        hangMan.setVisibility(View.INVISIBLE);

        letterGuess = (EditText) findViewById(R.id.t_letterGuess);
        letterGuess.setVisibility(View.INVISIBLE);

        textView = (TextView) findViewById(R.id.t_guessLetter);
        textView.setVisibility(View.INVISIBLE);

        gameInfo = (TextView) findViewById(R.id.t_gameInfo);
        gameInfo.setVisibility(View.INVISIBLE);

        button1 = (Button) findViewById(R.id.b_play_game);
        button1.setOnClickListener(this);

        button2 = (Button) findViewById(R.id.b_guess_letter);
        button2.setVisibility(View.INVISIBLE);
        button2.setOnClickListener(this);

        button3 = (Button) findViewById(R.id.b_give_up);
        button3.setVisibility(View.INVISIBLE);
        button3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.b_play_game:
                logik.nulstil();
                opdaterSkærm();
                Toast.makeText(this, "game started", Toast.LENGTH_SHORT).show();

                //hides playbutton and shows game.
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.VISIBLE);
                button3.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                letterGuess.setVisibility(View.VISIBLE);
                gameInfo.setVisibility(View.VISIBLE);
                hangMan.setVisibility(View.VISIBLE);
                break;

            case R.id.b_guess_letter:
                //hides keyboard on click.
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

                String bogstav = letterGuess.getText().toString();
                if (bogstav.length() != 1) {
                    textView.setText("Write one letter");
                    return;
                }
                logik.gætBogstav(bogstav);
                letterGuess.setText("");
                letterGuess.setError(null);
                opdaterSkærm();
                break;

            case R.id.b_give_up:
                //hides keyboard on click.
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

                //hides game and show playbutton
                button1.setVisibility(View.VISIBLE);
                button2.setVisibility(View.INVISIBLE);
                button3.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.INVISIBLE);
                letterGuess.setVisibility(View.INVISIBLE);
                gameInfo.setVisibility(View.INVISIBLE);
                hangMan.setVisibility(View.INVISIBLE);
                break;
            default:
                Toast.makeText(this, "Default hit", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    private void opdaterSkærm() {
        gameInfo.setText("Guess the word " + logik.getSynligtOrd());
        gameInfo.append("\n\nYou  have " + logik.getAntalForkerteBogstaver() + " wrong:" + logik.getBrugteBogstaver());


        if (logik.erSpilletVundet()) {
            gameInfo.append("\nYou won");
            StartingScreen.liste.add(new ListItemObject(score(), g.getOrdet(), g.getAntalForkerteBogstaver()));
            Intent e = new Intent(this,VundetActivity.class);
            startActivity(e);
        }
        if (logik.erSpilletTabt()) {
            gameInfo.setText("You lost. The word was: " + logik.getOrdet());
            StartingScreen.liste.add(new ListItemObject(score(), g.getOrdet(), g.getAntalForkerteBogstaver()));
            Intent e = new Intent(this,tabtActivity.class);
            startActivity(e);
        }
        switch (logik.getAntalForkerteBogstaver()) {
            case 0:
                hangMan.setImageResource(R.drawable.galge);
                break;
            case 1:
                hangMan.setImageResource(R.drawable.forkert1);
                break;
            case 2:
                hangMan.setImageResource(R.drawable.forkert2);
                break;
            case 3:
                hangMan.setImageResource(R.drawable.forkert3);
                break;
            case 4:
                hangMan.setImageResource(R.drawable.forkert4);
                break;
            case 5:
                hangMan.setImageResource(R.drawable.forkert5);
                break;
            case 6:
                hangMan.setImageResource(R.drawable.forkert6);
                break;
            case 7:
                Toast.makeText(this, "You lost", Toast.LENGTH_SHORT).show();
                Intent e = new Intent(this,tabtActivity.class);
                startActivity(e);
                break;
            default:
                Toast.makeText(this, "Default hit", Toast.LENGTH_SHORT).show();
        }
    }
    private int score (){
        return g.getOrdet().length();
    }

}