package com.fixture.football.football;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bhavesh on 22/4/16.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    String[] name = {"UEFA Euro 2016","English Premeir League","La Liga","Bundesliga","UEFA Champions League"};
    String[] lId = {"424","398","399","394","405"};
    String[] dMap = {"euro","epl","laliga","bundes","uefa"};
    Map<String,Integer> map = new HashMap<String,Integer>();

    Context context;
    LayoutInflater inflater;
    public static final String EXTRA_MESSAGE = "com.fixture.football.message";
    public RecycleAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        map.put("epl",R.drawable.epl);
        map.put("laliga",R.drawable.laliga);
        map.put("euro",R.drawable.euro);
        map.put("uefa",R.drawable.uefa);
        map.put("bundes",R.drawable.bundes);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_list, parent, false);

        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        holder.tv1.setText(name[position]);
        holder.imageView.setImageResource(map.get(dMap[position]));

        holder.tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("POSITION","PSOTIOM :- "+position);
                Intent intent = new Intent(context, MyTabActivity.class);
                intent.putExtra(EXTRA_MESSAGE, name[position]);
                intent.putExtra("league_id", lId[position]);
                context.startActivity(intent);
            }
        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("POSITION","PSOTIOM :- "+position);
                Intent intent = new Intent(context, MyTabActivity.class);
                intent.putExtra(EXTRA_MESSAGE, name[position]);
                intent.putExtra("league_id", lId[position]);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return name.length;
    }
}
