package com.games.harigames.market;

public class User_market {
    String market_name;
    String open_time;
    String close_time;
    public User_market(){

    }
    public User_market(String market_name, String open_time, String close_time) {
        this.market_name = market_name;
        this.open_time = open_time;
        this.close_time = close_time;
    }

    public String getMarket_name() {
        return market_name;
    }

    public void setMarket_name(String market_name) {
        this.market_name = market_name;
    }

    public String getOpen_time() {
        return open_time;
    }

    public void setOpen_time(String open_time) {
        this.open_time = open_time;
    }

    public String getClose_time() {
        return close_time;
    }

    public void setClose_time(String close_time) {
        this.close_time = close_time;
    }
}
