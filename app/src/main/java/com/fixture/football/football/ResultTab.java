package com.fixture.football.football;

import android.app.ListFragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
 * Created by bhavesh on 16/3/16.
 */
public class ResultTab extends Fragment {
    private String league_id;
    private String tag_json_array = "json_array_req";
    private String url = "http://54.187.141.48/index.php/api/v1/results/";
    ListView listView;
    private ProgressDialog progressDialog;

    public void setLeagueId(String league_id){
        this.league_id = league_id;
        this.url = this.url + this.league_id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.result_tab, container, false);
        listView = (ListView)view.findViewById(R.id.result_list);

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
                        array[i] = obj.getString("Home") + " " + obj.getString("Goal_Home")+" -- " +
                                    obj.getString("Goal_Away") + " " + obj.getString("Away")+" "+obj.getString("date");
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
                if( array.length > 0 ) {
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, array);
                    listView.setAdapter(adapter);
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
