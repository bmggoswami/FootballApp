package com.fixture.football.football;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by bhavesh on 10/5/16.
 */
public class FixtureAdapter extends ArrayAdapter<FixtureModel> {
    Context context;
    public FixtureAdapter(Context context,int resourceId,List<FixtureModel> items){
        super(context,resourceId,items);
    }

    private class ViewHolder{
        TextView home,away,date;
    }

    public View getView(int position,View convertView,ViewGroup parent){
        ViewHolder holder = null;
        FixtureModel model = getItem(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_item,null);
            holder = new ViewHolder();
            holder.home = (TextView)convertView.findViewById(R.id.home);
            holder.away = (TextView)convertView.findViewById(R.id.away);
            holder.date = (TextView)convertView.findViewById(R.id.date);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.home.setText(model.getHome());
        holder.away.setText(model.getAway());
        holder.date.setText(model.getDate());

        return convertView;
    }
}
