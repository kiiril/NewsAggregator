package com.buloichyk.newsdealer.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class NewsObject {
    private int id;
    private String article_id;      // useless
    private String title;
    private String link;
    private String[] keywords;
    private String[] creator;

    // probably it is array of strings
    private String video_url;
    private String description;
    private String content;
    private LocalDateTime pubDate;
    private String image_url;
    private String source_id;       // useless
    private long source_priority;   // useless
    private String[] country;
    private String[] category;
    private String language;

    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String[] getKeywords() {
        return keywords;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

    public String[] getCreator() {
        return creator;
    }

    public void setCreator(String[] creator) {
        this.creator = creator;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getPubDate() {
        return pubDate;
    }

    public void setPubDate(String stringPubDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.pubDate = LocalDateTime.parse(stringPubDate, formatter);
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getSource_id() {
        return source_id;
    }

    public void setSource_id(String source_id) {
        this.source_id = source_id;
    }

    public long getSource_priority() {
        return source_priority;
    }

    public void setSource_priority(long source_priority) {
        this.source_priority = source_priority;
    }

    public String[] getCountry() {
        return country;
    }

    public void setCountry(String[] country) {
        this.country = country;
    }

    public String[] getCategory() {
        return category;
    }

    public void setCategory(String[] category) {
        this.category = category;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "NewsObject{" +
                "article_id='" + article_id + '\'' +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", keywords=" + Arrays.toString(keywords) +
                ", creator=" + Arrays.toString(creator) +
                ", video_url='" + video_url + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", image_url='" + image_url + '\'' +
                ", source_id='" + source_id + '\'' +
                ", source_priority=" + source_priority +
                ", country=" + Arrays.toString(country) +
                ", category=" + Arrays.toString(category) +
                ", language='" + language + '\'' +
                '}';
    }

    public String getColor() {
        String category = getCategory()[0];
        return switch (category) {
            case "top" -> "#fa8072";
            case "world" -> "#4169e1";
            case "politics" -> "#a52a2a";
            case "business" -> "#4682b4";
            case "science" -> "#b0c4de";
            case "sports" -> "#ffe4b5";
            case "environment" -> "90ee90";
            case "food" -> "#228b22";
            case "health" -> "#adff2f";
            case "technology" -> "#6495ed";
            case "tourism" -> "#f4a460";
            case "entertainment" -> "#7b68ee";
            default -> "";
        };
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
