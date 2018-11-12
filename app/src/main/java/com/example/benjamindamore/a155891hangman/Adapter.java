package com.example.benjamindamore.a155891hangman;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter extends ArrayAdapter<ListItemObject>{

    public Adapter(@NonNull Context context, int resource, @NonNull List<ListItemObject> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;

        if(v == null){
            LayoutInflater in = LayoutInflater.from(getContext());
            v = in.inflate(R.layout.layout_item, null);
        }

        ListItemObject objectPos = getItem(position);

        if(objectPos != null) {
            TextView tvscore = (TextView) v.findViewById(R.id.tvhighscore);
            TextView tvord = (TextView) v.findViewById(R.id.tvord);
            TextView tvforkerteord = (TextView) v.findViewById(R.id.tvforkerteord);
            if (tvscore != null) {
                tvscore.setText("" + objectPos.getHighscore());
            }
            if (tvord != null) {
                tvord.setText(objectPos.getOrd());
            }
            if (tvforkerteord != null) {
                tvforkerteord.setText("" + objectPos.getAntalForkerteGæt());
            }
        }

        return v;
    }
}