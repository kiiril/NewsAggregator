package com.buloichyk.newsdealer.services;

import com.buloichyk.newsdealer.models.Category;
import com.buloichyk.newsdealer.util.ResponseObject;
import com.buloichyk.newsdealer.models.User;
import com.buloichyk.newsdealer.util.SearchObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NewsGeneratorService {
    private static final String URL = "https://newsdata.io/api/1/news?apikey=pub_30680664b8e369f58dc8cbbeebd4540e5069a";

    public ResponseObject generateNews(User user, SearchObject searchObject) {
        StringBuilder url = new StringBuilder(URL);
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
        String query = searchObject.getQuery();
        if (query != null && !query.isEmpty()) {
            if (searchObject.isTitleSearch()) {
                url.append("&qInTitle=");
            } else {
                url.append("&q=");
            }
            url.append(query);
        }
        if (searchObject.isSpecifiedForCountry()) {
            url.append("&country=").append(user.getCountry());
        }
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(url);
        return restTemplate.getForObject(url.toString(), ResponseObject.class);
    }

    public ResponseObject generateRandomNews(SearchObject searchObject) {
        StringBuilder url = new StringBuilder(URL);
        String query = searchObject.getQuery();
        if (query != null && !query.isEmpty()) {
            if (searchObject.isTitleSearch()) {
                url.append("&qInTitle=");
            } else {
                url.append("&q=");
            }
            url.append(query);
        }
        // by default language will be english
        url.append("&language=en");
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(url);
        return restTemplate.getForObject(url.toString(), ResponseObject.class);
    }

}
