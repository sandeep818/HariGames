package com.games.harigames.Place_bet;

import com.google.firebase.database.ServerValue;

import java.util.Map;

public class Plece_bet {
    String bet_number,bet_type,market_name,market_type,username,date, amount,status ,betTime,market_open_time,market_close_time;
    boolean settlement;

    public Object getBet_time() {
        return bet_time;
    }

    public void setBet_time(Object bet_time) {
        this.bet_time = bet_time;
    }

    Object bet_time ;

   public Plece_bet() {
    }

    public String getBetTime() {
        return betTime;
    }

    public void setBetTime(String betTime) {
        this.betTime = betTime;
    }

    public Plece_bet(String betTime, String bet_number, String bet_type, String market_name, String market_type, String username, String date, String amount, String status, boolean settlement,String market_open_time,String market_close_time) {
        this.bet_time =  ServerValue.TIMESTAMP;
        this.betTime=betTime;
        this.bet_number = bet_number;
        this.bet_type = bet_type;
        this.market_name = market_name;
        this.market_type = market_type;
        this.username = username;
        this.date = date;
        this.amount = amount;
        this.status = status;
        this.settlement = settlement;
        this.market_open_time=market_open_time;
        this.market_close_time=market_close_time;
    }

    public String getMarket_open_time() {
        return market_open_time;
    }

    public void setMarket_open_time(String market_open_time) {
        this.market_open_time = market_open_time;
    }

    public String getMarket_close_time() {
        return market_close_time;
    }

    public void setMarket_close_time(String market_close_time) {
        this.market_close_time = market_close_time;
    }

    public String getBet_number() {
        return bet_number;
    }

    public void setBet_number(String bet_number) {
        this.bet_number = bet_number;
    }

    public String getBet_type() {
        return bet_type;
    }

    public void setBet_type(String bet_type) {
        this.bet_type = bet_type;
    }

    public String getMarket_name() {
        return market_name;
    }

    public void setMarket_name(String market_name) {
        this.market_name = market_name;
    }

    public String getMarket_type() {
        return market_type;
    }

    public void setMarket_type(String market_type) {
        this.market_type = market_type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isSettlement() {
        return settlement;
    }

    public void setSettlement(boolean settlement) {
        this.settlement = settlement;
    }
}
