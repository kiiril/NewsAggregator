package com.buloichyk.newsdealer.services;

import com.buloichyk.newsdealer.models.Category;
import com.buloichyk.newsdealer.models.ResponseObject;
import com.buloichyk.newsdealer.models.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NewsGeneratorService {
    private static final String URL = "https://newsdata.io/api/1/news?apikey=pub_30680664b8e369f58dc8cbbeebd4540e5069a";

    public ResponseObject generateNews(User user) {
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
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(url);
        return restTemplate.getForObject(url.toString(), ResponseObject.class);
    }

    public ResponseObject generateRandomNews() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(URL, ResponseObject.class);
    }

}
