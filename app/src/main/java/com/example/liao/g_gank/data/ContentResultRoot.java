package com.example.liao.g_gank.data;

import java.util.List;

/**
 * Created by liao on 2016/5/8.
 */
public class ContentResultRoot {

    private boolean error;

    private List<ContentResult> results;

    public boolean getError() {
        return this.error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ContentResult> getResults() {
        return this.results;
    }

    public void setResults(List<ContentResult> results) {
        this.results = results;
    }

}
