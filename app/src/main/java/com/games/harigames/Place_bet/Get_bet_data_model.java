package com.games.harigames.Place_bet;

public class Get_bet_data_model {
    String bet_number;
    String bet_count;
    String totel_bet_amount;
    String market_type;
    String market_time;

    public Get_bet_data_model(String bet_number, String bet_count, String totel_bet_amount, String market_type, String market_time) {
        this.bet_number = bet_number;
        this.bet_count = bet_count;
        this.totel_bet_amount = totel_bet_amount;
        this.market_type = market_type;
        this.market_time = market_time;
    }

    public String getMarket_type() {
        return market_type;
    }

    public void setMarket_type(String market_type) {
        this.market_type = market_type;
    }

    public String getMarket_time() {
        return market_time;
    }

    public void setMarket_time(String market_time) {
        this.market_time = market_time;
    }

    public String getBet_number() {
        return bet_number;
    }

    public void setBet_number(String bet_number) {
        this.bet_number = bet_number;
    }

    public String getBet_count() {
        return bet_count;
    }

    public void setBet_count(String bet_count) {
        this.bet_count = bet_count;
    }

    public String getTotel_bet_amount() {
        return totel_bet_amount;
    }

    public void setTotel_bet_amount(String totel_bet_amount) {
        this.totel_bet_amount = totel_bet_amount;
    }
}
