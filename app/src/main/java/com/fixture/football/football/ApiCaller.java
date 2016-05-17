package com.fixture.football.football;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bhavesh on 19/3/16.
 */
public class ApiCaller {
    private String tag_json_array = "json_array_req";
    private String url = "http://54.187.141.48/index.php/api/v1/fixture/398";
    private ArrayAdapter adapter;

    public void makeRequest( final ListView list){
        JsonArrayRequest req = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String[] arr = new String[response.length()];
                for(int i=0;i<response.length();i++){
                    try{
                        JSONObject obj = response.getJSONObject(i);
                        Log.d("SUCCESS --->",obj.getString("h_code"));
                        arr[i] = obj.getString("h_code");
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
                //ArrayAdapter adapter = new ArrayAdapter(FixtureTab.this,android.R.layout.simple_list_item_1,arr);
                //list.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR --->",error.getMessage());
            }
        });
        AppController.getInstance().addToRequestQueue(req,tag_json_array);
    }
}
