package com.example.liao.g_gank.model.data;

import java.util.List;

/**
 * Created by liao on 2016/5/11.
 */
public class GirlResultRoot {

    private boolean error;

    private List<GirlResult> results ;

    public void setError(boolean error){
        this.error = error;
    }
    public boolean getError(){
        return this.error;
    }
    public void setResults(List<GirlResult> results){
        this.results = results;
    }
    public List<GirlResult> getResults(){
        return this.results;
    }
}
