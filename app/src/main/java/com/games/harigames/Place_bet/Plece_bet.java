package com.games.harigames.Place_bet;

public class Plece_bet {
    String bet_number,bet_type,market_name,market_type,username,date, amount,status;
    boolean settlement;
    long aLong_time ;

//    public Plece_bet() {
//    }

    public Plece_bet(long aLong_time , String bet_number, String bet_type, String market_name, String market_type, String username, String date, String amount, String status, boolean settlement) {
        this.aLong_time = aLong_time;
        this.bet_number = bet_number;
        this.bet_type = bet_type;
        this.market_name = market_name;
        this.market_type = market_type;
        this.username = username;
        this.date = date;
        this.amount = amount;
        this.status = status;
        this.settlement = settlement;
    }

    public long getaLong_time() {
        return aLong_time;
    }

    public void setaLong_time(long aLong_time) {
        this.aLong_time = aLong_time;
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
