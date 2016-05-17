package com.fixture.football.football;

import android.app.ListFragment;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by bhavesh on 16/5/16.
 */
public class StandingsTab extends Fragment {
    private String league_id;
    private String tag_json_array = "json_array_req";
    private String url = "http://54.187.141.48/index.php/api/v1/standings/";
    private ProgressDialog progressDialog;

    public void setLeagueId(String league_id){
        this.league_id = league_id;
        this.url = this.url + this.league_id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.standings_tab, container, false);
        final TableLayout tableLayout = (TableLayout) view.findViewById(R.id.standings_table);
        Bundle bundle = this.getArguments();
        this.url = this.url + bundle.getString("league_id");

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        JsonArrayRequest req = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("RESPONSE :- ", response.toString());
                String[] array = new String[response.length()];
                for(int i=0;i<response.length();i++){
                    try{
                        JSONObject obj = response.getJSONObject(i);
                        TableRow row = new TableRow(getContext());

                        TextView rank = new TextView(getContext());
                        rank.setText(String.valueOf(i+1));
                        rank.setPadding(10, 5, 5, 5);
                        rank.setTextSize(15);
                        row.addView(rank);

                        TextView name = new TextView(getContext());
                        name.setText(obj.getString("name"));
                        name.setPadding(5, 5, 5, 5);
                        name.setTextSize(15);
                        row.addView(name);

                        TextView played = new TextView(getContext());
                        played.setText(String.valueOf(obj.getString("played")));
                        played.setPadding(5, 5, 5, 5);
                        played.setTextSize(15);
                        row.addView(played);

                        TextView wins = new TextView(getContext());
                        wins.setText(String.valueOf(obj.getString("wins")));
                        wins.setPadding(5, 5, 5, 5);
                        wins.setTextSize(15);
                        row.addView(wins);

                        TextView draws = new TextView(getContext());
                        draws.setText(String.valueOf(obj.getString("draws")));
                        draws.setPadding(5, 5, 5, 5);
                        draws.setTextSize(15);
                        row.addView(draws);

                        TextView losses = new TextView(getContext());
                        losses.setText(String.valueOf(obj.getString("losses")));
                        losses.setPadding(5, 5, 5, 5);
                        losses.setTextSize(15);
                        row.addView(losses);

                        TextView points = new TextView(getContext());
                        points.setText(String.valueOf(obj.getString("points")));
                        points.setPadding(5, 5, 5, 5);
                        points.setTextSize(15);
                        row.addView(points);

                        tableLayout.addView(row);
                        array[i] = obj.getString("name") + " " + obj.getString("played")+" Vs " +
                                obj.getString("wins") + " " + obj.getString("draws")+" "+obj.getString("losses")+obj.getString("points");
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
                if( array.length > 0 ) {
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, array);

                }else{
                    Toast.makeText(getContext(), "No Fixtures found!", Toast.LENGTH_LONG);
                }
                progressDialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if( error instanceof TimeoutError)
                    Log.d("ERROR --->",error.getMessage());
                if(error instanceof NoConnectionError)
                    Log.d("ERROR --->",error.getMessage());
                if(error instanceof AuthFailureError)
                    Log.d("ERROR --->",error.getMessage());
                if(error instanceof ServerError)
                    Log.d("ERROR --->",error.getMessage());
                if(error instanceof NetworkError)
                    Log.d("ERROR --->",error.getMessage());
                if(error instanceof ParseError)
                    Log.d("ERROR --->",error.getMessage());

//                Log.d("ERROR --->",error.getMessage());
                Toast.makeText(getContext(),"Error Occurred while fetching details",Toast.LENGTH_LONG);
                progressDialog.hide();
            }
        });
        AppController.getInstance().addToRequestQueue(req, tag_json_array);
        return view;
    }
}
