package com.buloichyk.newsdealer.util;

public class SearchObject {
    private String query;
    private boolean titleSearch;
    private boolean specifiedForCountry;

    private boolean newestFirst;

    private boolean oldestFirst;

    private Integer timeframe;

    private String priorityDomain;

    private String nextPage;


    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public boolean isTitleSearch() {
        return titleSearch;
    }

    public void setTitleSearch(boolean titleSearch) {
        this.titleSearch = titleSearch;
    }

    public boolean isSpecifiedForCountry() {
        return specifiedForCountry;
    }

    public void setSpecifiedForCountry(boolean specifiedForCountry) {
        this.specifiedForCountry = specifiedForCountry;
    }

    public boolean isNewestFirst() {
        return newestFirst;
    }

    public void setNewestFirst(boolean newestFirst) {
        this.newestFirst = newestFirst;
    }

    public boolean isOldestFirst() {
        return oldestFirst;
    }

    public void setOldestFirst(boolean oldestFirst) {
        this.oldestFirst = oldestFirst;
    }

    public Integer getTimeframe() {
        return timeframe;
    }

    public void setTimeframe(Integer timeframe) {
        this.timeframe = timeframe;
    }

    public String getPriorityDomain() {
        return priorityDomain;
    }

    public void setPriorityDomain(String priorityDomain) {
        this.priorityDomain = priorityDomain;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }
}
