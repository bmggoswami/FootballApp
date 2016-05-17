package com.fixture.football.football;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.fixture.football.message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openFixture(View view){
        Intent intent = new Intent(this,MyTabActivity.class);
        switch (view.getId()){
            case R.id.euro:
                intent.putExtra(EXTRA_MESSAGE,"UEFA Euro 2016");
                intent.putExtra("league_id","424");
                break;
            case R.id.uefa:
                intent.putExtra(EXTRA_MESSAGE,"Uefa Champions League");
                intent.putExtra("league_id","405");
                break;
            case R.id.epl:
                intent.putExtra(EXTRA_MESSAGE,"English Premeir League");
                intent.putExtra("league_id","398");
                break;
            case R.id.laliga:
                intent.putExtra(EXTRA_MESSAGE,"La Liga");
                intent.putExtra("league_id","399");
                break;
            case R.id.bundesliga:
                intent.putExtra(EXTRA_MESSAGE,"Bundesliga");
                intent.putExtra("league_id","394");
                break;
        }
        startActivity(intent);
    }
}
