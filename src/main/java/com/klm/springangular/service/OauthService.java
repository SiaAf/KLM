package com.klm.springangular.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OauthService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OauthService.class);
    @Autowired
    private OAuth2RestOperations restTemplate;
    private String freshToken;
    private Date tokenExpiryDate;


    public String getToken() {
        LOGGER.info("getToken");
        OAuth2AccessToken accessToken = restTemplate.getAccessToken();
        freshToken = accessToken.getValue();
        tokenExpiryDate =accessToken.getExpiration();
        return freshToken;
    }

    public String getFreshToken() {
        LOGGER.info("getFreshToken");
        Date now = new Date();
        if (freshToken == null || tokenExpiryDate.before(now)) {
            return getToken();
        } else {
            return freshToken;
        }
    }
}
