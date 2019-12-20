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
    private OauthService oauthService;

    private RestTemplate restTemplate = new RestTemplate();

    @Async
    public HttpEntity getWithParameters(String url, Integer size, Integer page, String lang, String term) {
        LOGGER.info("Calling the server with url:" + url);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        if (size != null) {
            builder.queryParam("size", size);
        }
        if (page != null) {
            builder.queryParam("page", page);
        }
        if (lang != null) {
            builder.queryParam("lang", lang);
        }
        if (term != null) {
            builder.queryParam("term", term);
        }
       return callTheServer(builder);
    }

    @Async
    public HttpEntity getWithLanguageParam(String url, String lang) {
        LOGGER.info("Calling the server with url and one language param:" + url);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        if (lang != null) {
            builder.queryParam("lang", lang);
        }
        return callTheServer(builder);
    }
    @Async
    public HttpEntity getWithoutParam(String url) {
        LOGGER.info("Calling the server with url and one language param:" + url);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        return callTheServer(builder);
    }

    private HttpEntity callTheServer(UriComponentsBuilder builder) {
        HttpEntity<?> entity = new HttpEntity<>(createHeader());
        HttpEntity<String> response = restTemplate.exchange(
            builder.toUriString(),
            HttpMethod.GET,
            entity,
            String.class);
        return response;
    }

    private HttpHeaders createHeader() {
        LOGGER.info("Creating header");
        HttpHeaders headers = new HttpHeaders();
        String freshToken = oauthService.getFreshToken();
        headers.add("Authorization", "Bearer " + freshToken);
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }
}
