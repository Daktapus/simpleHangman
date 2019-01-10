//Det virker ikke helt lol
//Bare ignore


package com.example.benjamindamore.a155891hangman;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

public class SpilvalgActivity extends AppCompatActivity implements View.OnClickListener {

    private Button randBtn;
    private Button listeBtn;
    private galgelegLogik logik = galgelegLogik.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spilvalg);

        randBtn =(Button) findViewById(R.id.rand_btn);
        listeBtn =(Button) findViewById(R.id.liste_btn);

        listeBtn.setOnClickListener(this);
        randBtn.setOnClickListener(this);

        System.out.println("Henter ord fra DRs server....");
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

    }

    private void ordListDialog() {
        AlertDialog ad = new AlertDialog.Builder(this).create();

        ad.setCancelable(true);
        ad.setTitle("Vælg et ord!");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        ListView listView = new ListView(this);

        listView.setOnItemClickListener((adapter, v, position, arg3) -> {
            logik.nulstilMedOrd(position);
            Intent intent = new Intent(SpilvalgActivity.this, Hangman.class);
            this.startActivity(intent);
            ad.dismiss();
        });

        List<String> list = logik.muligeOrd;

        ArrayAdapter<String> adapt = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);

        listView.setAdapter(adapt);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        layout.addView(listView);

        ad.setView(layout);

        ad.show();
    }

    @Override
    public void onClick(View v) {
        if(v==randBtn){
            Intent intent = new Intent(SpilvalgActivity.this, Hangman.class);
            this.startActivity(intent);
        }
        else{
            ordListDialog();
        }

    }
}
