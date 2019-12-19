package com.klm.springangular;

import com.klm.springangular.service.OauthService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OauthServiceTest {

    @Autowired
    private OauthService oauthService;
    //TODO check this test
    @Test
    public void IsTokeExpired() {
        Date yesterday = new Date(System.currentTimeMillis()-24*60*60*1000);
//        assertTrue(yesterday.before(oauthService.tokenExpiryDate()));
    }
}
