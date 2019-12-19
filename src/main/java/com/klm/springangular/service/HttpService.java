package com.klm.springangular.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class HttpService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpService.class);

    @Autowired
    OauthService oauthService;
    private RestTemplate restTemplate;

    @Async
    public HttpEntity getWithParameters(String url, Integer size, Integer page, String lang, String term) {
        LOGGER.info("we are sending with url:" + url);
        restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        if (size != null) {
            builder.queryParam("size", size);
        }
        if (page != null) {
            builder.queryParam("page", page);
        }
        if (!lang.equals("null")) {
            builder.queryParam("lang", lang);
        }
        if (!term.equals("null")){
            builder.queryParam("term", term);
        }
        HttpEntity<?> entity = new HttpEntity<>(createHeader());
        HttpEntity<String> response = restTemplate.exchange(
            builder.toUriString(),
            HttpMethod.GET,
            entity,
            String.class);
        return response;
    }

    private HttpHeaders createHeader() {
        LOGGER.info("creating header");
        HttpHeaders headers = new HttpHeaders();
        String freshToken = oauthService.getFreshToken();
        headers.add("Authorization","Bearer " + freshToken);
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }
}
