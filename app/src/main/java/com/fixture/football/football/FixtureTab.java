package com.fixture.football.football;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by bhavesh on 16/3/16.
 */
public class FixtureTab extends Fragment {

    private String tag_json_array = "json_array_req";
    private String url = "http://54.187.141.48/index.php/api/v1/fixture/";
    ListView listView;
    private ProgressDialog progressDialog;
    private String league_id;
    Context context;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fixture_tab, container, false);

        listView = (ListView) view.findViewById(R.id.list);
        Bundle bundle = this.getArguments();
        this.url = this.url + bundle.getString("league_id");

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        final TableLayout tableLayout = (TableLayout) view.findViewById(R.id.fixture_table);
        final List<FixtureModel> modelList = new ArrayList<FixtureModel>();
        JsonArrayRequest req = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("RESPONSE :- ", response.toString());
                String[] array = new String[response.length()];
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);

                        FixtureModel items = new FixtureModel(obj.getString("Home"),obj.getString("Away"),obj.getString("date"));
                        modelList.add(items);
                        String dateStr = "Jul 16, 2013 12:08:59 AM";
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        df.setTimeZone(TimeZone.getTimeZone("GMT"));
                        Date date = df.parse(obj.getString("date"));
                        df.setTimeZone(TimeZone.getDefault());
                        String formattedDate = df.format(date);

                        Log.d("DATE EURO :-",obj.getString("date"));
                        Log.d("DATE IST  :-",formattedDate);
                        array[i] = obj.getString("Home") + " Vs " + obj.getString("Away") + " " + formattedDate;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if (array.length > 0) {
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, array);
//                    FixtureAdapter adapter1 = new FixtureAdapter(getContext().getApplicationContext(),R.layout.list_item,modelList);
                    listView.setAdapter(adapter);
                } else {
                    Toast.makeText(getContext(), "No Fixtures found!", Toast.LENGTH_LONG);
                }
                progressDialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError)
                    Log.d("ERROR --->", error.getMessage());
                if (error instanceof NoConnectionError)
                    Log.d("ERROR --->", error.getMessage());
                if (error instanceof AuthFailureError)
                    Log.d("ERROR --->", error.getMessage());
                if (error instanceof ServerError)
                    Log.d("ERROR --->", error.getMessage());
                if (error instanceof NetworkError)
                    Log.d("ERROR --->", error.getMessage());
                if (error instanceof ParseError)
                    Log.d("ERROR --->", error.getMessage());

//                Log.d("ERROR --->",error.getMessage());
                Toast.makeText(getContext(), "Error Occurred while fetching details", Toast.LENGTH_LONG);
                progressDialog.hide();
            }
        });
        AppController.getInstance().addToRequestQueue(req, tag_json_array);
        return view;
    }
}