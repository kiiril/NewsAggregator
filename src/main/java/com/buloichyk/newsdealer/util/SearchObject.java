package com.buloichyk.newsdealer.util;

public class SearchObject {
    private String query;
    private boolean titleSearch;
    private boolean specifiedForCountry;

    // TODO add filters for news


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
}
