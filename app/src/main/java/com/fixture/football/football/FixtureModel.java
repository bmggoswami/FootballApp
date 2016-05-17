package com.fixture.football.football;

/**
 * Created by bhavesh on 4/5/16.
 */
public class FixtureModel {
    private String home;
    private String away;
    private String date;

    public FixtureModel(String home,String away,String date){
        this.home = home;
        this.away = away;
        this.date = date;
    }
    public void setHome( String home ){
        this.home = home;
    }

    public void setAway( String away ){
        this.away = away;
    }

    public void setDate( String date ){
        this.date = date;
    }

    public String getHome(){
        return this.home;
    }
    public String getAway(){
        return this.away;
    }

    public String getDate(){
        return this.date;
    }
}
