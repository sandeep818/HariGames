package com.games.harigames.Place_bet;

public class Result_Declare_model {
    String market_name,result_date,opneing_result,closeing_result,single_result_one,single_result_two;

    public Result_Declare_model(String market_name, String result_date, String opneing_result, String closeing_result, String single_result_one, String single_result_two) {
        this.market_name = market_name;
        this.result_date = result_date;
        this.opneing_result = opneing_result;
        this.closeing_result = closeing_result;
        this.single_result_one = single_result_one;
        this.single_result_two = single_result_two;
    }

    public String getMarket_name() {
        return market_name;
    }

    public void setMarket_name(String market_name) {
        this.market_name = market_name;
    }

    public String getResult_date() {
        return result_date;
    }

    public void setResult_date(String result_date) {
        this.result_date = result_date;
    }

    public String getOpneing_result() {
        return opneing_result;
    }

    public void setOpneing_result(String opneing_result) {
        this.opneing_result = opneing_result;
    }

    public String getCloseing_result() {
        return closeing_result;
    }

    public void setCloseing_result(String closeing_result) {
        this.closeing_result = closeing_result;
    }

    public String getSingle_result_one() {
        return single_result_one;
    }

    public void setSingle_result_one(String single_result_one) {
        this.single_result_one = single_result_one;
    }

    public String getSingle_result_two() {
        return single_result_two;
    }

    public void setSingle_result_two(String single_result_two) {
        this.single_result_two = single_result_two;
    }
}
