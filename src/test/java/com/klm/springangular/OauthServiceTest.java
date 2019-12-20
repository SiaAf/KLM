package com.klm.springangular;

import com.klm.springangular.service.OauthService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OauthServiceTest {

    @Autowired
    private OauthService oauthService;

    @Test
    public void Get_Fresh_Token() {
        String freshToken = oauthService.getFreshToken();
        assertTrue(freshToken.length() == 36);
    }
}
