package com.buloichyk.newsdealer.util;

public class SearchObject {
    private String query;
    private boolean titleSearch;
    private boolean specifiedForCountry;

    private boolean sortByTitle;

    private boolean sortByRelevance;

    private Integer timeframe;

    private boolean priorityDomain;

    // domain relevance maybe


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

    public boolean isSortByTitle() {
        return sortByTitle;
    }

    public void setSortByTitle(boolean sortByTitle) {
        this.sortByTitle = sortByTitle;
    }

    public boolean isSortByRelevance() {
        return sortByRelevance;
    }

    public void setSortByRelevance(boolean sortByRelevance) {
        this.sortByRelevance = sortByRelevance;
    }

    public Integer getTimeframe() {
        return timeframe;
    }

    public void setTimeframe(Integer timeframe) {
        this.timeframe = timeframe;
    }

    public boolean isPriorityDomain() {
        return priorityDomain;
    }

    public void setPriorityDomain(boolean priorityDomain) {
        this.priorityDomain = priorityDomain;
    }
}
