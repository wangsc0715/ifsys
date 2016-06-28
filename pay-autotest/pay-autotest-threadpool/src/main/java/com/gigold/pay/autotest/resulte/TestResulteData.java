package com.gigold.pay.autotest.resulte;

import net.sf.json.JSONObject;

/**
 * Created by chenkuan on 15/12/8.
 */
public class TestResulteData implements ResulteData {
    private int count;
    private String resultString;
    private JSONObject resultObj;

    public int getCount() {
        return count;
    }

    public void addResulte(){

    };
    public JSONObject getResulte(){
        return resultObj;
    };
}
