package com.buloichyk.newsdealer.services;

import com.buloichyk.newsdealer.models.Category;
import com.buloichyk.newsdealer.util.NewsObject;
import com.buloichyk.newsdealer.util.ResponseObject;
import com.buloichyk.newsdealer.models.User;
import com.buloichyk.newsdealer.util.SearchObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Comparator;

@Service
public class NewsGeneratorService {

    private static final String API_KEY = "apikey=pub_30680664b8e369f58dc8cbbeebd4540e5069a";
    private static final String URL = "https://newsdata.io/api/1/news?";

    public ResponseObject generateNews(User user, SearchObject searchObject) {
        StringBuilder url = buildBasicUrl(searchObject);
        if (user.getCategories() != null && !user.getCategories().isEmpty()) {
            url.append("&category=");
            for (Category category: user.getCategories()) {
                url.append(category.getName()).append(",");
            }
            url.deleteCharAt(url.length() - 1);
        }
        if (user.getLanguage() != null) {
            url.append("&language=").append(user.getLanguage());
        }

        if (searchObject.isSpecifiedForCountry()) {
            url.append("&country=").append(user.getCountry());
        }

        return getResponseObject(searchObject, url);
    }

    public ResponseObject generateRandomNews(SearchObject searchObject) {
        StringBuilder url = buildBasicUrl(searchObject);
        // by default language will be english
        url.append("&language=en");

        return getResponseObject(searchObject, url);
    }

    private ResponseObject getResponseObject(SearchObject searchObject, StringBuilder url) {
        System.out.println(url);

        RestTemplate restTemplate = new RestTemplate();
        ResponseObject responseObject = restTemplate.getForObject(url.toString(), ResponseObject.class);

        if (responseObject == null) {
            throw new RuntimeException("Bad response from API");
        }

        return filterNews(responseObject, searchObject);
    }

    private StringBuilder buildBasicUrl(SearchObject searchObject) {
        StringBuilder url = new StringBuilder(URL);

        url.append(API_KEY);

        String query = searchObject.getQuery();
        if (query != null && !query.isEmpty()) {
            if (searchObject.isTitleSearch()) {
                url.append("&qInTitle=");
            } else {
                url.append("&q=");
            }
            url.append(query);
        }

        if (searchObject.getTimeframe() != null)
            url.append("&timeframe=").append(searchObject.getTimeframe());

        if (searchObject.getPriorityDomain() != null)
            url.append("&prioritydomain=").append(searchObject.getPriorityDomain());

        return url;
    }

    private ResponseObject filterNews(ResponseObject responseObject, SearchObject searchObject) {
        NewsObject[] news = responseObject.getResults();

        if (searchObject.isNewestFirst()) {
            Arrays.sort(news, (o1, o2) -> o2.getPubDate().compareTo(o1.getPubDate()));
        }

        if (searchObject.isOldestFirst()) {
            Arrays.sort(news, Comparator.comparing(NewsObject::getPubDate));
        }

        responseObject.setResults(news);
        return responseObject;
    }

}
