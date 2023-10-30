package com.buloichyk.newsdealer.util;


import java.io.Serializable;
import java.util.Arrays;

public class ResponseObject implements Serializable {
    private long totalResults;      // useless should be

    private String nextPage;        // useless

    private NewsObject[] results;

    private String status;

    public long getTotalResults ()
    {
        return totalResults;
    }

    public void setTotalResults (long totalResults)
    {
        this.totalResults = totalResults;
    }

    public String getNextPage ()
    {
        return nextPage;
    }

    public void setNextPage (String nextPage)
    {
        this.nextPage = nextPage;
    }

    public NewsObject[] getResults() {
        return results;
    }

    public void setResults(NewsObject[] results) {
        this.results = results;
        int idCounter = 1;
//        AtomicInteger idCounter = new AtomicInteger(1);
//        Arrays.stream(this.results).forEach(e -> e.setId(idCounter.getAndIncrement()));
        for (NewsObject newsObject: this.results) {
            newsObject.setId(idCounter++);
        }
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ResponseObject{" +
                "totalResults=" + totalResults +
                ", nextPage='" + nextPage + '\'' +
                ", results=" + Arrays.toString(results) +
                ", status='" + status + '\'' +
                '}';
    }
}