package com.jestarok.connect4;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.EventListener;
import java.util.List;

/**
 * Created by mc on 9/30/2017.
 */

public class ChipsAdapter extends ArrayAdapter<Chip> {


    public ChipsAdapter(Context context,  List<Chip> chips) {
        super(context, 0, chips);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Chip chips = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.chipitem,parent,false);
        }
//        Button play = (Button) convertView.findViewById(R.id.btnChip);

        return convertView;
    }
}
